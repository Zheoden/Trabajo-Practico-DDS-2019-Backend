package TP1;

import java.util.ArrayList;

import modelo.clases.*;
import modelo.enums.*;
import modelo.enums.comportamiento.TipoCalzado;
import modelo.enums.comportamiento.TipoInferior;
import modelo.enums.comportamiento.TipoSuperior;

import org.junit.Assert;
import org.junit.Test;

public class GuardarropasTest {
	
	@Test
	public void getPrenda() {
		Prenda prenda = new Prenda(TipoSuperior.REMERACORTA, Material.ALGODON, Color.AZUL);
		Prenda prenda1 = new Prenda(TipoInferior.PANTALON, Material.ALGODON, Color.AZUL);
		Prenda prenda2 = new Prenda(TipoCalzado.ZAPATILLAS, Material.ALGODON, Color.AZUL);
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		Guardarropas guardaRopa = new Guardarropas(prendas);
		
		Assert.assertEquals(guardaRopa.getRandomPrendaByCategoria(Categoria.PARTE_SUPERIOR), prenda);
		Assert.assertEquals(guardaRopa.getRandomPrendaByCategoria(Categoria.PARTE_INFERIOR), prenda1);
		Assert.assertEquals(guardaRopa.getRandomPrendaByCategoria(Categoria.CALZADO), prenda2);
	}
	
	@Test
	public void existPrendaByCategoriaFalse() {
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		Guardarropas guardaRopa = new Guardarropas(prendas);
		Assert.assertEquals(guardaRopa.existPrendaByCategoria(Categoria.PARTE_SUPERIOR), false);
	}
	
	@Test
	public void existPrendaByCategoriaTrue() {
		Prenda prenda = new Prenda(TipoSuperior.REMERACORTA, Material.ALGODON, Color.AZUL);
		Prenda prenda1 = new Prenda(TipoInferior.PANTALON, Material.ALGODON, Color.AZUL);
		Prenda prenda2 = new Prenda(TipoCalzado.ZAPATILLAS, Material.ALGODON, Color.AZUL);
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		Guardarropas guardaRopa = new Guardarropas(prendas);
		
		Assert.assertEquals(guardaRopa.existPrendaByCategoria(Categoria.PARTE_SUPERIOR), true);
		Assert.assertEquals(guardaRopa.existPrendaByCategoria(Categoria.PARTE_INFERIOR), true);
		Assert.assertEquals(guardaRopa.existPrendaByCategoria(Categoria.CALZADO), true);
	}
	
	@Test
	public void agregarPrendaConImagenAlGuardarropa() {
		
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		Imagen objetoImagen = new Imagen();
		Prenda prenda = new Prenda(TipoCalzado.ZAPATILLAS, Material.CUERO, Color.ROJO, Color.BLANCO);
		prenda.setDireccionImagen("ImgPrendas/zapas.jpg");
		prendas.add(prenda);
		Guardarropas unGuardarropa = new Guardarropas(prendas);	
		objetoImagen.normalizarImagen(unGuardarropa.getPrendas().get(0).getDireccionImagen());
		Assert.assertEquals(objetoImagen.getImagenRenderizada().getHeight(), 800);
	}

}
