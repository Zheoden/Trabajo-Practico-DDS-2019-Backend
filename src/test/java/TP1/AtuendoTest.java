package TP1;

import org.junit.Assert;
import org.junit.Test;

import modelo.Prenda;
import modelo.Usuario;
import modelo.Atuendo;

import java.util.ArrayList;

public class AtuendoTest {

	
	@Test
	public void atuendoCreadoCorrectamente() {
	
		Prenda prenda1 = new Prenda(Prenda.Tipo.AROS, Prenda.Color.AMARILLO);
		Prenda prenda2 = new Prenda(Prenda.Tipo.REMERACORTA,Prenda.Tela.ALGODON, Prenda.Color.ROSA);		
		ArrayList<Prenda> prendas = new ArrayList<Prenda>();
		prendas.add(prenda1);
		prendas.add(prenda2);
		Atuendo atuendo = new Atuendo(prendas);
		Assert.assertEquals(atuendo.getClass(), Atuendo.class);
		
	}
	
	
}
