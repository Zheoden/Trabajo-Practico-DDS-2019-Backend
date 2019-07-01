package test.modelo.enums.comportamiento;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.enums.*;
import modelo.enums.comportamiento.TipoAccesorio;
import modelo.interfaces.TipoPrenda;

@DisplayName("Tests para los Accesorios")
public class TipoAccesorioTest {
	TipoPrenda anteojos = TipoAccesorio.ANTEOJOS;
	TipoPrenda aros = TipoAccesorio.AROS;
	TipoPrenda bufanda = TipoAccesorio.BUFANDA;
	TipoPrenda gorra = TipoAccesorio.GORRA;
	TipoPrenda collar = TipoAccesorio.COLLAR;
	TipoPrenda lentes = TipoAccesorio.LENTES;
	
	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void categoria() {
		Assert.assertEquals(anteojos.categoria(), Categoria.ACCESORIO);
		Assert.assertEquals(aros.categoria(), Categoria.ACCESORIO);
		Assert.assertEquals(bufanda.categoria(), Categoria.ACCESORIO);
		Assert.assertEquals(gorra.categoria(), Categoria.ACCESORIO);
		Assert.assertEquals(collar.categoria(), Categoria.ACCESORIO);
		Assert.assertEquals(lentes.categoria(), Categoria.ACCESORIO);
	}

	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void nivelDeCapa() {
		Assert.assertEquals(anteojos.nivelDeCapa(), 0);
		Assert.assertEquals(aros.nivelDeCapa(), 0);
		Assert.assertEquals(bufanda.nivelDeCapa(), 0);
		Assert.assertEquals(gorra.nivelDeCapa(), 0);
		Assert.assertEquals(collar.nivelDeCapa(), 0);
		Assert.assertEquals(lentes.nivelDeCapa(), 0);
	}

	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void esMaterialValido() {
		Assert.assertTrue(anteojos.esMaterialValido(Material.ALGODON));
		Assert.assertTrue(anteojos.esMaterialValido(Material.CUERO));
		Assert.assertTrue(anteojos.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(anteojos.esMaterialValido(Material.JEAN));
		Assert.assertTrue(anteojos.esMaterialValido(Material.LINO));
		Assert.assertTrue(anteojos.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(anteojos.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(anteojos.esMaterialValido(Material.POLAR));
		Assert.assertTrue(anteojos.esMaterialValido(Material.SEDA));
		Assert.assertTrue(anteojos.esMaterialValido(Material.TERCIOPELO));
		
		Assert.assertTrue(aros.esMaterialValido(Material.ALGODON));
		Assert.assertTrue(aros.esMaterialValido(Material.CUERO));
		Assert.assertTrue(aros.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(aros.esMaterialValido(Material.JEAN));
		Assert.assertTrue(aros.esMaterialValido(Material.LINO));
		Assert.assertTrue(aros.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(aros.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(aros.esMaterialValido(Material.POLAR));
		Assert.assertTrue(aros.esMaterialValido(Material.SEDA));
		Assert.assertTrue(aros.esMaterialValido(Material.TERCIOPELO));
		
		Assert.assertTrue(bufanda.esMaterialValido(Material.CUERO));
		Assert.assertTrue(bufanda.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(bufanda.esMaterialValido(Material.JEAN));
		Assert.assertTrue(bufanda.esMaterialValido(Material.LINO));
		Assert.assertTrue(bufanda.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(bufanda.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(bufanda.esMaterialValido(Material.POLAR));
		Assert.assertTrue(bufanda.esMaterialValido(Material.SEDA));
		Assert.assertTrue(bufanda.esMaterialValido(Material.TERCIOPELO));

        Assert.assertTrue(gorra.esMaterialValido(Material.CUERO));
		Assert.assertTrue(gorra.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(gorra.esMaterialValido(Material.JEAN));
		Assert.assertTrue(gorra.esMaterialValido(Material.LINO));
		Assert.assertTrue(gorra.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(gorra.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(gorra.esMaterialValido(Material.POLAR));
		Assert.assertTrue(gorra.esMaterialValido(Material.SEDA));
		Assert.assertTrue(gorra.esMaterialValido(Material.TERCIOPELO));

        Assert.assertTrue(collar.esMaterialValido(Material.CUERO));
		Assert.assertTrue(collar.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(collar.esMaterialValido(Material.JEAN));
		Assert.assertTrue(collar.esMaterialValido(Material.LINO));
		Assert.assertTrue(collar.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(collar.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(collar.esMaterialValido(Material.POLAR));
		Assert.assertTrue(collar.esMaterialValido(Material.SEDA));
		Assert.assertTrue(collar.esMaterialValido(Material.TERCIOPELO));

        Assert.assertTrue(lentes.esMaterialValido(Material.CUERO));
		Assert.assertTrue(lentes.esMaterialValido(Material.GABARDINA));
		Assert.assertTrue(lentes.esMaterialValido(Material.JEAN));
		Assert.assertTrue(lentes.esMaterialValido(Material.LINO));
		Assert.assertTrue(lentes.esMaterialValido(Material.LYCRA));
		Assert.assertTrue(lentes.esMaterialValido(Material.OXFORD));
		Assert.assertTrue(lentes.esMaterialValido(Material.POLAR));
		Assert.assertTrue(lentes.esMaterialValido(Material.SEDA));
		Assert.assertTrue(lentes.esMaterialValido(Material.TERCIOPELO));
	}
	
	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void nivelDeAbrigo() {
		Assert.assertEquals(anteojos.nivelDeAbrigo(), 0);
		Assert.assertEquals(aros.nivelDeAbrigo(), 0);
		Assert.assertEquals(bufanda.nivelDeAbrigo(), 0);
		Assert.assertEquals(gorra.nivelDeAbrigo(), 0);
		Assert.assertEquals(collar.nivelDeAbrigo(), 0);
		Assert.assertEquals(lentes.nivelDeAbrigo(), 0);
	}
}
