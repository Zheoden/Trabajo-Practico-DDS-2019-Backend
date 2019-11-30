package test.modelo.clases;

import modelo.clases.*;
import modelo.dtos.Color;
import modelo.dtos.Material;
import modelo.dtos.TipoPrenda;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

import modelo.interfaces.Suscripcion;


public class EventoTest {
	
	Prenda camisaRojo = new Prenda("PR001", TipoPrenda.CAMISA, Color.ROJO);
	Prenda bufandaAzul = new Prenda("PR002", TipoPrenda.BUFANDA, Color.AZUL);
	Prenda pantalonVerde = new Prenda("PR003", TipoPrenda.PANTALON, Color.VERDE);
	ArrayList<Prenda> prendas1 = new ArrayList<Prenda>();
	Guardarropas guardaropa1 = new Guardarropas(prendas1, "Guardarropa1");
	ArrayList<Guardarropas> guardaRopas = new ArrayList<Guardarropas>();
	Suscripcion premium = new SuscripcionPremium();
	Calendar fecha1 = GregorianCalendar.getInstance();
	Calendar fecha2 = GregorianCalendar.getInstance();

	@Test
	@DisplayName("Avisa al Usuario de cada Evento el cual asiste")
	public void usuarioAsisteASusEventos() throws Exception {
    
		Usuario usuario1 = new Usuario("Rodrigo", "Nicola", guardaRopas, premium, "test@test.com", "12341234", 0);

		prendas1.add(camisaRojo);
		prendas1.add(bufandaAzul);
		prendas1.add(pantalonVerde);
		guardaRopas.add(guardaropa1);
		fecha1.set(2019, 10, 12);
		fecha1.set(Calendar.HOUR_OF_DAY, 07);
		fecha1.set(Calendar.MINUTE, 30);
		fecha2.set(2019, 06, 29);
		fecha2.set(Calendar.HOUR_OF_DAY, 21);
		fecha2.set(Calendar.MINUTE, 30);
		Evento irAlAlamo = new Evento("AlamosNigth", "Adrogue", fecha2);
		Evento developer = new Evento("Desarrollar software", "Azul", fecha1);
		usuario1.cargarEvento(irAlAlamo);
		usuario1.cargarEvento(developer);
		PrintStream out = mock(PrintStream.class);
		System.setOut(out);
		usuario1.irAEventos();
		verify(out).println(startsWith("Voy a AlamosNigth en Adrogue en la fecha 2019-07-29 21:30"));
		verify(out).println(startsWith("Voy a Desarrollar software en Azul en la fecha 2019-11-12 07:30"));
	}

	@Test
	@DisplayName("Verificar que el usuario no esta invitado al Evento")
	public void noEstasInvitadoAlEvento() throws Exception {
		prendas1.add(camisaRojo);
		prendas1.add(bufandaAzul);
		prendas1.add(pantalonVerde);
		guardaRopas.add(guardaropa1);

		Usuario usuario1 = new Usuario("Juan", "Verde", guardaRopas, premium, "test@test.com", "12341234", 0);
		fecha1.set(2019, 10, 12);
		fecha1.set(Calendar.HOUR_OF_DAY, 21);
		fecha1.set(Calendar.MINUTE, 30);
		Evento irAlAlamo = new Evento("Ir al alamo", "Lanus", fecha1);
		Evento developer = new Evento("Desarrollar software", "Azul", fecha1);
		usuario1.cargarEvento(developer);
		PrintStream out = mock(PrintStream.class);
		System.setOut(out);
		usuario1.irAElEvento(irAlAlamo);
		verify(out).println(startsWith("No estas invitado al evento " + irAlAlamo.getNombre()));
	}

	@Test
	public void recordatorioEventoFail() throws Exception {
		prendas1.add(camisaRojo);
		prendas1.add(bufandaAzul);
		prendas1.add(pantalonVerde);
		guardaRopas.add(guardaropa1);

		Usuario usuario1 = new Usuario("Matias", "People", guardaRopas, premium, "test@test.com", "12341234", 0);
    
		usuario1.setGuardarropas(guardaRopas);
		fecha1.set(2019, 8, 2);
		fecha1.set(Calendar.HOUR_OF_DAY, 14);
		fecha1.set(Calendar.MINUTE, 46);
		fecha1.set(Calendar.SECOND, 00);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.out.println(new PrintStream(outContent));
		Assert.assertEquals("", outContent.toString());
	}
	
//	@Test
//	// Avisa del evento un minuto antes en este caso
//	public void recordatorioEventoSucces() throws Exception {
//		prendas1.add(camisaRojo);
//		prendas1.add(bufandaAzul);
//		prendas1.add(pantalonVerde);
//		guardaRopas.add(guardaropa1);
//		Usuario usuario1 = new Usuario(guardaRopas, premium);
//		usuario1.setGuardaRopas(guardaRopas);
//		fecha1.set(2019, 7, 2);
//		fecha1.set(Calendar.HOUR_OF_DAY, 14);
//		fecha1.set(Calendar.MINUTE, 46);
//		fecha1.set(Calendar.SECOND, 00);
//		Evento irAlAlamo = new Evento("Ir al alamo", "Bueno Aires", fecha1);
//		PrintStream out = mock(PrintStream.class);
//		System.setOut(out);
//		usuario1.cargarEvento(irAlAlamo);
//		verify(out).println(startsWith("Recordatorio de evento Ir al alamo"));
//	}

	
	@Test
	@DisplayName("Test para verificar que se acepten los atuendos")
	public void aceptarAtuendos() {
		fecha1.set(2019, 7, 2);
		fecha1.set(Calendar.HOUR_OF_DAY, 14);
		fecha1.set(Calendar.MINUTE, 46);
		fecha1.set(Calendar.SECOND, 00);
		Evento irAlAlamo = new Evento("Ir al alamo", "Bueno Aires", fecha1);
		
		Prenda prenda1 = new Prenda("PR001", TipoPrenda.AROS, Color.AMARILLO);
		Prenda prenda2 = new Prenda("PR002", TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROSA);
		ArrayList<Prenda> prendas = new ArrayList<Prenda>();
		prendas.add(prenda1);
		prendas.add(prenda2);
		Atuendo atuendo = new Atuendo(prendas);
		
		irAlAlamo.aceptarAtuendo(atuendo);
		
		Assert.assertTrue(irAlAlamo.getAtuendosAceptados().contains(atuendo));
	}	
	
	
	@Test
	@DisplayName("Test para verificar que se rechacen los atuendos")
	public void rechazarAtuendos() {
		fecha1.set(2019, 7, 2);
		fecha1.set(Calendar.HOUR_OF_DAY, 14);
		fecha1.set(Calendar.MINUTE, 46);
		fecha1.set(Calendar.SECOND, 00);
		Evento irAlAlamo = new Evento("Ir al alamo", "Bueno Aires", fecha1);
		
		Prenda prenda1 = new Prenda("PR001", TipoPrenda.AROS, Color.AMARILLO);
		Prenda prenda2 = new Prenda("PR002",TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROSA);
		ArrayList<Prenda> prendas = new ArrayList<Prenda>();
		prendas.add(prenda1);
		prendas.add(prenda2);
		Atuendo atuendo = new Atuendo(prendas);
		
		irAlAlamo.rechazarAtuendo(atuendo);
		
		Assert.assertFalse(irAlAlamo.getAtuendosAceptados().contains(atuendo));
	}	
	
	@Test
	@DisplayName("Test para verificar que se deshagan las operaciones sobre los atuendos")
	public void deshacerAtuendos() {
		fecha1.set(2019, 7, 2);
		fecha1.set(Calendar.HOUR_OF_DAY, 14);
		fecha1.set(Calendar.MINUTE, 46);
		fecha1.set(Calendar.SECOND, 00);
		Evento irAlAlamo = new Evento("Ir al alamo", "Bueno Aires", fecha1);
		
		Prenda prenda1 = new Prenda("PR001", TipoPrenda.AROS, Color.AMARILLO);
		Prenda prenda2 = new Prenda("PR002", TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROSA);
		ArrayList<Prenda> prendas = new ArrayList<Prenda>();
		prendas.add(prenda1);
		prendas.add(prenda2);
		Atuendo atuendo = new Atuendo(prendas);
		
		Prenda prenda3 = new Prenda("PR001", TipoPrenda.BERMUDAS, Color.AMARILLO);
		Prenda prenda4 = new Prenda("PR002", TipoPrenda.BUZO, Material.ALGODON, Color.ROSA);
		Prenda prenda5 = new Prenda("PR003", TipoPrenda.AROS, Material.ALGODON, Color.ROSA);
		ArrayList<Prenda> prendas2 = new ArrayList<Prenda>();
		prendas2.add(prenda3);
		prendas2.add(prenda4);
		prendas2.add(prenda5);
		Atuendo atuendo2 = new Atuendo(prendas2);		
		
		Prenda prenda6 = new Prenda("PR006", TipoPrenda.CAMPERA, Color.AZUL);
		ArrayList<Prenda> prendas3 = new ArrayList<Prenda>();
		prendas3.add(prenda6);
		Atuendo atuendo3 = new Atuendo(prendas3);		
	
		irAlAlamo.aceptarAtuendo(atuendo);	
		irAlAlamo.rechazarAtuendo(atuendo2);
		irAlAlamo.aceptarAtuendo(atuendo3);
		irAlAlamo.deshacer();
		
		Assert.assertFalse(irAlAlamo.getAtuendosAceptados().contains(atuendo3));
		Assert.assertFalse(irAlAlamo.getAtuendosAceptados().contains(atuendo2));
		Assert.assertTrue(irAlAlamo.getAtuendosAceptados().contains(atuendo));
	}



}
