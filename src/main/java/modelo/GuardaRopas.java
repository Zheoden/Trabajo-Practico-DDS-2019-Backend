package modelo;

import java.util.ArrayList;
import java.util.List;

public class GuardaRopas {
	private ArrayList<Prenda> prendas = new ArrayList<Prenda>();


public void  setPrendas (ArrayList<Prenda> unaPrenda){
	prendas.addAll(unaPrenda);
	}


public ArrayList <Prenda> getPrendas(){
	return this.prendas;
}