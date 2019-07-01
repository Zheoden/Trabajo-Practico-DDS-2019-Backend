package test.modelo.enums.comportamiento;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.enums.Categoria;
import modelo.enums.Material;
import modelo.enums.comportamiento.TipoInferior;
import modelo.interfaces.TipoPrenda;


@DisplayName("Tests para los Atuendos")
public class TipoInferiorTest {
	TipoPrenda bermudas = TipoInferior.BERMUDAS;
	TipoPrenda calzas = TipoInferior.CALZAS;
	TipoPrenda pantalon = TipoInferior.PANTALON;
	TipoPrenda pollera = TipoInferior.POLLERA;
	TipoPrenda shorts = TipoInferior.SHORTS;
	
	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void categoria() {
		Assert.assertEquals(bermudas.categoria(), Categoria.PARTE_INFERIOR);
		Assert.assertEquals(calzas.categoria(), Categoria.PARTE_INFERIOR);
		Assert.assertEquals(pantalon.categoria(), Categoria.PARTE_INFERIOR);
		Assert.assertEquals(pollera.categoria(), Categoria.PARTE_INFERIOR);
		Assert.assertEquals(shorts.categoria(), Categoria.PARTE_INFERIOR);
	}

	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void nivelDeCapa() {
		Assert.assertEquals(bermudas.nivelDeCapa(), 0);
		Assert.assertEquals(calzas.nivelDeCapa(), 0);
		Assert.assertEquals(pantalon.nivelDeCapa(), 0);
		Assert.assertEquals(pollera.nivelDeCapa(), 0);
		Assert.assertEquals(shorts.nivelDeCapa(), 0);
	}

	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void esMaterialValido() {
		Assert.assertTrue(bermudas.esMaterialValido(Material.ALGODON));
		Assert.assertTrue(bermudas.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(bermudas.esMaterialValido(Material.JEAN));
		Assert.assertTrue(bermudas.esMaterialValido(Material.LINO));
		Assert.assertFalse(bermudas.esMaterialValido(Material.CUERO));		
		Assert.assertFalse(bermudas.esMaterialValido(Material.LYCRA));
		Assert.assertFalse(bermudas.esMaterialValido(Material.OXFORD));
		Assert.assertFalse(bermudas.esMaterialValido(Material.POLAR));
		Assert.assertFalse(bermudas.esMaterialValido(Material.SEDA));
		Assert.assertFalse(bermudas.esMaterialValido(Material.TERCIOPELO));
		
		Assert.assertTrue(calzas.esMaterialValido(Material.ALGODON));
		Assert.assertTrue(calzas.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(calzas.esMaterialValido(Material.JEAN));
		Assert.assertTrue(calzas.esMaterialValido(Material.LINO));
		Assert.assertTrue(calzas.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(calzas.esMaterialValido(Material.POLAR));		
		Assert.assertFalse(calzas.esMaterialValido(Material.CUERO));		
		Assert.assertFalse(calzas.esMaterialValido(Material.OXFORD));		
		Assert.assertFalse(calzas.esMaterialValido(Material.SEDA));
		Assert.assertFalse(calzas.esMaterialValido(Material.TERCIOPELO));
		
		Assert.assertTrue(pantalon.esMaterialValido(Material.CUERO));
		Assert.assertTrue(pantalon.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(pantalon.esMaterialValido(Material.JEAN));
		Assert.assertTrue(pantalon.esMaterialValido(Material.LINO));
		Assert.assertTrue(pantalon.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(pantalon.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(pantalon.esMaterialValido(Material.POLAR));
		Assert.assertTrue(pantalon.esMaterialValido(Material.SEDA));
		Assert.assertTrue(pantalon.esMaterialValido(Material.TERCIOPELO));

        Assert.assertTrue(pollera.esMaterialValido(Material.CUERO));
		Assert.assertTrue(pollera.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(pollera.esMaterialValido(Material.JEAN));
		Assert.assertTrue(pollera.esMaterialValido(Material.LINO));
		Assert.assertTrue(pollera.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(pollera.esMaterialValido(Material.SEDA));
		Assert.assertTrue(pollera.esMaterialValido(Material.TERCIOPELO));
		Assert.assertFalse(pollera.esMaterialValido(Material.LYCRA));
		Assert.assertFalse(pollera.esMaterialValido(Material.POLAR));
		
		Assert.assertTrue(shorts.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(shorts.esMaterialValido(Material.LINO));
		Assert.assertTrue(shorts.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(shorts.esMaterialValido(Material.OXFORD));
        Assert.assertFalse(shorts.esMaterialValido(Material.CUERO));		
		Assert.assertFalse(shorts.esMaterialValido(Material.JEAN));		
		Assert.assertFalse(shorts.esMaterialValido(Material.POLAR));
		Assert.assertFalse(shorts.esMaterialValido(Material.SEDA));
		Assert.assertFalse(shorts.esMaterialValido(Material.TERCIOPELO));
	}
	
	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void nivelDeAbrigo() {
		Assert.assertEquals(bermudas.nivelDeAbrigo(), 2);
		Assert.assertEquals(calzas.nivelDeAbrigo(), 0);
		Assert.assertEquals(pantalon.nivelDeAbrigo(), 0);
		Assert.assertEquals(pollera.nivelDeAbrigo(), 2);
		Assert.assertEquals(shorts.nivelDeAbrigo(), 2);
		
	}
}
