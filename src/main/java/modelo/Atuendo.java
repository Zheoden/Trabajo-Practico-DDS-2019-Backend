package modelo;

import java.util.ArrayList;

public class Atuendo {
	
	ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	
	public Atuendo (ArrayList<Prenda> ListaDePrendas) {
		this.prendas = ListaDePrendas;
	}	
	
	public ArrayList<Prenda> getPrendas() {
		return prendas;
	}

}
