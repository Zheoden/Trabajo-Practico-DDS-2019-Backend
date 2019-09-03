package test.modelo.clases;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import modelo.clases.AdministrarProveedores;
import modelo.clases.Atuendo;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.dtos.Color;
import modelo.dtos.Material;
import modelo.dtos.TipoPrenda;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

@DisplayName("Tests para los Guardarropas")
public class GuardarropasTest {
	ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	Prenda prenda = new Prenda(TipoPrenda.BUZO, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda1 = new Prenda(TipoPrenda.CAMISA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda2 = new Prenda(TipoPrenda.CAMPERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda3 = new Prenda(TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda4 = new Prenda(TipoPrenda.REMERALARGA, Material.ALGODON, Color.ROJO, Color.BLANCO);

	Prenda prenda5 = new Prenda(TipoPrenda.BERMUDAS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda6 = new Prenda(TipoPrenda.CALZAS, Material.LYCRA, Color.ROJO, Color.BLANCO);
	Prenda prenda7 = new Prenda(TipoPrenda.PANTALON, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda8 = new Prenda(TipoPrenda.POLLERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda9 = new Prenda(TipoPrenda.SHORTS, Material.ALGODON, Color.ROJO, Color.BLANCO);

	Prenda prenda10 = new Prenda(TipoPrenda.OJOTAS, Material.CUERO, Color.ROJO, Color.BLANCO);

	Prenda prenda11 = new Prenda(TipoPrenda.ANTEOJOS, Material.CUERO, Color.ROJO, Color.BLANCO);

	@Test
	@DisplayName("Tests para el Constructor")
	public void getPrenda() {
		Prenda prenda = new Prenda(TipoPrenda.REMERACORTA, Material.ALGODON, Color.AZUL);
		Prenda prenda1 = new Prenda(TipoPrenda.PANTALON, Material.ALGODON, Color.AZUL);
		Prenda prenda2 = new Prenda(TipoPrenda.ZAPATILLAS, Material.ALGODON, Color.AZUL);
		ArrayList<Prenda> prendas = new ArrayList<Prenda>();
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		Guardarropas guardaRopa = new Guardarropas(prendas);

		Assert.assertEquals(guardaRopa.getClass(), Guardarropas.class);
		Assert.assertEquals(guardaRopa.getPrendas(), prendas);
	}

	@Test
	@DisplayName("Tests para obtener prendas de partes superiores")
	public void obtenerPrendasSuperiores() {
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		prendas.add(prenda3);
		prendas.add(prenda4);

		Guardarropas unGuardarropa = new Guardarropas(prendas);
		Assert.assertEquals(unGuardarropa.obtenerPrendasSuperiores(),
				this.prendas.stream().collect(Collectors.toSet()));
	}

	@Test
	@DisplayName("Tests para obtener prendas de partes inferiores")
	public void obtenerPrendasInferiores() {
		prendas.add(prenda5);
		prendas.add(prenda6);
		prendas.add(prenda7);
		prendas.add(prenda8);
		prendas.add(prenda9);

		Guardarropas unGuardarropa = new Guardarropas(prendas);
		Assert.assertEquals(unGuardarropa.obtenerPrendasInferiores(),
				this.prendas.stream().collect(Collectors.toSet()));
	}

	@Test
	@DisplayName("Tests para obtener prendas de partes calzados")
	public void obtenerCalzados() {
		prendas.add(prenda10);

		Guardarropas unGuardarropa = new Guardarropas(prendas);
		Assert.assertEquals(unGuardarropa.obtenerCalzados(), this.prendas.stream().collect(Collectors.toSet()));
	}

	@Test
	@DisplayName("Tests para obtener prendas de partes accesorios")
	public void obtenerAccesorios() {
		prendas.add(prenda11);

		Guardarropas unGuardarropa = new Guardarropas(prendas);
		Assert.assertEquals(unGuardarropa.obtenerAccesorios(), this.prendas.stream().collect(Collectors.toSet()));
	}

	@Test
	@DisplayName("Tests para obtener un atuendo Random")
	public void obtenerAtuendoRandom() {
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		prendas.add(prenda3);
		prendas.add(prenda4);
		prendas.add(prenda5);
		prendas.add(prenda6);
		prendas.add(prenda7);
		prendas.add(prenda8);
		prendas.add(prenda9);
		prendas.add(prenda10);
		prendas.add(prenda11);

		Guardarropas unGuardarropa = new Guardarropas(prendas);
		List<Atuendo> atuendos = unGuardarropa.generarSugerencias(26.0, 0);
		Atuendo atuendoRandom = unGuardarropa.obtenerAtuendoRandom(atuendos);
		Assert.assertEquals(atuendoRandom.getClass(), Atuendo.class);
		Assert.assertEquals(atuendoRandom.getPrendas().size(), 4);
	}

	@Test
	@DisplayName("Tests para verificar que NO se puedan generar atuendos incompletos")
	public void generarSugerenciasVacio() {
		prendas.add(prenda5);
		prendas.add(prenda6);
		prendas.add(prenda7);
		prendas.add(prenda8);
		prendas.add(prenda9);
		prendas.add(prenda11);

		Guardarropas unGuardarropa = new Guardarropas(prendas);
		List<Atuendo> atuendos = unGuardarropa.generarSugerencias(26.0, 0);
		Assert.assertEquals(atuendos.size(), 0);
	}

	@Test
	@DisplayName("Tests para verificar que se puedan generar atuendos validos")
	public void generarSugerenciasLleno() {
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		prendas.add(prenda3);
		prendas.add(prenda4);
		prendas.add(prenda5);
		prendas.add(prenda6);
		prendas.add(prenda7);
		prendas.add(prenda8);
		prendas.add(prenda9);
		prendas.add(prenda10);
		prendas.add(prenda11);

		Guardarropas unGuardarropa = new Guardarropas(prendas);
		List<Atuendo> atuendos = unGuardarropa.generarSugerencias(26.0, 0);
		Assert.assertEquals(atuendos.size(), 15);
	}

	@Test
	@DisplayName("Tests para verificar que NO se puedan generar atuendos validos en base al clima actual")
	public void atuendosValidosParaAhoraVacio() {
		prendas.add(prenda3); // remera corta
		prendas.add(prenda4); // remera larga
		prendas.add(prenda5);// bermudas
		prendas.add(prenda10);
		prendas.add(prenda11);

		Guardarropas unGuardarropa = new Guardarropas(prendas);
		List<Atuendo> atuendos = unGuardarropa.atuendosValidosParaAhora(0);
		Assert.assertEquals(atuendos.size(), 0);
	}

	@Test
	@DisplayName("Tests para verificar que se puedan generar atuendos validos en base al clima actual")
	public void atuendosValidosParaAhoraLleno() {
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		prendas.add(prenda3);
		prendas.add(prenda4);
		prendas.add(prenda5);
		prendas.add(prenda6);
		prendas.add(prenda7);
		prendas.add(prenda8);
		prendas.add(prenda9);
		prendas.add(prenda10);
		prendas.add(prenda11);

		AdministrarProveedores a = Mockito.mock(AdministrarProveedores.class);
		Mockito.when(a.obtenerTemperaturaActual()).thenReturn(28.0);
		
		Guardarropas unGuardarropa = new Guardarropas(prendas, a);
		List<Atuendo> atuendos = unGuardarropa.atuendosValidosParaAhora(0);
		Assert.assertEquals(atuendos.size(), 15);
	}

	@Test
	@DisplayName("Tests para verificar los puntos de abrigo de un cierto conjunto de prendas")
	public void obtenerPuntosDeAbrigo() {
		prendas.add(prenda);
		prendas.add(prenda3);
		prendas.add(prenda6);
		prendas.add(prenda7);
		prendas.add(prenda10);
		prendas.add(prenda11);

		Guardarropas unGuardarropa = new Guardarropas(prendas);
		int sumatoriaPrendas = unGuardarropa.obtenerPuntosDeAbrigo(prendas.stream().collect(Collectors.toSet()));
		Assert.assertEquals(sumatoriaPrendas, 4);
	}

	@Test
	@DisplayName("Tests para verificar que ciertas NO prendas se puedan superponer")
	public void prendasTienenNivelesDeCapaValidosFalse() {
		prendas.add(prenda3);
		prendas.add(prenda4);

		Guardarropas unGuardarropa = new Guardarropas(prendas);
		boolean esValido = unGuardarropa
				.prendasTienenNivelesDeCapaValidos(prendas.stream().collect(Collectors.toSet()));
		Assert.assertFalse(esValido);
	}

	@Test
	@DisplayName("Tests para verificar que ciertas prendas se puedan superponer")
	public void prendasTienenNivelesDeCapaValidosTrue() {
		prendas.add(prenda);
		prendas.add(prenda4);

		Guardarropas unGuardarropa = new Guardarropas(prendas);
		boolean esValido = unGuardarropa
				.prendasTienenNivelesDeCapaValidos(prendas.stream().collect(Collectors.toSet()));
		Assert.assertTrue(esValido);
	}
}
