package test.modelo.proveedores;

import modelo.proveedores.openweather.OpenWeather;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class OpenWeatherTest {

    @Test
    @DisplayName("Test para obtener la temperatura del dia X")
    public void getClientsFromJSON() throws ParseException {    	
    	OpenWeather proveedor = new OpenWeather();
    	
    	String localDate = "2019-07-07 00:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate fechaDelClima = LocalDate.parse(localDate, formatter);

    	System.out.println(proveedor.obtenerTemperaturATalDia(fechaDelClima));
    	    	

    }
}
