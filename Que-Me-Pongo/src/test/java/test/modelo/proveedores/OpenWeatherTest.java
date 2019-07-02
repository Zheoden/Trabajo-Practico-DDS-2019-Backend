package test.modelo.proveedores;

import modelo.proveedores.openweather.OpenWeather;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Tests para el proveedor OpenWeather")
public class OpenWeatherTest {
	
	OpenWeather proveedor = new OpenWeather();

	@Test
	@DisplayName("Test para obtener la temperatura actual")
	public void obtenerPronosticoActual() {
		
	}
	
	@Test
	@DisplayName("Test para validar los pronosticos de toda la semana")
	public void obtenerPronosticoExtendido() {

	}
	
	@Test
	@DisplayName("Test para obtener la temperatura del dia X")
	public void obtenerTemperaturATalDia() {
		

		String localDate = "2019-07-07 00:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate fechaDelClima = LocalDate.parse(localDate, formatter);

		System.out.println(proveedor.obtenerTemperaturATalDia(fechaDelClima));

	}
	
	@Test
	@DisplayName("Test para validar si el proveedor esta activo")
	public void estaActivo() {
		Assert.assertTrue(proveedor.estaActivo());
	}
}
