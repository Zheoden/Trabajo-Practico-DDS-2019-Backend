package modelo.proveedores.openweather;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import modelo.interfaces.Proveedores;
import modelo.proveedores.openweather.IProveedorClima;
import modelo.proveedores.openweather.PronosticoHomogeneo;
import modelo.proveedores.openweather.openweather.actual.OpenWeatherDTO;
import modelo.proveedores.openweather.openweather.extendido.ExtendedOpenWeatherDTO;
import modelo.proveedores.openweather.openweather.extendido.WeatherEvery3Hours;
import modelo.utils.JsonParser;

import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OpenWeather implements Proveedores {
	private Client client;
	private static final String key = "8265e5cbff52629ea9d1042c6dee7cb0";
	private JsonParser jsonParser = new JsonParser();
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	private static final String pronosticoActualUrl = "http://api.openweathermap.org/data/2.5/weather";
	private static final String pronosticoPorHoraUrl = "http://api.openweathermap.org/data/2.5/forecast";

	public OpenWeatherDTO obtenerPronosticoActual() {
		client = Client.create();
		WebResource webResource = client.resource(pronosticoActualUrl).queryParam("id", "3433955").queryParam("appid", key);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		String output = response.getEntity(String.class);
		return jsonMapper.fromJson(output, new TypeReference<OpenWeatherDTO>() {
		});
	}

	public ExtendedOpenWeatherDTO obtenerPronosticoExtendido() {
		client = Client.create();
		WebResource webResource = client.resource(pronosticoPorHoraUrl).queryParam("id", "3433955").queryParam("appid", key);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		String output = response.getEntity(String.class);
		return jsonMapper.fromJson(output, new TypeReference<ExtendedOpenWeatherDTO>() {
		});
	}

	@Override
	public PronosticoHomogeneo obtenerPronosticoActualHomogeneo() {
		LocalDate now = LocalDate.now();
		OpenWeatherDTO pronosticoActual = obtenerPronosticoActual();
		return new PronosticoHomogeneo(now, pronosticoActual.getMain().getTemp(),
				pronosticoActual.getWeather().get(0).getMain(), pronosticoActual.getMain().getHumidity(),
				pronosticoActual.getMain().getTemp_min(), pronosticoActual.getMain().getTemp_max(), "Celsius");
	}

	@Override
	public PronosticoHomogeneo obtenerTemperaturATalDia(LocalDate fecha) { //EL FORMATO DE LA FECHA PASADO DEBE SER dd/mm/aaaa
		ExtendedOpenWeatherDTO pronosticoExtendido = obtenerPronosticoExtendido();
		List<WeatherEvery3Hours> pronosticoDelDiaCadaTresHoras =
				pronosticoExtendido.
						getList().
						stream().
						filter(clima -> esElClimaDeLaFecha(clima, fecha)).
						collect(Collectors.toList());
		WeatherEvery3Hours pronosticoDelDia = pronosticoDelDiaCadaTresHoras.get(0);
		return new PronosticoHomogeneo(fecha, pronosticoDelDia.getMain().getTemp(), pronosticoDelDia.getWeather().get(0).getMain(),
				pronosticoDelDia.getMain().getHumidity(), pronosticoDelDia.getMain().getTemp_min(),
				pronosticoDelDia.getMain().getTemp_max(), "Celsius");
	}

	private Boolean esElClimaDeLaFecha(WeatherEvery3Hours clima, LocalDate fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate fechaDelClima = LocalDate.parse(clima.getDt_txt(), formatter);

		return fecha.equals(fechaDelClima);
	}

	@Override
	public Boolean estaActivo() {
		Client client = Client.create();
		WebResource webResource = client.resource(pronosticoPorHoraUrl).queryParam("id", "3433955").queryParam("appid", key);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		String output = response.getEntity(String.class);
		ResponseDTO responseDTO = jsonMapper.fromJson(output, new TypeReference<ResponseDTO>() {
		});

		return responseDTO.getCod() == 200;
	}
}