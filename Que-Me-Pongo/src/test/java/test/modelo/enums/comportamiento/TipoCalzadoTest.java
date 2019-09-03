package test.modelo.enums.comportamiento;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.dtos.Categoria;
import modelo.dtos.Material;
import modelo.dtos.TipoPrenda;

@DisplayName("Tests para los Atuendos")
public class TipoCalzadoTest {
	TipoPrenda zapatillas = TipoPrenda.ZAPATILLAS;
	TipoPrenda zapatos = TipoPrenda.ZAPATOS;
	TipoPrenda zapatosDeTacon = TipoPrenda.ZAPATOSDETACON;
	TipoPrenda ojotas = TipoPrenda.OJOTAS;

	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void categoria() {
		Assert.assertEquals(zapatillas.categoria(), Categoria.CALZADO);
		Assert.assertEquals(zapatos.categoria(), Categoria.CALZADO);
		Assert.assertEquals(zapatosDeTacon.categoria(), Categoria.CALZADO);
		Assert.assertEquals(ojotas.categoria(), Categoria.CALZADO);
	}

	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void nivelDeCapa() {
		Assert.assertEquals(zapatillas.nivelDeCapa(), 1);
		Assert.assertEquals(zapatos.nivelDeCapa(), 1);
		Assert.assertEquals(zapatosDeTacon.nivelDeCapa(), 1);
		Assert.assertEquals(ojotas.nivelDeCapa(), 0);
	}

	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void esMaterialValido() {
		Assert.assertTrue(zapatillas.esMaterialValido(Material.ALGODON));
		Assert.assertTrue(zapatillas.esMaterialValido(Material.CUERO));
		Assert.assertTrue(zapatillas.esMaterialValido(Material.JEAN));
		Assert.assertTrue(zapatillas.esMaterialValido(Material.LINO));
		Assert.assertFalse(zapatillas.esMaterialValido(Material.GABARDINA));
		Assert.assertFalse(zapatillas.esMaterialValido(Material.LYCRA));
		Assert.assertFalse(zapatillas.esMaterialValido(Material.OXFORD));
		Assert.assertFalse(zapatillas.esMaterialValido(Material.POLAR));
		Assert.assertFalse(zapatillas.esMaterialValido(Material.SEDA));
		Assert.assertFalse(zapatillas.esMaterialValido(Material.TERCIOPELO));

		Assert.assertTrue(zapatos.esMaterialValido(Material.CUERO));
		Assert.assertFalse(zapatos.esMaterialValido(Material.ALGODON));
		Assert.assertFalse(zapatos.esMaterialValido(Material.GABARDINA));
		Assert.assertFalse(zapatos.esMaterialValido(Material.JEAN));
		Assert.assertFalse(zapatos.esMaterialValido(Material.LINO));
		Assert.assertFalse(zapatos.esMaterialValido(Material.LYCRA));
		Assert.assertFalse(zapatos.esMaterialValido(Material.OXFORD));
		Assert.assertFalse(zapatos.esMaterialValido(Material.POLAR));
		Assert.assertFalse(zapatos.esMaterialValido(Material.SEDA));
		Assert.assertFalse(zapatos.esMaterialValido(Material.TERCIOPELO));

		Assert.assertTrue(zapatosDeTacon.esMaterialValido(Material.CUERO));
		Assert.assertFalse(zapatosDeTacon.esMaterialValido(Material.ALGODON));
		Assert.assertFalse(zapatosDeTacon.esMaterialValido(Material.GABARDINA));
		Assert.assertFalse(zapatosDeTacon.esMaterialValido(Material.JEAN));
		Assert.assertFalse(zapatosDeTacon.esMaterialValido(Material.LINO));
		Assert.assertFalse(zapatosDeTacon.esMaterialValido(Material.LYCRA));
		Assert.assertFalse(zapatosDeTacon.esMaterialValido(Material.OXFORD));
		Assert.assertFalse(zapatosDeTacon.esMaterialValido(Material.POLAR));
		Assert.assertFalse(zapatosDeTacon.esMaterialValido(Material.SEDA));
		Assert.assertFalse(zapatosDeTacon.esMaterialValido(Material.TERCIOPELO));

		Assert.assertTrue(ojotas.esMaterialValido(Material.CUERO));
		Assert.assertFalse(ojotas.esMaterialValido(Material.ALGODON));
		Assert.assertFalse(ojotas.esMaterialValido(Material.GABARDINA));
		Assert.assertFalse(ojotas.esMaterialValido(Material.JEAN));
		Assert.assertFalse(ojotas.esMaterialValido(Material.LINO));
		Assert.assertFalse(ojotas.esMaterialValido(Material.LYCRA));
		Assert.assertFalse(ojotas.esMaterialValido(Material.OXFORD));
		Assert.assertFalse(ojotas.esMaterialValido(Material.POLAR));
		Assert.assertFalse(ojotas.esMaterialValido(Material.SEDA));
		Assert.assertFalse(ojotas.esMaterialValido(Material.TERCIOPELO));
	}

	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void nivelDeAbrigo() {
		Assert.assertEquals(zapatillas.nivelDeAbrigo(), 0);
		Assert.assertEquals(zapatos.nivelDeAbrigo(), 0);
		Assert.assertEquals(zapatosDeTacon.nivelDeAbrigo(), 0);
		Assert.assertEquals(ojotas.nivelDeAbrigo(), 0);
	}

}
