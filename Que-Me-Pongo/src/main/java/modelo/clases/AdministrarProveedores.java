package modelo.clases;

import modelo.interfaces.Proveedores;

import java.util.Date;
import java.util.Set;

public class AdministrarProveedores {
	
	Set<Proveedores> proveedores;
	
	public AdministrarProveedores() {
	}
	
	public int obtenerTemperatura(Date fecha) {
		return 7;
	}
	
	public int obtenerTemperaturaActual() {
		return 7;
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
