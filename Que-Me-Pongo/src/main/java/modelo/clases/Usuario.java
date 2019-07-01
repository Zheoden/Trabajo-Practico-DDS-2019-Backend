package modelo.clases;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	ArrayList<Guardarropas> guardarropas = new ArrayList<Guardarropas>();
	
	public Usuario(ArrayList<Guardarropas> guardaRopas) {
		this.setGuardaRopas(guardaRopas);
	}
	
	public List<Atuendo> todosPosiblesAtuendosPorGuardarropa() {
		List<Atuendo> atuendosValidos = new ArrayList<Atuendo>();
		this.guardarropas.forEach( guardarropa -> atuendosValidos.addAll(guardarropa.atuendosValidosParaAhora()));
		return atuendosValidos;
	}
	
	public ArrayList<Guardarropas> getGuardaRopas() {
		return guardarropas;
	}

	public void setGuardaRopas(ArrayList<Guardarropas> guardaRopas) {
		this.guardarropas = guardaRopas;
	}
}
