package modelo;

import java.util.ArrayList;

public class Usuario {
	
	ArrayList<GuardaRropas> guardaRopas = new ArrayList<GuardaRropas>();
	
	
	public Usuario(ArrayList <GuardaRopas> guardaRopas) {
		this.setGuardaRopas(guardaRopas);
	}

	public ArrayList<GuardaRopas> getGuardaRopas() {
		return guardaRopas;
	}


	public void setGuardaRopas(ArrayList<GuardaRopas> guardaRopas) {
		this.guardaRopas = guardaRopas;
	}
	
	public Atuendo sugerimePrendas(QueMePongo queMePongo) {
		return queMePongo.prendasSugeridasPara(this);
	}
}
