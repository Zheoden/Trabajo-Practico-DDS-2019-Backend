package modelo.interfaces;

import java.time.LocalDate;

import modelo.proveedores.openweather.PronosticoHomogeneo;

public interface Proveedores {
	
	PronosticoHomogeneo obtenerPronosticoActualHomogeneo();
	PronosticoHomogeneo obtenerTemperaturATalDia(LocalDate fecha);
	Boolean estaActivo();

}
