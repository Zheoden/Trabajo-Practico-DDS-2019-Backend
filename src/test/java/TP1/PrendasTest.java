package TP1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import modelo.Guardarropas;
import modelo.Prenda;
import modelo.Usuario;

public class PrendasTest {
/*
	@Test
	public void crearUsuarioCorrectamente() {
		
		Guardarropas guardaRopa1 = new Guardarropas(new ArrayList<Prenda>());
		Guardarropas guardaRopa2 = new Guardarropas(new ArrayList<Prenda>());
		ArrayList <Guardarropas> guardaRopas = new ArrayList <Guardarropas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);
		Usuario pepe = new Usuario(guardaRopas);
		Assert.assertEquals(pepe.getClass(), Usuario.class);
	}
*/
	
//Duda con respecto a por qué rompe. Me falta agregar algo más?
	@Test
	public void obtenerCategoriaDeUnTipo() {
	
		Prenda prenda = new Prenda(Prenda.Tipo.AROS, Prenda.Color.AMARILLO);		
		Assert.assertEquals(prenda.getTipo(), Prenda.Categoria.ACCESORIO);
		
	}
	
	@Test
	public void obtenerTipodeTela() {
		
		Prenda prenda = new Prenda(Prenda.Tipo.REMERACORTA,Prenda.Tela.ALGODON, Prenda.Color.ROSA);		
		Assert.assertEquals(prenda.getTela(), Prenda.Tela.ALGODON);
		
		
	}
	
	
	
}

