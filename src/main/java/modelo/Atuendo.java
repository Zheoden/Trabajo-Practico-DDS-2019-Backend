package modelo;

import java.util.ArrayList;

public class Atuendo {
	
	ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	
	public Atuendo (ArrayList<Prenda> prendas) {
		this.prendas = prendas;
	}	
	
	public ArrayList<Prenda> getPrendas() {
		return prendas;
	}
	
	public void addPrenda(Prenda unaPrenda) {
		this.prendas.add(unaPrenda);
	}
}
