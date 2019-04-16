package modelo;

import java.util.ArrayList;
import java.util.List;

public class Guardarropas {
	private ArrayList<Prenda> prendas = new ArrayList<Prenda>();

	public Guardarropas(ArrayList<Prenda> prendas) {
		this.setPrendas(prendas);
	}

	public void addPrenda(Prenda unaPrenda) {
		this.prendas.add(unaPrenda);
	}

	public void setPrendas(ArrayList<Prenda> prendasNuevas) {
		this.prendas = prendasNuevas;
	}

	public ArrayList<Prenda> getPrendas() {
		return this.prendas;
	}
}
