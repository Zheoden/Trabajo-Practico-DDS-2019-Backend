package proveedores.openweather;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import modelo.dtos.ExtendedOpenWeatherDTO;
import modelo.dtos.OpenWeatherDTO;
import modelo.interfaces.Proveedores;
import utils.JsonParser;
import utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.MediaType;

public class OpenWeather implements Proveedores {
	private Client client;
	private static String key;
	private static String pronosticoActualUrl;
	private static String pronosticoPorHoraUrl;

	public OpenWeather() {
		try {
			key = Utils.getProyectProperties().getProperty("open.weather.key");
			pronosticoActualUrl = Utils.getProyectProperties().getProperty("open.weather.hora.actual.url");
			pronosticoPorHoraUrl = Utils.getProyectProperties().getProperty("open.weather.por.hora.url");
		} catch (Exception e) {
			key = "";
			pronosticoActualUrl = "";
			pronosticoPorHoraUrl = "";
		}
	}

	@Override
	public OpenWeatherDTO obtenerPronosticoActual() {
		client = Client.create();
		WebResource webResource = client.resource(pronosticoActualUrl).queryParam("id", "3433955").queryParam("appid",
				key);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		String output = response.getEntity(String.class);
		return JsonParser.read(output, new TypeReference<OpenWeatherDTO>() {
		});
	}

	@Override
	public ExtendedOpenWeatherDTO obtenerPronosticoExtendido() {
		client = Client.create();
		WebResource webResource = client.resource(pronosticoPorHoraUrl).queryParam("id", "3433955").queryParam("appid",
				key);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		String output = response.getEntity(String.class);
		return JsonParser.read(output, new TypeReference<ExtendedOpenWeatherDTO>() {
		});
	}

	@Override
	public Double obtenerTemperaturATalDia(LocalDate fecha) { // EL FORMATO DE LA FECHA PASADO DEBE SER dd/mm/aaaa
		ExtendedOpenWeatherDTO pronosticoExtendido = obtenerPronosticoExtendido();
		List<ExtendedMain> pronosticoDelDiaCadaTresHoras = pronosticoExtendido.getList().stream()
				.filter(clima -> esElClimaDeLaFecha(clima, fecha)).collect(Collectors.toList());
		ExtendedMain pronosticoDelDia = pronosticoDelDiaCadaTresHoras.get(0);
		return pronosticoDelDia.getTemp();
	}

	@Override
	public Boolean esElClimaDeLaFecha(ExtendedMain clima, LocalDate fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate fechaDelClima = LocalDate.parse(clima.getDt_txt(), formatter);

		return fecha.equals(fechaDelClima);
	}

	@Override
	public Boolean estaActivo() {
		return true;
	}
}