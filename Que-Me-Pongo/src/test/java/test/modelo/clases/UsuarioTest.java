package test.modelo.clases;

import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

import modelo.clases.AdministrarProveedores;
import modelo.clases.Atuendo;
import modelo.clases.Evento;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.SuscripcionGratuita;
import modelo.clases.SuscripcionPremium;
import modelo.clases.Usuario;
import modelo.dtos.Color;
import modelo.dtos.Material;
import modelo.dtos.TipoPrenda;
import modelo.interfaces.Suscripcion;

@DisplayName("Tests para los Usuarios")
public class UsuarioTest {
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
	Prenda prenda14 = new Prenda(TipoPrenda.ZAPATILLAS, Material.CUERO, Color.AZUL, Color.ROJO);
	Prenda prenda15 = new Prenda(TipoPrenda.ZAPATOSDETACON, Material.CUERO, Color.MARRON, Color.NEGRO);
	Prenda prenda16 = new Prenda(TipoPrenda.MEDIAS, Material.POLAR, Color.VERDE, Color.AMARILLO);

	Prenda prenda11 = new Prenda(TipoPrenda.ANTEOJOS, Material.CUERO, Color.ROJO, Color.BLANCO);
	Prenda prenda12 = new Prenda(TipoPrenda.BUFANDA, Material.POLAR, Color.AZUL, Color.BLANCO);
	Prenda prenda13 = new Prenda(TipoPrenda.GUANTES, Material.POLAR, Color.NEGRO, Color.GRIS);
	Prenda prenda17 = new Prenda(TipoPrenda.BUFANDA, Material.LINO, Color.NEGRO, Color.BLANCO);
	
	ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	ArrayList<Prenda> prendas1 = new ArrayList<Prenda>();

	@Test
	@DisplayName("Verificar la creacion de un usuario")
	public void crearUsuarioCorrectamente() {
		Guardarropas guardaRopa1 = new Guardarropas(new ArrayList<Prenda>());
		Guardarropas guardaRopa2 = new Guardarropas(new ArrayList<Prenda>());
		ArrayList<Guardarropas> guardaRopas = new ArrayList<Guardarropas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);
		Suscripcion subs = new SuscripcionPremium();
    
		Usuario pepe = new Usuario(guardaRopas, subs, "test@test.com", "12341234", 0);
    
		Assert.assertEquals(pepe.getClass(), Usuario.class);
		Assert.assertEquals(pepe.getGuardaRopas(), guardaRopas);
		Assert.assertEquals(pepe.getSuscripcion(), subs);
	}

	@Test
	@DisplayName("Solicitar a todos los guardarropas todos los conjuntos que pueden armar y unificarlos")
	public void todosPosiblesAtuendosPorGuardarropa() {
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		prendas.add(prenda5);
		prendas.add(prenda6);
		prendas.add(prenda10);
		prendas.add(prenda11);

		prendas1.add(prenda4);
		prendas1.add(prenda3);
		prendas1.add(prenda7);
		prendas1.add(prenda8);
		
		prendas1.add(prenda10);
		prendas1.add(prenda11);

		AdministrarProveedores admin = Mockito.mock(AdministrarProveedores.class);
		Mockito.when(admin.obtenerTemperaturaActual()).thenReturn(22.0);
		
		Guardarropas guardaRopa1 = new Guardarropas(prendas, admin);
		Guardarropas guardaRopa2 = new Guardarropas(prendas1, admin);
		ArrayList<Guardarropas> guardaRopas = new ArrayList<Guardarropas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);

		Usuario pepe = new Usuario(guardaRopas, new SuscripcionPremium(), "test@test.com", "12341234", 0);

		List<Atuendo> atuendos = pepe.todosPosiblesAtuendosPorGuardarropaParaAhora();
		Assert.assertEquals(atuendos.size(), 4);
	}

	@Test
	@DisplayName("Agregar mas prendas de la permitida por la suscripcion del usuario")
	public void agregarPrendaAGuardaRopa() {
		Guardarropas guardaRopa3 = new Guardarropas(new ArrayList<Prenda>());
		guardaRopa3.addPrenda(prenda1);
		guardaRopa3.addPrenda(prenda2);
		guardaRopa3.addPrenda(prenda3);
		guardaRopa3.addPrenda(prenda4);
		guardaRopa3.addPrenda(prenda5);

		Guardarropas guardaRopa4 = new Guardarropas(new ArrayList<Prenda>());
		guardaRopa4.addPrenda(prenda6);
		guardaRopa4.addPrenda(prenda7);
		guardaRopa4.addPrenda(prenda8);

		ArrayList<Guardarropas> ropero = new ArrayList<Guardarropas>();
		ropero.add(guardaRopa3);
		ropero.add(guardaRopa4);

		SuscripcionGratuita subs2 = new SuscripcionGratuita();

		Usuario santi = new Usuario(ropero, subs2, "test@test.com", "12341234", 0);

		PrintStream out = mock(PrintStream.class);
		System.setOut(out);
		santi.agregarPrendaAGuardaRopas(prenda9, guardaRopa3);
		verify(out).println(startsWith(
				"El guardaRopas posee la cantidad maxima de prendas permitidas por la suscripcion del ususario"));
	}

	@Test
	@DisplayName("La cantidad permitida por la suscripcionGratuita da false")
	public void suscripcionGratuita() {
		SuscripcionGratuita subs3 = new SuscripcionGratuita();
		Assert.assertEquals(subs3.cantidadPrendasPermitidas(5), false);
	}

	@Test
	@DisplayName("Agregar menos prendas de la permitida por la suscripcion del usuario")
	public void agregarPrendaAGuardaRopa2() {
		Guardarropas guardaRopa3 = new Guardarropas(new ArrayList<Prenda>());
		guardaRopa3.addPrenda(prenda1);
		guardaRopa3.addPrenda(prenda2);
		guardaRopa3.addPrenda(prenda3);
		guardaRopa3.addPrenda(prenda4);

		Guardarropas guardaRopa4 = new Guardarropas(new ArrayList<Prenda>());
		guardaRopa4.addPrenda(prenda6);
		guardaRopa4.addPrenda(prenda7);
		guardaRopa4.addPrenda(prenda8);

		ArrayList<Guardarropas> ropero2 = new ArrayList<Guardarropas>();
		ropero2.add(guardaRopa3);
		ropero2.add(guardaRopa4);

		SuscripcionGratuita subs4 = new SuscripcionGratuita();

		Usuario santi = new Usuario(ropero2, subs4, "test@test.com", "12341234", 0);
    
		santi.agregarPrendaAGuardaRopas(prenda5, guardaRopa3);

		Assert.assertEquals(guardaRopa3.laPrendaEstaEnElGuardaRopa(prenda5), true);
	}
	
	@Test
	@DisplayName("Dos usuarios agregan prendas al guardaropas")
	public void agregarPrendaAGuardaRopaCompartido() {
		Guardarropas guardaRopa3 = new Guardarropas(new ArrayList<Prenda>());
		guardaRopa3.addPrenda(prenda1);
		guardaRopa3.addPrenda(prenda2);
		guardaRopa3.addPrenda(prenda3);
		guardaRopa3.addPrenda(prenda4);

		ArrayList<Guardarropas> ropero2 = new ArrayList<Guardarropas>();
		ropero2.add(guardaRopa3);
		
		Usuario santi = new Usuario(ropero2, new SuscripcionPremium(), "test@test.com", "12341234", 0);
		Usuario fede = new Usuario(ropero2, new SuscripcionPremium(), "test@test.com", "12341234", 0);
		
		santi.agregarPrendaAGuardaRopas(prenda5, guardaRopa3);
		fede.agregarPrendaAGuardaRopas(prenda6, guardaRopa3);
		
		Assert.assertEquals(guardaRopa3.laPrendaEstaEnElGuardaRopa(prenda5), true);
		Assert.assertEquals(guardaRopa3.laPrendaEstaEnElGuardaRopa(prenda6), true);
	}
	
	@Test
	@DisplayName("Solicitar a todos los eventos todos los conjuntos que fueron aceptados y unificarlos")
	public void todosPosiblesAtuendosPorEvento() {
		Evento trabajo = new Evento("Ir a trabajar", "a la Ofi", GregorianCalendar.getInstance());
		
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		prendas.add(prenda5);
		prendas.add(prenda6);
		prendas.add(prenda10);
		prendas.add(prenda11);

		prendas1.add(prenda4);
		prendas1.add(prenda3);
		prendas1.add(prenda7);
		prendas1.add(prenda8);
		prendas1.add(prenda10);
		prendas1.add(prenda11);

		AdministrarProveedores admin = Mockito.mock(AdministrarProveedores.class);
		Mockito.when(admin.obtenerTemperatura(trabajo.getFechaEvento())).thenReturn(20.0);
		
		Guardarropas guardaRopa1 = new Guardarropas(prendas, admin);
		Guardarropas guardaRopa2 = new Guardarropas(prendas1, admin);
		ArrayList<Guardarropas> guardaRopas = new ArrayList<Guardarropas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);

		Usuario pepe = new Usuario(guardaRopas, new SuscripcionPremium(), "test@test.com", "12341234", 2);
		pepe.cargarEvento(trabajo);
		pepe.cargarEvento(trabajo);
		trabajo.setAtuendosAceptados(new ArrayList<Atuendo>(pepe.todosPosiblesAtuendosPorGuardarropaParaEvento(trabajo)));
		
		List<Atuendo> atuendos = pepe.todosLosAtuendosAceptados();
		Assert.assertEquals(atuendos.size(), 8);
	}
}