package modelo.interfaces;

import java.time.LocalDate;

import modelo.proveedores.openweather.ExtendedMain;
import modelo.proveedores.openweather.ExtendedOpenWeatherDTO;
import modelo.proveedores.openweather.OpenWeatherDTO;

public interface Proveedores {

	OpenWeatherDTO obtenerPronosticoActual();

	ExtendedOpenWeatherDTO obtenerPronosticoExtendido();

	Double obtenerTemperaturATalDia(LocalDate fecha);

	Boolean esElClimaDeLaFecha(ExtendedMain clima, LocalDate fecha);

	Boolean estaActivo();
}
