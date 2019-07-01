package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import modelo.enums.*;

public class Usuario {
	
	Suscripcion suscripcion;
	ArrayList<Guardarropas> guardarropas = new ArrayList<Guardarropas>();
	
	public Suscripcion getSuscripcion() {
		return suscripcion;
	}
	
	public void setSuscripcion(Suscripcion unaSuscripcion) {
		suscripcion = unaSuscripcion;
	}
	
	public boolean listaDeGuardarropasValida(ArrayList<Guardarropas> guardaRopas) {
		return guardaRopas.stream().allMatch(unGuardarropa -> unGuardarropa.tamanioGuardarropas() <= suscripcion.cantidadPrendasPermitidas());
	}
	
	public Usuario(ArrayList <Guardarropas> guardaRopas, Suscripcion unaSuscripcion) throws Exception  {
		this.setGuardaRopas(guardaRopas);
		this.setSuscripcion(unaSuscripcion);
	}

	public ArrayList<Guardarropas> getGuardaRopas() {
		return guardarropas;
	}

	public void setGuardaRopas(ArrayList<Guardarropas> guardaRopas) throws Exception {
		if(this.listaDeGuardarropasValida(guardaRopas)) {
		this.guardarropas = guardaRopas; 
		}
		else {
			throw new Exception("La lista ingresada no cumple con los requisitos permitidos por la Suscripcion del usuario");
		}
	}
	
	public boolean verificarGuardarropas(Categoria categoria) {
		return this.guardarropas.stream().anyMatch(e -> e.existPrendaByCategoria(categoria) );
	}
	
	public Atuendo sugerirAtuendo(ArrayList<Categoria> categorias) throws Exception {
		Atuendo atuendo = new Atuendo(new ArrayList<Prenda>());
			
		if(categorias.size() > 0 && categorias.stream().allMatch(e -> this.verificarGuardarropas(e) )) {
			
			categorias.forEach(e -> atuendo.addPrenda(this.getRandomPrendaByType(e)) );
			
		} else {
			throw new Exception("El usuario no contiene prendas como para recomendar un atuendo");
		}
		
		return atuendo ;
	}
	
	public Guardarropas getGuardarropasConPrenda(Categoria categoria) {
		Random rand = new Random();
		List<Guardarropas> aux = this.guardarropas.stream().filter( e -> e.existPrendaByCategoria(categoria) ).collect(Collectors.toList());
		int n = rand.nextInt(aux.size());
		
		return aux.get(n);
	}
	
	public Prenda getRandomPrendaByType(Categoria categoria) {
		return this.getGuardarropasConPrenda(categoria).getRandomPrendaByCategoria(categoria);
	}
}
