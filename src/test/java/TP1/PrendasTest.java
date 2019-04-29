package TP1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import modelo.enums.*;
import modelo.Guardarropas;
import modelo.Prenda;
import modelo.Usuario;

public class PrendasTest {

	@Test
	public void obtenerCategoriaDeUnTipo() {
		Prenda prenda = new Prenda(Tipo.AROS, Color.AMARILLO);
		Assert.assertEquals(prenda.Categoria(), Categoria.ACCESORIO);
	}

	@Test
	public void constructorPrenda() {
		Prenda prenda = new Prenda(Tipo.REMERACORTA, Tela.ALGODON, Color.AZUL, Color.ROJO);
		Assert.assertEquals(prenda.getColorPrimario(), Color.AZUL);
		Assert.assertEquals(prenda.getColorSecundario(), Color.ROJO);
		Assert.assertEquals(prenda.getTela(), Tela.ALGODON);

	}
}
