package test.modelo.clases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

import modelo.clases.AdministrarProveedores;
import modelo.clases.Atuendo;
import modelo.clases.Evento;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.dtos.Color;
import modelo.dtos.Material;
import modelo.dtos.TipoPrenda;

@DisplayName("Tests para los Guardarropas")
public class GuardarropasTest {
	ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	Prenda prenda = new Prenda("PR001", TipoPrenda.BUZO, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda1 = new Prenda("PR002", TipoPrenda.CAMISA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda2 = new Prenda("PR003", TipoPrenda.CAMPERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda3 = new Prenda("PR004", TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda4 = new Prenda("PR005", TipoPrenda.REMERALARGA, Material.ALGODON, Color.ROJO, Color.BLANCO);

	Prenda prenda5 = new Prenda("PR006", TipoPrenda.BERMUDAS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda6 = new Prenda("PR007", TipoPrenda.CALZAS, Material.LYCRA, Color.ROJO, Color.BLANCO);
	Prenda prenda7 = new Prenda("PR008", TipoPrenda.PANTALON, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda8 = new Prenda("PR009", TipoPrenda.POLLERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda9 = new Prenda("PR0010", TipoPrenda.SHORTS, Material.ALGODON, Color.ROJO, Color.BLANCO);

	Prenda prenda10 = new Prenda("PR0011", TipoPrenda.OJOTAS, Material.CUERO, Color.ROJO, Color.BLANCO);

	Prenda prenda11 = new Prenda("PR0012", TipoPrenda.ANTEOJOS, Material.CUERO, Color.ROJO, Color.BLANCO);
	Prenda prenda12 = new Prenda("PR0013", TipoPrenda.ZAPATILLAS, Material.CUERO, Color.NEGRO, Color.ROJO);
	Prenda prenda13 = new Prenda("PR0014", TipoPrenda.ZAPATOS, Material.CUERO, Color.NEGRO, Color.MARRON);
	
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
		Guardarropas guardaRopa = new Guardarropas(prendas, "Guardarropa1");

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

		Guardarropas unGuardarropa = new Guardarropas(prendas, "Guardarropa2");
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

		Guardarropas unGuardarropa = new Guardarropas(prendas, "Guardarropa3");
		Assert.assertEquals(unGuardarropa.obtenerPrendasInferiores(),
				this.prendas.stream().collect(Collectors.toSet()));
	}

	@Test
	@DisplayName("Tests para obtener prendas de partes calzados")
	public void obtenerCalzados() {
		prendas.add(prenda10);

		Guardarropas unGuardarropa = new Guardarropas(prendas, "Guardarropa4");
		Assert.assertEquals(unGuardarropa.obtenerCalzados(), this.prendas.stream().collect(Collectors.toSet()));
	}

	@Test
	@DisplayName("Tests para obtener prendas de partes accesorios")
	public void obtenerAccesorios() {
		prendas.add(prenda11);

		Guardarropas unGuardarropa = new Guardarropas(prendas, "Guardarropa5");
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

		Guardarropas unGuardarropa = new Guardarropas(prendas, "Guardarropa6");
		List<Atuendo> atuendos = unGuardarropa.generarSugerencias(26.0, 0, null);
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

		Guardarropas unGuardarropa = new Guardarropas(prendas, "Guardarropa7");
		List<Atuendo> atuendos = unGuardarropa.generarSugerencias(26.0, 0, null);
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

		Guardarropas unGuardarropa = new Guardarropas(prendas, "Guardarropa8");
		List<Atuendo> atuendos = unGuardarropa.generarSugerencias(26.0, 0, null);
		Assert.assertEquals(atuendos.size(), 15);
	}

//	@Test
//	@DisplayName("Tests para verificar que NO se puedan generar atuendos validos en base al clima actual")
//	public void atuendosValidosParaAhoraVacio() {
//		prendas.add(prenda3); // remera corta
//		prendas.add(prenda4); // remera larga
//		prendas.add(prenda5); // bermudas
//		prendas.add(prenda10);
//		prendas.add(prenda11);
//
//		Guardarropas unGuardarropa = new Guardarropas(prendas, "Guardarropa9");
//		List<Atuendo> atuendos = unGuardarropa.atuendosValidosParaAhora(0);
//		Assert.assertEquals(atuendos.size(), 0);
//	}

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
		
		Guardarropas unGuardarropa = new Guardarropas(prendas, a, "Guardarropa10");
		List<Atuendo> atuendos = unGuardarropa.atuendosValidosParaAhora(0);
		Assert.assertEquals(atuendos.size(), 15);
	}
	
	@Test
	@DisplayName("Tests para verificar que el Atuendo generado pertenece al Evento")
	public void atuendosValidosParaEvento() {
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

		Calendar fecha = GregorianCalendar.getInstance();
		fecha.set(2019, 26, 10);
		fecha.set(Calendar.HOUR_OF_DAY, 22);
		fecha.set(Calendar.MINUTE, 15);
		Evento paloko = new Evento("Ir a Paloko", "Buenos Aires", fecha);
		
		AdministrarProveedores a = Mockito.mock(AdministrarProveedores.class);
		Mockito.when(a.obtenerTemperaturaActual()).thenReturn(23.0);
		
		Guardarropas unGuardarropa = new Guardarropas(prendas, a, "Guardarropa11");
		
		List<Atuendo> atuendosEvento = unGuardarropa.atuendosValidosParaEvento(paloko, 0);
		
		Assert.assertEquals(atuendosEvento.get(2).getEvento(), paloko);
	}
	
	@Test
	@DisplayName("Verificar que se generan combinaciones de Prendas")
	public void setDePrendasCombinadas() {
		
		prendas.add(prenda1);
		prendas.add(prenda2);
		prendas.add(prenda3);
		prendas.add(prenda5);
		prendas.add(prenda6);
		prendas.add(prenda8);
		prendas.add(prenda9);
		prendas.add(prenda10);
		
		AdministrarProveedores a = Mockito.mock(AdministrarProveedores.class);
		Mockito.when(a.obtenerTemperaturaActual()).thenReturn(19.0);
		System.out.println(a.obtenerTemperaturaActual());
		Guardarropas unGuardarropa = new Guardarropas(prendas, a, "Guardarropa12");
		Set<Prenda> setPrendas = new HashSet<>(prendas);
		Set<Set<Prenda>> combinacionesObtenidas = unGuardarropa.obtenerCombinacionesDePrenda(setPrendas,
				a.obtenerTemperaturaActual(), 0);
		
		Assert.assertEquals(combinacionesObtenidas.size(), 5);	
	}
	
	@Test
	@DisplayName("Tests para verificar que se puedan generar atuendos validos en base al clima actual y la sensibilidad del usuario caluriento")
	public void atuendosValidosParaAhoraLlenoConSensibilidadCaluriento() {
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
		Mockito.when(a.obtenerTemperaturaActual()).thenReturn(26.0);
		
		Guardarropas unGuardarropa = new Guardarropas(prendas, a, "Guardarropa13");
		List<Atuendo> atuendos = unGuardarropa.atuendosValidosParaAhora(2);
		Assert.assertEquals(atuendos.size(), 15);
	}

	@Test
	@DisplayName("Tests para verificar que se puedan generar atuendos validos en base al clima actual y la sensibilidad del usuario friolento")
	public void atuendosValidosParaAhoraLlenoConSensibilidadFriolento() {
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
		Mockito.when(a.obtenerTemperaturaActual()).thenReturn(30.0);
		
		Guardarropas unGuardarropa = new Guardarropas(prendas, a, "Guardarropa14");
		List<Atuendo> atuendos = unGuardarropa.atuendosValidosParaAhora(-2);
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

		Guardarropas unGuardarropa = new Guardarropas(prendas, "Guardarropa15");
		int sumatoriaPrendas = unGuardarropa.obtenerPuntosDeAbrigo(prendas.stream().collect(Collectors.toSet()));
		Assert.assertEquals(sumatoriaPrendas, 4);
	}

	@Test
	@DisplayName("Tests para verificar que ciertas NO prendas se puedan superponer")
	public void prendasTienenNivelesDeCapaValidosFalse() {
		prendas.add(prenda3);
		prendas.add(prenda4);

		Guardarropas unGuardarropa = new Guardarropas(prendas, "Guardarropa16");
		boolean esValido = unGuardarropa
				.prendasTienenNivelesDeCapaValidos(prendas.stream().collect(Collectors.toSet()));
		Assert.assertFalse(esValido);
	}

	@Test
	@DisplayName("Tests para verificar que ciertas prendas se puedan superponer")
	public void prendasTienenNivelesDeCapaValidosTrue() {
		prendas.add(prenda);
		prendas.add(prenda4);

		Guardarropas unGuardarropa = new Guardarropas(prendas, "Guardarropa17");
		boolean esValido = unGuardarropa
				.prendasTienenNivelesDeCapaValidos(prendas.stream().collect(Collectors.toSet()));
		Assert.assertTrue(esValido);
	}
	
	@Test
	@DisplayName("Verificar que la cantidad de combinaciones de Prenda no es vacio")
	public void seTienenCombinacionesNoVacias() {
		
		prendas.add(prenda2);
		prendas.add(prenda3);
		prendas.add(prenda4);
		prendas.add(prenda7);
		prendas.add(prenda9);
		prendas.add(prenda11);
		prendas.add(prenda12);
		
		Guardarropas unGuardarropa = new Guardarropas(prendas, "Guardarropa18");
		AdministrarProveedores a = Mockito.mock(AdministrarProveedores.class);
		Mockito.when(a.obtenerTemperaturaActual()).thenReturn(15.0);
		
		Set<Set<Prenda>> combinacionesCreadas = unGuardarropa.obtenerCombinacionesNoVacias(prendas.stream().collect(Collectors.toSet()), a.obtenerTemperaturaActual(), -3);
		Assert.assertTrue(!combinacionesCreadas.isEmpty());
	}
}