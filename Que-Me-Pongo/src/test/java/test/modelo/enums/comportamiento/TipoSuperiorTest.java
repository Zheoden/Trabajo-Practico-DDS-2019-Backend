package test.modelo.enums.comportamiento;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.enums.Categoria;
import modelo.enums.Material;
import modelo.enums.comportamiento.TipoSuperior;
import modelo.interfaces.TipoPrenda;

@DisplayName("Tests para los Atuendos")
public class TipoSuperiorTest {
	TipoPrenda buzo = TipoSuperior.BUZO;
	TipoPrenda camisa = TipoSuperior.CAMISA;
	TipoPrenda campera = TipoSuperior.CAMPERA;
	TipoPrenda remeracorta = TipoSuperior.REMERACORTA;
	TipoPrenda remeralarga = TipoSuperior.REMERALARGA;
	TipoPrenda sweater = TipoSuperior.SWEATER;
	
	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void categoria() {
		Assert.assertEquals(buzo.categoria(), Categoria.PARTE_SUPERIOR);
		Assert.assertEquals(camisa.categoria(), Categoria.PARTE_SUPERIOR);
		Assert.assertEquals(campera.categoria(), Categoria.PARTE_SUPERIOR);
		Assert.assertEquals(remeracorta.categoria(), Categoria.PARTE_SUPERIOR);
		Assert.assertEquals(remeralarga.categoria(), Categoria.PARTE_SUPERIOR);
		Assert.assertEquals(sweater.categoria(), Categoria.PARTE_SUPERIOR);
	}

	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void nivelDeCapa() {
		Assert.assertEquals(buzo.nivelDeCapa(), 1);
		Assert.assertEquals(camisa.nivelDeCapa(), 0);
		Assert.assertEquals(campera.nivelDeCapa(), 2);
		Assert.assertEquals(remeracorta.nivelDeCapa(), 0);
		Assert.assertEquals(remeralarga.nivelDeCapa(), 0);
		Assert.assertEquals(sweater.nivelDeCapa(), 1);
	}

	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void esMaterialValido() {
		Assert.assertTrue(buzo.esMaterialValido(Material.LINO));
		Assert.assertTrue(buzo.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(buzo.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(buzo.esMaterialValido(Material.POLAR));		
		Assert.assertFalse(buzo.esMaterialValido(Material.CUERO));
		Assert.assertFalse(buzo.esMaterialValido(Material.GABARDINA));
		Assert.assertFalse(buzo.esMaterialValido(Material.JEAN));		
		Assert.assertFalse(buzo.esMaterialValido(Material.SEDA));
		Assert.assertFalse(buzo.esMaterialValido(Material.TERCIOPELO));
		
		Assert.assertTrue(camisa.esMaterialValido(Material.ALGODON));
		Assert.assertTrue(camisa.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(camisa.esMaterialValido(Material.JEAN));
		Assert.assertTrue(camisa.esMaterialValido(Material.LINO));
		Assert.assertTrue(camisa.esMaterialValido(Material.OXFORD));
		Assert.assertFalse(camisa.esMaterialValido(Material.CUERO));
		Assert.assertTrue(camisa.esMaterialValido(Material.SEDA));
		Assert.assertFalse(camisa.esMaterialValido(Material.LYCRA));		
		Assert.assertFalse(camisa.esMaterialValido(Material.POLAR));		
		Assert.assertFalse(camisa.esMaterialValido(Material.TERCIOPELO));
		
		Assert.assertTrue(campera.esMaterialValido(Material.ALGODON));
		Assert.assertTrue(campera.esMaterialValido(Material.CUERO));
		Assert.assertTrue(campera.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(campera.esMaterialValido(Material.JEAN));
		Assert.assertTrue(campera.esMaterialValido(Material.LINO));
		Assert.assertTrue(campera.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(campera.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(campera.esMaterialValido(Material.POLAR));
		Assert.assertTrue(campera.esMaterialValido(Material.SEDA));
		Assert.assertTrue(campera.esMaterialValido(Material.TERCIOPELO));
		
		Assert.assertTrue(remeracorta.esMaterialValido(Material.ALGODON));
		Assert.assertTrue(remeracorta.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(remeracorta.esMaterialValido(Material.LINO));
		Assert.assertTrue(remeracorta.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(remeracorta.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(remeracorta.esMaterialValido(Material.POLAR));
		Assert.assertTrue(remeracorta.esMaterialValido(Material.SEDA));
		Assert.assertFalse(remeracorta.esMaterialValido(Material.CUERO));		
		Assert.assertFalse(remeracorta.esMaterialValido(Material.JEAN));		
		Assert.assertFalse(remeracorta.esMaterialValido(Material.TERCIOPELO));

		Assert.assertTrue(remeralarga.esMaterialValido(Material.ALGODON));
		Assert.assertTrue(remeralarga.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(remeralarga.esMaterialValido(Material.LINO));
		Assert.assertTrue(remeralarga.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(remeralarga.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(remeralarga.esMaterialValido(Material.POLAR));
		Assert.assertTrue(remeralarga.esMaterialValido(Material.SEDA));
        Assert.assertFalse(remeralarga.esMaterialValido(Material.CUERO));		
		Assert.assertFalse(remeralarga.esMaterialValido(Material.JEAN));		
		Assert.assertFalse(remeralarga.esMaterialValido(Material.TERCIOPELO));

		Assert.assertTrue(sweater.esMaterialValido(Material.ALGODON));
		Assert.assertTrue(sweater.esMaterialValido(Material.LINO));
		Assert.assertTrue(sweater.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(sweater.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(sweater.esMaterialValido(Material.POLAR));		
        Assert.assertFalse(sweater.esMaterialValido(Material.CUERO));
		Assert.assertFalse(sweater.esMaterialValido(Material.GABARDINA));
		Assert.assertFalse(sweater.esMaterialValido(Material.JEAN));		
		Assert.assertFalse(sweater.esMaterialValido(Material.SEDA));
		Assert.assertFalse(sweater.esMaterialValido(Material.TERCIOPELO));
	}
	
	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void nivelDeAbrigo() {
		Assert.assertEquals(buzo.nivelDeAbrigo(), 3);
		Assert.assertEquals(camisa.nivelDeAbrigo(), 2);
		Assert.assertEquals(campera.nivelDeAbrigo(), 5);
		Assert.assertEquals(remeracorta.nivelDeAbrigo(), 1);
		Assert.assertEquals(remeralarga.nivelDeAbrigo(), 2);
		Assert.assertEquals(sweater.nivelDeAbrigo(), 3);
	}
}