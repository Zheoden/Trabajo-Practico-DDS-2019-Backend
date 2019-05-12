package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import modelo.enums.*;

public class Guardarropas {
	ArrayList<Prenda> prendas = new ArrayList<Prenda>();

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
	
	public Prenda getRandomPrendaByCategoria(Categoria categoria) {
		Random rand = new Random();
		List<Prenda> aux = this.prendas.stream().filter( e -> e.isCategoria(categoria) ).collect(Collectors.toList());
		int n = rand.nextInt(aux.size());
		
		return aux.get(n);
	}
	
	public boolean existPrendaByCategoria(Categoria categoria) {
		return this.prendas.stream().anyMatch(e -> e.isCategoria(categoria));
	}
}
