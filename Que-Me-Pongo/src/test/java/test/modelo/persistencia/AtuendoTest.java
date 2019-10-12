package test.modelo.persistencia;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import junit.framework.Assert;
import modelo.clases.AdministrarProveedores;
import modelo.clases.Atuendo;
import modelo.clases.Evento;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.SuscripcionPremium;
import modelo.clases.Usuario;
import modelo.dtos.Color;
import modelo.dtos.Material;
import modelo.dtos.TipoPrenda;
import repository.AtuendoRepository;
import repository.EventoRepository;
import repository.GuardarropaRepository;
import repository.PrendaRepository;
import repository.UsuarioRepository;

public class AtuendoTest {

	static UsuarioRepository userRepo = new UsuarioRepository();
	AtuendoRepository atuendoRepo = new AtuendoRepository();
	EventoRepository eventoRepo = new EventoRepository();
	
	Usuario pepe;
	static ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	static ArrayList<Prenda> prendas1 = new ArrayList<Prenda>();
	static Guardarropas guardaRopa1;
	static Guardarropas guardaRopa2;
	static ArrayList<Guardarropas> guardaRopas = new ArrayList<Guardarropas>();
	static Evento trabajo = new Evento("Ir a trabajar", "a la Ofi", GregorianCalendar.getInstance());
	static List<Atuendo> atuendosGenerados;
	
	static Prenda prenda1 = new Prenda(TipoPrenda.BUZO, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda2 = new Prenda(TipoPrenda.CAMISA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda3 = new Prenda(TipoPrenda.CAMPERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda4 = new Prenda(TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda5 = new Prenda(TipoPrenda.REMERALARGA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda6 = new Prenda(TipoPrenda.BERMUDAS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda7 = new Prenda(TipoPrenda.CALZAS, Material.LYCRA, Color.ROJO, Color.BLANCO);
	static Prenda prenda8 = new Prenda(TipoPrenda.PANTALON, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda9 = new Prenda(TipoPrenda.POLLERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda10 = new Prenda(TipoPrenda.OJOTAS, Material.CUERO, Color.ROJO, Color.BLANCO);
	static Prenda prenda11 = new Prenda(TipoPrenda.ANTEOJOS, Material.CUERO, Color.ROJO, Color.BLANCO);
	
	@BeforeClass
	public static void setUp() {
		
		prendas.add(prenda1);
		prendas.add(prenda2);
		prendas.add(prenda3);
		prendas.add(prenda6);
		prendas.add(prenda7);
		prendas.add(prenda10);
		prendas.add(prenda11);

		prendas1.add(prenda5);
		prendas1.add(prenda4);
		prendas1.add(prenda8);
		prendas1.add(prenda9);
		prendas1.add(prenda10);
		prendas1.add(prenda11);
		
		AdministrarProveedores admin = Mockito.mock(AdministrarProveedores.class);
		Mockito.when(admin.obtenerTemperatura(trabajo.getFechaEvento())).thenReturn(20.0);
		
		Guardarropas guardaRopa1 = new Guardarropas(prendas, admin);
		Guardarropas guardaRopa2 = new Guardarropas(prendas1, admin);
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);
		
		Usuario pepe = new Usuario(guardaRopas, new SuscripcionPremium(), "test@test.com", "12341234", 2);
		pepe.setUsername("pepe");
		pepe.setPassword("pepas");
		pepe.cargarEvento(trabajo);
		trabajo.setUsuario(pepe);
		atuendosGenerados = pepe.todosPosiblesAtuendosPorGuardarropaParaEvento(trabajo);
		atuendosGenerados.stream().forEach(atuendo -> atuendo.setEvento(trabajo));
		
		for (int i = 0; i < atuendosGenerados.size(); i++) {

			if (i % 2 == 0) {
				trabajo.aceptarAtuendo(atuendosGenerados.get(i));
			} else {
				trabajo.rechazarAtuendo(atuendosGenerados.get(i));
			}

		}
		
		Optional<Usuario> usuario = userRepo.find("pepe");
		if(!(usuario.isPresent())) {
			userRepo.persist(pepe);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		List<Atuendo> atuendosAceptadosPorEvento = atuendoRepo.findSugerenciasAceptadasParaEvento("Ir a trabajar");
		Assert.assertEquals(atuendosAceptadosPorEvento.size(), 2);
	}

}
