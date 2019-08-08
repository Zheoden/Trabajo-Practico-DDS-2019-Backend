package modelo.interfaces;

import java.time.LocalDate;

import modelo.dtos.ExtendedOpenWeatherDTO;
import modelo.dtos.OpenWeatherDTO;
import proveedores.openweather.ExtendedMain;

public interface Proveedores {

	OpenWeatherDTO obtenerPronosticoActual();

	ExtendedOpenWeatherDTO obtenerPronosticoExtendido();

	Double obtenerTemperaturATalDia(LocalDate fecha);

	Boolean esElClimaDeLaFecha(ExtendedMain clima, LocalDate fecha);

	Boolean estaActivo();
}
