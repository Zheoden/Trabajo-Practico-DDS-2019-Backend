package modelo;

import java.util.ArrayList;

public class Atuendo {
	
	ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	
	public Atuendo (ArrayList<Prenda> _prendas) {
		prendas = _prendas;
	}	
	
	public ArrayList<Prenda> getPrendas() {
		return prendas;
	}

	// no valido el atuendo. Eso lo vamos a hacer en el algoritmo para generar atuendos
	// tampoco hago genero setter ya que no veo sentido generar un atuendo sin prendas. A lo sumo luego vamos a tener funciones para agregar o sacar de la lista
	
}
