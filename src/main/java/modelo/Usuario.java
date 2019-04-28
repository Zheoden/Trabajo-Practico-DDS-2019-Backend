package modelo;

import java.util.ArrayList;

public class Usuario {
	
	ArrayList<Guardarropas> guardarropas = new ArrayList<Guardarropas>();
	
	
	public Usuario(ArrayList <Guardarropas> guardaRopas) {
		this.setGuardaRopas(guardaRopas);
	}

	public ArrayList<Guardarropas> getGuardaRopas() {
		return guardarropas;
	}


	public void setGuardaRopas(ArrayList<Guardarropas> guardaRopas) {
		this.guardarropas = guardaRopas;
	}
	
	public Atuendo sugerimePrendas(QueMePongo queMePongo) {
		return queMePongo.prendasSugeridasPara(this);
	}
}
