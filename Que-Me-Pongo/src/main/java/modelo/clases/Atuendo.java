package modelo.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.google.common.collect.Lists;

public class Atuendo {

	ArrayList<Prenda> prendas = new ArrayList<Prenda>();

	public Atuendo(ArrayList<Prenda> prendas) {
		this.prendas = prendas;
	}

	public Atuendo(Prenda superior, Prenda inferior, Prenda calzado, Prenda accesorio) {
		this.addPrenda(superior);
		this.addPrenda(inferior);
		this.addPrenda(calzado);
		this.addPrenda(accesorio);
	}

	public Atuendo(Set<Prenda> superior, Set<Prenda> inferior, Set<Prenda> calzado, Set<Prenda> accesorio) {
		this.addListPrenda(Lists.newArrayList(superior));
		this.addListPrenda(Lists.newArrayList(inferior));
		this.addListPrenda(Lists.newArrayList(calzado));
		this.addListPrenda(Lists.newArrayList(accesorio));
	}

	public ArrayList<Prenda> getPrendas() {
		return prendas;
	}

	public void addPrenda(Prenda unaPrenda) {
		this.prendas.add(unaPrenda);
	}

	public void addListPrenda(List<Prenda> prendas) {
		this.prendas.addAll(prendas);
	}
}
