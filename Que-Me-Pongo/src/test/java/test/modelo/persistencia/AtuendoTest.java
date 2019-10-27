package test.modelo.persistencia;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.AfterClass;
import org.junit.Assert;
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
import repository.UsuarioRepository;

public class AtuendoTest {

	static UsuarioRepository userRepo = new UsuarioRepository();
	AtuendoRepository atuendoRepo = new AtuendoRepository();
	EventoRepository eventoRepo = new EventoRepository();

	static ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	static ArrayList<Prenda> prendas1 = new ArrayList<Prenda>();
	static Guardarropas guardaRopa1;
	static Guardarropas guardaRopa2;
	static ArrayList<Guardarropas> guardaRopas = new ArrayList<Guardarropas>();
	static Evento trabajo = new Evento("Ir a trabajar", "a la Ofi", GregorianCalendar.getInstance());
	static List<Atuendo> atuendosGenerados;
	static Usuario pepe = new Usuario(guardaRopas, new SuscripcionPremium(), "test@test.com", "12341234", 2);
	
	static Prenda prenda1 = new Prenda("PR001", TipoPrenda.BUZO, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda2 = new Prenda("PR002", TipoPrenda.CAMISA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda3 = new Prenda("PR003", TipoPrenda.CAMPERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda4 = new Prenda("PR004", TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda5 = new Prenda("PR005", TipoPrenda.REMERALARGA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda6 = new Prenda("PR006", TipoPrenda.BERMUDAS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda7 = new Prenda("PR007", TipoPrenda.CALZAS, Material.LYCRA, Color.ROJO, Color.BLANCO);
	static Prenda prenda8 = new Prenda("PR008", TipoPrenda.PANTALON, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda9 = new Prenda("PR009", TipoPrenda.POLLERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda10 = new Prenda("PR0010", TipoPrenda.OJOTAS, Material.CUERO, Color.ROJO, Color.BLANCO);
	static Prenda prenda11 = new Prenda("PR0011", TipoPrenda.ANTEOJOS, Material.CUERO, Color.ROJO, Color.BLANCO);

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
		
		Guardarropas guardaRopa1 = new Guardarropas(prendas, admin, "Guardarropa1");
		Guardarropas guardaRopa2 = new Guardarropas(prendas1, admin, "Guardarropa2");

		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);

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

			userRepo.persist(pepe);
	}

	@Test
	public void test() {
		Usuario usuarioEncontrado = userRepo.find("pepe").get();
		List<Atuendo> atuendosAceptadosPorEvento = atuendoRepo.findSugerenciasAceptadasParaEvento("Ir a trabajar", usuarioEncontrado.getUsername());
		Assert.assertEquals(atuendosAceptadosPorEvento.size(), 2);
	}
	
	@AfterClass
	public static void clearSetUp() {
		userRepo.delete(pepe);
	}
}
