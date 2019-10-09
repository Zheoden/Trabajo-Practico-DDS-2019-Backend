package test.modelo.persistencia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
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
    //Repositorios
	UsuarioRepository userRepo = new UsuarioRepository();
	PrendaRepository prendaRepo = new PrendaRepository();
	AtuendoRepository atuendoRepo = new AtuendoRepository();
	GuardarropaRepository guardarropasRepo = new GuardarropaRepository();
	EventoRepository eventoRepo = new EventoRepository();
	
	//Creacion de prendas
	Prenda prenda1 = new Prenda(TipoPrenda.CAMISA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda2 = new Prenda(TipoPrenda.CAMPERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda3 = new Prenda(TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda4 = new Prenda(TipoPrenda.BERMUDAS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda5 = new Prenda(TipoPrenda.CALZAS, Material.LYCRA, Color.ROJO, Color.BLANCO);
	Prenda prenda6 = new Prenda(TipoPrenda.PANTALON, Material.ALGODON, Color.ROJO, Color.BLANCO);
	
	//Lista de prendas
	ArrayList<Prenda> listaDePrendas1 = new ArrayList<Prenda>();
	ArrayList<Prenda> listaDePrendas2 = new ArrayList<Prenda>();
	
	//Creacion de guardarropas
	Guardarropas guardaRopas1 = new Guardarropas(listaDePrendas1);
	Guardarropas guardaRopas2 = new Guardarropas(listaDePrendas2);
	
	//Lista de guardarropas
	ArrayList<Guardarropas> listaGuardarropas1 = new ArrayList<Guardarropas>();
	ArrayList<Guardarropas> listaGuardarropas2 = new ArrayList<Guardarropas>();

	//Creacion de suscripciones
	Suscripcion subs = new SuscripcionPremium();
	Suscripcion subs2 = new SuscripcionGratuita();
	
	//Creacion de usuarios
	Usuario user1 = new Usuario(listaGuardarropas1, subs, "test@test.com", "12341234110", 0);
	Usuario user2 = new Usuario(listaGuardarropas2, subs2, "test2@test.com", "1122112209", 0);
	
	//Creacion de eventos
	Calendar fecha1 = GregorianCalendar.getInstance();
	Calendar fecha2 = GregorianCalendar.getInstance();
	Evento irAlAlamo = new Evento("AlamosNigth", "Adrogue", fecha2);
	Evento developer = new Evento("Desarrollar software", "Azul", fecha1);
	Evento poolParty = new Evento("pool party", "Azul", fecha1);
	Evento prueba = new Evento("prueba", "Azul", fecha1);

	@Before
	public void setUp() {
		listaDePrendas1.add(prenda1);
		listaDePrendas1.add(prenda2);
		listaDePrendas1.add(prenda3);

		listaDePrendas2.add(prenda4);
		listaDePrendas2.add(prenda5);
		listaDePrendas2.add(prenda6);
		listaDePrendas2.add(prenda1);

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
		user2.setGuardaRopas(listaGuardarropas1);
		
		//Sin esto como esta estructurado los tests.
		//Al ejecutar los que hay, se persisten los objetos
		//como x = objeto * unidadtests
		
		List<Usuario> usuarios = userRepo.all();
		if (usuarios.isEmpty()) {
		userRepo.persist(user1);
		userRepo.persist(user2);
		}
	}

	@Test
	@DisplayName("Usuario crea guardarropas y los persiste")
	public void guardarropasPersistidosPorElUsuario() {
		Optional<Usuario> user = userRepo.find("pepeCirco");
		List<Guardarropas> guardarropas1 = guardarropasRepo.findByUser(user.get().getUsername());
		Assert.assertEquals(user.get().getUsername(), "pepeCirco");
		Assert.assertEquals(guardarropas1.size(), 1);
	}


}
