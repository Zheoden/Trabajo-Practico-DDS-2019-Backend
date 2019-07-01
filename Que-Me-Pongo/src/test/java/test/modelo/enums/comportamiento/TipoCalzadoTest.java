package test.modelo.enums.comportamiento;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.enums.Categoria;
import modelo.enums.Material;
import modelo.enums.comportamiento.TipoAccesorio;
import modelo.enums.comportamiento.TipoCalzado;
import modelo.interfaces.TipoPrenda;

@DisplayName("Tests para los Atuendos")
public class TipoCalzadoTest {
	TipoPrenda zapatillas = TipoCalzado.ZAPATILLAS;
	TipoPrenda zapatos = TipoCalzado.ZAPATOS;
	TipoPrenda zapatosDeTacon = TipoCalzado.ZAPATOSDETACON;
	TipoPrenda ojotas = TipoCalzado.OJOTAS;
	
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
		Assert.assertEquals(zapatillas.nivelDeCapa(), 0);
		Assert.assertEquals(zapatos.nivelDeCapa(), 0);
		Assert.assertEquals(zapatosDeTacon.nivelDeCapa(), 0);
		Assert.assertEquals(ojotas.nivelDeCapa(), 0);
	}

	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void esMaterialValido() {
		Assert.assertTrue(zapatillas.esMaterialValido(Material.ALGODON));
		Assert.assertTrue(zapatillas.esMaterialValido(Material.CUERO));
		Assert.assertTrue(zapatillas.esMaterialValido(Material.JEAN));
		Assert.assertTrue(zapatillas.esMaterialValido(Material.LINO));
		
		Assert.assertTrue(zapatos.esMaterialValido(Material.CUERO));
		
		Assert.assertTrue(zapatosDeTacon.esMaterialValido(Material.CUERO));

        Assert.assertTrue(ojotas.esMaterialValido(Material.CUERO));
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
