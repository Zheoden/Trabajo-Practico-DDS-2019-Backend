package modelo.clases;

import modelo.interfaces.Proveedores;

import java.util.Date;
import java.util.Set;

public class AdministrarProveedores {
	
	Set<Proveedores> proveedores;
	
	public AdministrarProveedores() {
	}
	
	public Double obtenerTemperatura(Date fecha) {
		return 7.0;
	}
	
	public Double obtenerTemperaturaActual() {
		return 7.0;
	}
	
	public void addProveedores(Proveedores proveedores) {
		this.proveedores.add(proveedores);
	}
	
	public void setProveedores(Set<Proveedores> proveedores) {
		this.proveedores = proveedores;
	}
	
	public Set<Proveedores> getProveedores() {
		return this.proveedores;
	}
}
