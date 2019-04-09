package modelo;

import java.util.ArrayList;
import java.util.List;

public class GuardaRropas {
	private ArrayList<Prenda> prendas = new ArrayList<Prenda>();

	public GuardaRropas(ArrayList<Prenda> prendas) {
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
