package TP1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import modelo.Guardarropas;
import modelo.Prenda;
import modelo.Usuario;

public class PrendasTest {

	@Test
	public void obtenerCategoriaDeUnTipo() {
	
		Prenda prenda = new Prenda(Prenda.Tipo.AROS, Prenda.Color.AMARILLO);		
		Assert.assertEquals(prenda.Categoria(), Prenda.Categoria.ACCESORIO);
		
	}
	
	@Test
	public void obtenerTipodeTela() {
		
		Prenda prenda = new Prenda(Prenda.Tipo.REMERACORTA,Prenda.Tela.ALGODON, Prenda.Color.ROSA);		
		Assert.assertEquals(prenda.getTela(), Prenda.Tela.ALGODON);
		
		
	}
	
	
	@Test
	public void obtenerColorPrimarioDeLaPrenda() {
		
		Prenda prenda = new Prenda(Prenda.Tipo.PANTALON,Prenda.Tela.JEAN, Prenda.Color.AZUL);		
		Assert.assertEquals(prenda.getColorPrimario(), Prenda.Color.AZUL);
		
		
	}
	
	@Test
	public void obtenerColorSecundarioDeLaPrenda() {
		
		Prenda prenda = new Prenda(Prenda.Tipo.CAMISA,Prenda.Tela.LINO, Prenda.Color.ROSA, Prenda.Color.BLANCO);		
		Assert.assertEquals(prenda.getColorSecundario(), Prenda.Color.BLANCO);
				
	}
	
	
}

