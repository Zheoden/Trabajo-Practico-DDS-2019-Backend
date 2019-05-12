package TP1;

import java.util.ArrayList;

import modelo.*;
import modelo.enums.*;

import org.junit.Assert;
import org.junit.Test;

public class GuardarropasTest {
	
	@Test
	public void getPrenda() {
		Prenda prenda = new Prenda(Tipo.REMERACORTA, Tela.ALGODON, Color.AZUL);
		Prenda prenda1 = new Prenda(Tipo.PANTALON, Tela.ALGODON, Color.AZUL);
		Prenda prenda2 = new Prenda(Tipo.ZAPATILLAS, Tela.ALGODON, Color.AZUL);
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		Guardarropas guardaRopa = new Guardarropas(prendas);
		
		Assert.assertEquals(guardaRopa.getRandomPrendaByCategoria(Categoria.PARTESUPERIOR), prenda);
		Assert.assertEquals(guardaRopa.getRandomPrendaByCategoria(Categoria.PARTEINFERIOR), prenda1);
		Assert.assertEquals(guardaRopa.getRandomPrendaByCategoria(Categoria.CALZADO), prenda2);
	}
	
	@Test
	public void existPrendaByCategoriaFalse() {
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		Guardarropas guardaRopa = new Guardarropas(prendas);
		Assert.assertEquals(guardaRopa.existPrendaByCategoria(Categoria.PARTESUPERIOR), false);
	}
	
	@Test
	public void existPrendaByCategoriaTrue() {
		Prenda prenda = new Prenda(Tipo.REMERACORTA, Tela.ALGODON, Color.AZUL);
		Prenda prenda1 = new Prenda(Tipo.PANTALON, Tela.ALGODON, Color.AZUL);
		Prenda prenda2 = new Prenda(Tipo.ZAPATILLAS, Tela.ALGODON, Color.AZUL);
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		Guardarropas guardaRopa = new Guardarropas(prendas);
		
		Assert.assertEquals(guardaRopa.existPrendaByCategoria(Categoria.PARTESUPERIOR), true);
		Assert.assertEquals(guardaRopa.existPrendaByCategoria(Categoria.PARTEINFERIOR), true);
		Assert.assertEquals(guardaRopa.existPrendaByCategoria(Categoria.CALZADO), true);
	}

}
