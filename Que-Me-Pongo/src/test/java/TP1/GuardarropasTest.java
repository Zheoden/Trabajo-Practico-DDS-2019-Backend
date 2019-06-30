package TP1;

import java.util.ArrayList;

import modelo.clases.*;
import modelo.enums.*;

import org.junit.Assert;
import org.junit.Test;

public class GuardarropasTest {
	
	@Test
	public void getPrenda() {
		Prenda prenda = new Prenda(Tipo.REMERACORTA, Material.ALGODON, Color.AZUL);
		Prenda prenda1 = new Prenda(Tipo.PANTALON, Material.ALGODON, Color.AZUL);
		Prenda prenda2 = new Prenda(Tipo.ZAPATILLAS, Material.ALGODON, Color.AZUL);
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
		Prenda prenda = new Prenda(Tipo.REMERACORTA, Material.ALGODON, Color.AZUL);
		Prenda prenda1 = new Prenda(Tipo.PANTALON, Material.ALGODON, Color.AZUL);
		Prenda prenda2 = new Prenda(Tipo.ZAPATILLAS, Material.ALGODON, Color.AZUL);
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		Guardarropas guardaRopa = new Guardarropas(prendas);
		
		Assert.assertEquals(guardaRopa.existPrendaByCategoria(Categoria.PARTESUPERIOR), true);
		Assert.assertEquals(guardaRopa.existPrendaByCategoria(Categoria.PARTEINFERIOR), true);
		Assert.assertEquals(guardaRopa.existPrendaByCategoria(Categoria.CALZADO), true);
	}
	
	@Test
	public void agregarPrendaConImagenAlGuardarropa() {
		
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		Imagen objetoImagen = new Imagen();
		Prenda prenda = new Prenda(Tipo.ZAPATILLAS, Material.CUERO, Color.ROJO, Color.BLANCO);
		prenda.setDireccionImagen("ImgPrendas/zapas.jpg");
		prendas.add(prenda);
		Guardarropas unGuardarropa = new Guardarropas(prendas);	
		objetoImagen.normalizarImagen(unGuardarropa.getPrendas().get(0).getDireccionImagen());
		Assert.assertEquals(objetoImagen.getImagenRenderizada().getHeight(), 800);
	}

}
