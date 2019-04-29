package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import modelo.enums.*;

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
	
	public boolean verificarGuardarropas(Categoria categoria) {
		return this.guardarropas.stream().anyMatch(e -> e.existPrendaByType(categoria) );
	}
	
	public Atuendo sugerirAtuendo() throws Exception {
		Atuendo atuendo = new Atuendo(new ArrayList<Prenda>());
				
		if( this.verificarGuardarropas(Categoria.PARTEINFERIOR) &&
			this.verificarGuardarropas(Categoria.PARTESUPERIOR) &&
			this.verificarGuardarropas(Categoria.CALZADO)) {
			
			atuendo.addPrenda(this.getRandomPrendaByType(Categoria.PARTEINFERIOR));
			atuendo.addPrenda(this.getRandomPrendaByType(Categoria.PARTESUPERIOR));
			atuendo.addPrenda(this.getRandomPrendaByType(Categoria.CALZADO));
			
		} else {
			throw new Exception("El usuario no contiene prendas como para recomendar un atuendo");
		}
		
		return atuendo ;
	}
	
	public Guardarropas getGuardarropasConPrenda(Categoria categoria) {
		Random rand = new Random();
		List<Guardarropas> aux = this.guardarropas.stream().filter( e -> e.existPrendaByType(categoria) ).collect(Collectors.toList());
		int n = rand.nextInt(aux.size());
		
		return aux.get(n);
	}
	
	public Prenda getRandomPrendaByType(Categoria categoria) {
		return this.getGuardarropasConPrenda(categoria).getRandomPrendaByType(categoria);
	}
}
