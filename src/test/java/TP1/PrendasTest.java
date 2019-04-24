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
	public void constructorPrenda() {

		Prenda prenda = new Prenda(Prenda.Tipo.REMERACORTA, Prenda.Tela.ALGODON, Prenda.Color.AZUL, Prenda.Color.ROJO);
		Assert.assertEquals(prenda.getColorPrimario(), Prenda.Color.AZUL);
		Assert.assertEquals(prenda.getColorSecundario(), Prenda.Color.ROJO);
		Assert.assertEquals(prenda.getTela(), Prenda.Tela.ALGODON);

	}
}
