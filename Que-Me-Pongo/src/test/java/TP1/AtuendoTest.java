package TP1;


import org.junit.Assert;
import org.junit.Test;

import modelo.Prenda;
import modelo.Atuendo;
import modelo.enums.*;

import java.util.ArrayList;

public class AtuendoTest {

	@Test
	public void atuendoCreadoCorrectamente() {
		Atuendo atuendo = new Atuendo(new ArrayList<Prenda>());
		Assert.assertEquals(atuendo.getClass(), Atuendo.class);
	}
	
	
	@Test
	public void agregarPrendasAlAtuendo() {
		Prenda prenda1 = new Prenda(Tipo.AROS, Color.AMARILLO);
		Prenda prenda2 = new Prenda(Tipo.REMERACORTA,Tela.ALGODON, Color.ROSA);		
		ArrayList<Prenda> prendas = new ArrayList<Prenda>();
		prendas.add(prenda1);
		prendas.add(prenda2);
		Atuendo atuendo = new Atuendo(prendas);
		Assert.assertEquals(atuendo.getPrendas(), prendas);
	}
}
