package modelo.clases;

import modelo.interfaces.Proveedores;
import modelo.proveedores.openweather.OpenWeather;

import java.time.LocalDate;

public class AdministrarProveedores {

	Proveedores proveedores;

	public AdministrarProveedores() {
		this.proveedores = (new OpenWeather());
	}

	public Double obtenerTemperatura(LocalDate fecha) {
		return this.proveedores.obtenerTemperaturATalDia(fecha);
	}

	public Double obtenerTemperaturaActual() {
		return this.proveedores.obtenerPronosticoActual().getTemp();
	}

//	public void addProveedores(Proveedores proveedor) {
//		System.out.println(proveedor);
//	}

//	public void setProveedores(Set<Proveedores> proveedores) {
//		this.proveedores = proveedores;
//	}
//	
//	public Set<Proveedores> getProveedores() {
//		return this.proveedores;
//	}
}
