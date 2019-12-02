package test.modelo.clases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.clases.Atuendo;
import modelo.clases.Evento;
import modelo.clases.Prenda;
import modelo.dtos.Color;
import modelo.dtos.Material;
import modelo.dtos.TipoPrenda;

@DisplayName("Tests para los Atuendos")
public class AtuendoTest {

	ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	
	Prenda prenda1 = new Prenda("PR001", TipoPrenda.AROS, Color.AMARILLO);
	Prenda prenda2 = new Prenda("PR002", TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROSA);
	Prenda prenda3 = new Prenda("PR003", TipoPrenda.ZAPATOS, Material.CUERO, Color.BEIGE, Color.NEGRO);
	Prenda prenda4 = new Prenda("PR004", TipoPrenda.REMERALARGA, Material.ALGODON, Color.ROJO, Color.AZUL);
	Prenda prenda5 = new Prenda("PR005", TipoPrenda.SWEATER, Material.OXFORD, Color.AZUL, Color.CAQUI);
	Prenda prenda6 = new Prenda("PR006", TipoPrenda.PANTALONLARGO, Material.JEAN, Color.AZUL, Color.CELESTE);
	Prenda prenda7 = new Prenda("PR007", TipoPrenda.BUFANDA, Material.LINO, Color.NEGRO, Color.GRIS);
	
	@Test
	@DisplayName("Test para verificar la creacion de un Atuendo")
	public void atuendoCreadoCorrectamente() {
		Atuendo atuendo = new Atuendo(new ArrayList<Prenda>());
		Assert.assertEquals(atuendo.getClass(), Atuendo.class);
	}

	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void agregarPrendasAlAtuendo() {
	
		prendas.add(prenda1);
		prendas.add(prenda2);
		Atuendo atuendo = new Atuendo(prendas);
		Assert.assertEquals(atuendo.getPrendas(), prendas);
	}
	
	@Test
	@DisplayName("Test para verificar que el Atuendo generado pertenece al Evento en cuestion")
	public void corroborarAtuendoGeneradoParaEvento() {
		
		Calendar fechaEvento = GregorianCalendar.getInstance();
		fechaEvento.set(2019, 25, 12);
		fechaEvento.set(Calendar.HOUR_OF_DAY, 16);
		fechaEvento.set(Calendar.MINUTE, 40);
		
		Evento eventoNuevo = new Evento("Ir a Taz", "Costa Salguero",fechaEvento);
		
		prendas.add(prenda1);
		prendas.add(prenda4);
		prendas.add(prenda6);
		prendas.add(prenda7);
		Atuendo atuendo = new Atuendo(prendas);
		
		eventoNuevo.aceptarAtuendo(atuendo);
		atuendo.setEvento(eventoNuevo);
		
		Assert.assertTrue(atuendo.getEvento().equals(eventoNuevo));
		Assert.assertTrue(eventoNuevo.getAtuendosAceptados().get(0).equals(atuendo));
		
	}
	
	@Test
	@DisplayName("Test para verificar que estan en uso las Prendas en el Atuendo")
	public void modificarEstadoDePrendasDeUnAtuendo() {
		
		prendas.add(prenda3);
		prendas.add(prenda4);
		prendas.add(prenda5);
		prendas.add(prenda6);
		prendas.add(prenda7);
		
		Atuendo atuendo = new Atuendo(prendas);
		atuendo.modificarEstadoDePrendas(true);	
		Assert.assertFalse(atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEnUso().equals(false)));
	}
	
	@Test
	@DisplayName("Test para verificar que se acepta el Atuendo y sus Prendas estan en uso")
	public void aceptarAtuendoGenerado() {
		
		prendas.add(prenda1);
		prendas.add(prenda2);
		prendas.add(prenda3);
		prendas.add(prenda6);
		
		Atuendo atuendo = new Atuendo(prendas);
		atuendo.aceptar();
		
		Assert.assertTrue(atuendo.getPrendas().get(3).getEnUso());
		Assert.assertTrue(atuendo.getAceptado());	
	}
	
	@Test
	@DisplayName("Test para verificar que se rechaza el Atuendo y sus Prendas no estan en uso")
	public void rechazarAtuendoGenerado() {
		
		prendas.add(prenda1);
		prendas.add(prenda2);
		prendas.add(prenda4);
		prendas.add(prenda3);
		prendas.add(prenda6);
		prendas.add(prenda7);
		
		Atuendo atuendo = new Atuendo(prendas);
		atuendo.rechazar();
		
		Assert.assertFalse(atuendo.getPrendas().get(5).getEnUso());
		Assert.assertFalse(atuendo.getAceptado());	
	}	
}
