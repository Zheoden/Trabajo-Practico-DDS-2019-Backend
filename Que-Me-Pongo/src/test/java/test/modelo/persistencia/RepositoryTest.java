package test.modelo.persistencia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

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
import repository.AtuendoRepository;
import repository.EventoRepository;
import repository.GuardarropaRepository;
import repository.PrendaRepository;
import repository.UsuarioRepository;

public class RepositoryTest {
	// Repositorios
	static UsuarioRepository userRepo = new UsuarioRepository();
	PrendaRepository prendaRepo = new PrendaRepository();
	AtuendoRepository atuendoRepo = new AtuendoRepository();
	GuardarropaRepository guardarropasRepo = new GuardarropaRepository();
	EventoRepository eventoRepo = new EventoRepository();

	static Prenda prenda1 = new Prenda("PR020", TipoPrenda.CAMISA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda2 = new Prenda("PR021", TipoPrenda.CAMPERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda3 = new Prenda("PR022", TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda4 = new Prenda("PR023", TipoPrenda.BERMUDAS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda5 = new Prenda("PR024", TipoPrenda.CALZAS, Material.LYCRA, Color.ROJO, Color.BLANCO);
	static Prenda prenda6 = new Prenda("PR025", TipoPrenda.PANTALON, Material.ALGODON, Color.ROJO, Color.BLANCO);
	static Prenda prenda7 = new Prenda("PR026", TipoPrenda.CAMPERA, Material.ALGODON, Color.ROJO, Color.BLANCO);

	// Lista de prendas
	static ArrayList<Prenda> listaDePrendas1 = new ArrayList<Prenda>();
	static ArrayList<Prenda> listaDePrendas2 = new ArrayList<Prenda>();
	static ArrayList<Prenda> listaDePrendas3 = new ArrayList<Prenda>();

	// Creacion de guardarropas
	static Guardarropas guardaRopas1 = new Guardarropas(listaDePrendas1, "Guardarropa1");
	static Guardarropas guardaRopas2 = new Guardarropas(listaDePrendas2, "Guardarropa2");
	static Guardarropas guardaRopas3 = new Guardarropas(listaDePrendas3, "Guardarropa3");

	// Lista de guardarropas
	static ArrayList<Guardarropas> listaGuardarropas1 = new ArrayList<Guardarropas>();
	static ArrayList<Guardarropas> listaGuardarropas2 = new ArrayList<Guardarropas>();

	// Creacion de suscripciones
	static Suscripcion subs = new SuscripcionPremium();
	static Suscripcion subs2 = new SuscripcionGratuita();

	// Creacion de usuarios
	static Usuario user1 = new Usuario(listaGuardarropas1, subs, "test@test.com", "12341234110", 0);
	static Usuario user2 = new Usuario(listaGuardarropas2, subs2, "test2@test.com", "1122112209", 0);

	// Creacion de eventos
	static Calendar fecha1 = GregorianCalendar.getInstance();
	static Calendar fecha2 = GregorianCalendar.getInstance();
	static Evento irAlAlamo = new Evento("AlamosNigth", "Adrogue", fecha2);
	static Evento developer = new Evento("Desarrollar software", "Azul", fecha1);
	static Evento poolParty = new Evento("pool party", "Azul", fecha1);
	static Evento prueba = new Evento("prueba", "Azul", fecha1);

	@BeforeClass
	public static void setUp() {
		listaDePrendas1.add(prenda1);
		listaDePrendas1.add(prenda2);
		listaDePrendas1.add(prenda3);

		listaDePrendas2.add(prenda4);
		listaDePrendas2.add(prenda5);
		listaDePrendas2.add(prenda6);

		listaGuardarropas1.add(guardaRopas1);
		listaGuardarropas2.add(guardaRopas2);

		fecha1.set(2019, 10, 12);
		fecha1.set(Calendar.HOUR_OF_DAY, 07);
		fecha1.set(Calendar.MINUTE, 30);
		fecha2.set(2019, 06, 29);
		fecha2.set(Calendar.HOUR_OF_DAY, 21);
		fecha2.set(Calendar.MINUTE, 30);

		irAlAlamo.setUsuario(user1);
		developer.setUsuario(user1);
		poolParty.setUsuario(user1);
		prueba.setUsuario(user2);

		user1.cargarEvento(irAlAlamo);
		user1.cargarEvento(poolParty);
		user1.cargarEvento(developer);
		user2.cargarEvento(prueba);

		user1.setUsername("pepeCirco");
		user2.setUsername("mamaKondo");
		user1.setPassword("123");
		user2.setPassword("456");

		userRepo.persist(user1);
		userRepo.persist(user2);

	}

	@Test
	@DisplayName("Usuario crea guardarropas los persiste")
	public void A_guardarropasPersistidosPorElUsuario() {
		Optional<Usuario> user = userRepo.find("pepeCirco");
		List<Guardarropas> guardarropas1 = guardarropasRepo.findByUser(user.get().getUsername());
		Assert.assertEquals(guardarropas1.size(), 1);

	}
//
//	@Test
//	@DisplayName("Usuario agrega guardarropa y los persiste")
//	public void B_guardarropasNuevosPersistidosPorElUsuario() {
//		Optional<Usuario> user = userRepo.find("pepeCirco");
//		listaGuardarropas1.add(guardaRopas3);
//		user.get().setGuardarropas(listaGuardarropas1);
//		userRepo.beginTransaction();
//		userRepo.entityManager().flush();
//		userRepo.commitTransaction();
//		List<Guardarropas> guardarropas2 = guardarropasRepo.findByUser(user.get().getUsername());
//		Assert.assertEquals(guardarropas2.size(), 2);
//	}
//
//	@Test
//	@DisplayName("Verifica la carga de eventos")
//	public void verificarCargaDeEventos() {
//		Optional<Usuario> user = userRepo.findById(1);
//		Assert.assertEquals(user.get().getEventos().size(), 3);
//	}
//
//	@Test
//	@DisplayName("Eliminar eventos del calendario")
//	public void eliminarEventos() {
//		Optional<Usuario> user = userRepo.findById(1);
//		List<Evento> listaDeEventos = user.get().getEventos();
//		listaDeEventos.remove(1);
//		Assert.assertEquals(listaDeEventos.size(), 2);
//	}
//	
//	@AfterClass
//	public static void clearSetUp() {
//		userRepo.delete(user1);
//		userRepo.delete(user2);
//	}
}