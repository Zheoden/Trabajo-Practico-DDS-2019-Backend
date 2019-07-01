package TP2;
    import modelo.clases.*;
	import static org.junit.Assert.assertTrue;


	import java.io.IOException;
	import java.io.PrintStream;

	import static org.mockito.Matchers.startsWith;
	import static org.mockito.Mockito.mock;
	import static org.mockito.Mockito.verify;
	import java.util.ArrayList;
	import java.util.Calendar;
	import java.util.GregorianCalendar;
	import org.junit.*;
	import modelo.*;
	import modelo.enums.Color;
    import modelo.enums.comportamiento.TipoAccesorio;
import modelo.enums.comportamiento.TipoInferior;
import modelo.enums.comportamiento.TipoSuperior;

	public class EventoTest {
		
		@Test
		public void usuarioAsisteASusEventos() throws Exception
		{
		Prenda camisaRojo = new Prenda(TipoSuperior.CAMISA,Color.ROJO);
		Prenda  bufandaAzul = new Prenda(TipoAccesorio.BUFANDA,Color.AZUL);
		Prenda pantalonVerde = new Prenda(TipoInferior.PANTALON,Color.VERDE);
		ArrayList<Prenda> prendas1 = new ArrayList<Prenda>();
		prendas1.add(camisaRojo);
		prendas1.add(bufandaAzul);
		prendas1.add(pantalonVerde);
		Guardarropas guardaropa1 = new Guardarropas(prendas1);
		ArrayList <Guardarropas> guardaRopas = new ArrayList<Guardarropas>();
		guardaRopas.add(guardaropa1);
		Suscripcion premium = new SuscripcionPremium();
		Usuario usuario1 = new Usuario(guardaRopas, premium);
	    Calendar fecha1 = GregorianCalendar.getInstance();
	    fecha1.set(2019,10,12);
	    Calendar fecha2 = GregorianCalendar.getInstance();
	    fecha2.set(2019,06,29);
	    Ciudad ciudad1 = new Ciudad("Adrogue",new Double(22));
	    Ciudad ciudad3 = new Ciudad("Azul",new Double(16));
		Evento irAlAlamo = new Evento("AlamosNigth",ciudad1,fecha2);
		Evento developer = new Evento("Desarrollar software",ciudad3,fecha1);
		usuario1.cargarEvento(irAlAlamo);
		usuario1.cargarEvento(developer);
		PrintStream out = mock(PrintStream.class);
	    System.setOut(out);
	    usuario1.irAEventos();
	    verify(out).println(startsWith("Voy a AlamosNigth en Adrogue en la fecha 2019-07-28"));
	    verify(out).println(startsWith("Voy a Desarrollar software en Azul en la fecha 2019-11-11"));
		}


		@Test(expected = Exception.class)
		public void noEstasInvitadoAlEvento() throws Exception
		{
			Prenda camisaRojo = new Prenda(TipoSuperior.CAMISA,Color.ROJO);
			Prenda  bufandaAzul = new Prenda(TipoAccesorio.BUFANDA,Color.AZUL);
			Prenda pantalonVerde = new Prenda(TipoInferior.PANTALON,Color.VERDE);
			ArrayList<Prenda> prendas1 = new ArrayList<Prenda>();
			prendas1.add(camisaRojo);
			prendas1.add(bufandaAzul);
			prendas1.add(pantalonVerde);
			Guardarropas guardaropa1 = new Guardarropas(prendas1);
			ArrayList <Guardarropas> guardaRopas = new ArrayList<Guardarropas>();
			guardaRopas.add(guardaropa1);
			Suscripcion premium = new SuscripcionPremium();
			Usuario usuario1 = new Usuario(guardaRopas, premium);
		    Calendar fecha1 = GregorianCalendar.getInstance();
		    fecha1.set(2019,10,12);
		    Ciudad ciudad1 = new Ciudad("Capital Federal",new Double(30));
			Evento irAlAlamo = new Evento("Ir al alamo",ciudad1,fecha1);
			usuario1.irAElEvento(irAlAlamo);
		}
		
	}

