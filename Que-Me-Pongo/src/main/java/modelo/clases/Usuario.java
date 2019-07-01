package modelo.clases;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	Suscripcion suscripcion;
	ArrayList<Guardarropas> guardarropas = new ArrayList<Guardarropas>();
	
	public Usuario(ArrayList <Guardarropas> guardaRopas, Suscripcion unaSuscripcion) throws Exception  {
		this.setGuardaRopas(guardaRopas);
		this.setSuscripcion(unaSuscripcion);
	}
	
	public List<Atuendo> todosPosiblesAtuendosPorGuardarropa() {
		List<Atuendo> atuendosValidos = new ArrayList<Atuendo>();
		this.guardarropas.forEach( guardarropa -> atuendosValidos.addAll(guardarropa.atuendosValidosParaAhora()));
		return atuendosValidos;
	}
	
	public Suscripcion getSuscripcion() {
		return suscripcion;
	}
	
	public void setSuscripcion(Suscripcion unaSuscripcion) {
		suscripcion = unaSuscripcion;
	}
	
	public boolean listaDeGuardarropasValida(ArrayList<Guardarropas> guardaRopas) {
		return guardaRopas.stream().allMatch(unGuardarropa -> unGuardarropa.tamanioGuardarropas() <= suscripcion.cantidadPrendasPermitidas());
	}
	
	public ArrayList<Guardarropas> getGuardaRopas() {
		return guardarropas;
	}

	public void setGuardaRopas(ArrayList<Guardarropas> guardaRopas) {
		this.guardarropas = guardaRopas;
	}
}
