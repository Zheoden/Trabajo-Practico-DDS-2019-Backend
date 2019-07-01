package test.modelo.clases;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import modelo.clases.Atuendo;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.enums.*;
import modelo.enums.comportamiento.TipoAccesorio;
import modelo.enums.comportamiento.TipoCalzado;
import modelo.enums.comportamiento.TipoInferior;
import modelo.enums.comportamiento.TipoSuperior;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Tests para los Guardarropas")
public class GuardarropasTest {
	
	ArrayList <Prenda> prendas = new ArrayList <Prenda>();
	Prenda prenda = new Prenda(TipoSuperior.BUZO, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda1 = new Prenda(TipoSuperior.CAMISA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda2 = new Prenda(TipoSuperior.CAMPERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda3 = new Prenda(TipoSuperior.REMERACORTA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda4 = new Prenda(TipoSuperior.REMERALARGA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	
	Prenda prenda5 = new Prenda(TipoInferior.BERMUDAS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda6 = new Prenda(TipoInferior.CALZAS, Material.LYCRA, Color.ROJO, Color.BLANCO);
	Prenda prenda7 = new Prenda(TipoInferior.PANTALON, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda8 = new Prenda(TipoInferior.POLLERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda9 = new Prenda(TipoInferior.SHORTS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	
	Prenda prenda10 = new Prenda(TipoCalzado.OJOTAS, Material.CUERO, Color.ROJO, Color.BLANCO);

	Prenda prenda11 = new Prenda(TipoAccesorio.ANTEOJOS, Material.CUERO, Color.ROJO, Color.BLANCO);
	
	@Test
	@DisplayName("Tests para el Constructor")
	public void getPrenda() {
		Prenda prenda = new Prenda(TipoSuperior.REMERACORTA, Material.ALGODON, Color.AZUL);
		Prenda prenda1 = new Prenda(TipoInferior.PANTALON, Material.ALGODON, Color.AZUL);
		Prenda prenda2 = new Prenda(TipoCalzado.ZAPATILLAS, Material.ALGODON, Color.AZUL);
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
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
		Assert.assertEquals(unGuardarropa.obtenerPrendasSuperiores(),this.prendas.stream().collect(Collectors.toSet()));
	}
	
	@Test
	@DisplayName("Tests para obtener prendas de partes inferiores")
	public void obtenerPrendasInferiores() {

	}
	
	@Test
	@DisplayName("Tests para obtener prendas de partes calzados")
	public void obtenerCalzados() {
		
	}
	
	@Test
	@DisplayName("Tests para obtener prendas de partes accesorios")
	public void obtenerAccesorios() {
		
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
		List<Atuendo> atuendos = unGuardarropa.generarSugerencias(26.0);
		Atuendo atuendoRandom = unGuardarropa.obtenerAtuendoRandom(atuendos);
		Assert.assertEquals(atuendoRandom.getClass(), Atuendo.class );
		Assert.assertEquals(atuendoRandom.getPrendas().size(), 4);
	}
	
	@Test
	@DisplayName("Tests para verificar que NO se puedan generar atuendos validos")
	public void generarSugerenciasVacio() {
		
	}
	
	@Test
	@DisplayName("Tests para verificar que se puedan generar atuendos validos")
	public void generarSugerenciasLleno() {
		
	}
	
	@Test
	@DisplayName("Tests para verificar que NO se puedan generar atuendos validos en base al clima actual")
	public void atuendosValidosParaAhoraVacio() {
		
	}
	
	@Test
	@DisplayName("Tests para verificar que se puedan generar atuendos validos en base al clima actual")
	public void atuendosValidosParaAhoraLleno() {
		
	}
	
	@Test
	@DisplayName("Tests para verificar los puntos de abrigo de un cierto conjunto de prendas")
	public void obtenerPuntosDeAbrigo() {
		
	}
	
	@Test
	@DisplayName("Tests para verificar que ciertas NO prendas se puedan superponer")
	public void prendasTienenNivelesDeCapaValidosFalse() {
		
	}
	
	@Test
	@DisplayName("Tests para verificar que ciertas prendas se puedan superponer")
	public void prendasTienenNivelesDeCapaValidosTrue() {
		
	}
	
	@Test
	@DisplayName("ESTE TEST ES UN EJEMPLO, BORRAR DESPUES")
	public void testGuava() {
		
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
		List<Atuendo> atuendos = unGuardarropa.generarSugerencias(7.0);
		Assert.assertEquals(atuendos.size(), 4);
	}

}
