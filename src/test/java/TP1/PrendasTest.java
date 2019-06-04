package TP1;

import org.junit.Assert;
import org.junit.Test;

import modelo.enums.*;
import modelo.Prenda;

public class PrendasTest {

	@Test
	public void validarPrendasConMismoColorPrimarioYSecundario() {
		Prenda prenda = new Prenda(Tipo.PANTALON, Tela.JEAN, Color.AMARILLO, Color.AMARILLO);
		Assert.assertEquals(prenda.getColorPrimario(), Color.AMARILLO);
		Assert.assertEquals(prenda.getTela(), Tela.JEAN);
		Assert.assertEquals(prenda.Categoria(), Categoria.PARTEINFERIOR);
		Assert.assertEquals(prenda.getColorSecundario(), null);
	}

	@Test
	public void constructorPrenda() {
		Prenda prenda = new Prenda(Tipo.REMERACORTA, Tela.ALGODON, Color.AZUL, Color.ROJO);
		Assert.assertEquals(prenda.getColorPrimario(), Color.AZUL);
		Assert.assertEquals(prenda.getColorSecundario(), Color.ROJO);
		Assert.assertEquals(prenda.getTela(), Tela.ALGODON);
		Assert.assertEquals(prenda.Categoria(), Categoria.PARTESUPERIOR);
	}
}
