package test.modelo.persistencia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import org.junit.After;
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
import repository.UsuarioRepository;

public class RepositoryTest {

	UsuarioRepository userRepo = new UsuarioRepository();
	Prenda prenda1 = new Prenda(TipoPrenda.CAMISA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda2 = new Prenda(TipoPrenda.CAMPERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda3 = new Prenda(TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda4 = new Prenda(TipoPrenda.BERMUDAS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda5 = new Prenda(TipoPrenda.CALZAS, Material.LYCRA, Color.ROJO, Color.BLANCO);
	Prenda prenda6 = new Prenda(TipoPrenda.PANTALON, Material.ALGODON, Color.ROJO, Color.BLANCO);
	ArrayList<Prenda> listaDePrendas1 = new ArrayList<Prenda>();
	ArrayList<Prenda> listaDePrendas2 = new ArrayList<Prenda>();
	Guardarropas guardaRopas1 = new Guardarropas(listaDePrendas1);
	Guardarropas guardaRopas2 = new Guardarropas(listaDePrendas2);
	ArrayList<Guardarropas> listaGuardarropas1 = new ArrayList<Guardarropas>();
	ArrayList<Guardarropas> listaGuardarropas2 = new ArrayList<Guardarropas>();
	Suscripcion subs = new SuscripcionPremium();
	Suscripcion subs2 = new SuscripcionGratuita();
	Usuario user1 = new Usuario(listaGuardarropas1, subs, "test@test.com", "12341234110", 0);
	Usuario user2 = new Usuario(listaGuardarropas2, subs2, "test2@test.com", "1122112209", 0);
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
		
		userRepo.persist(user1);
		userRepo.persist(user2);
	}

	@Test
	@DisplayName("Verificar la cantidad de eventos de pepeCirco")
	public void cantidadDeEventosPepeCirco() {
		Optional<Usuario> user = userRepo.find(1);
		Assert.assertEquals(user.get().getEventos().size(), 3);
	}

	@Test
	@DisplayName("Verificar la cantidad de eventos de MamaKondo")
	public void cantidadDeEventosMamaKondo() {
		Optional<Usuario> user = userRepo.find(2);
		Assert.assertEquals(user.get().getEventos().size(), 1);
	}
	
	@Test
	@DisplayName("Verificar la cantidad de guardarropas del usuario")
	public void cantidadDeGuardarropas() {
		Optional<Usuario> user = userRepo.find("mamaKondo", "456");
		Assert.assertEquals(user.get().getGuardaRopas().size(), 1);
	}
	
	@Test
	@DisplayName("Verificar que el Usuario tiene el Guardarropa id asociado")
	public void coincidenElId() {	
		Optional<Usuario> user = userRepo.find(1);
		Guardarropas guardarropa = user.get().getGuardaRopas().get(0);
		Optional<Guardarropas> guardarropaId = userRepo.findGuardarropaById(1);
		Guardarropas guardarropaEncontrado = guardarropaId.get();
		Assert.assertTrue(guardarropa.getId() == (guardarropaEncontrado.getId()));
	}
	
	@DisplayName("Verificar la cantidad de prendas de pepeCirco")
	@Test
	public void cantidadDePrendasPepeCirco() {
		Optional<Usuario> user = userRepo.find(1);
		Assert.assertEquals(user.get().getGuardaRopas().get(0).getPrendas().size(), 3);
	}

	@DisplayName("Verificar la cantidad de prendas de mamaKondo")
	@Test
	public void cantidadDePrendasMamaKondo() {
		Optional<Usuario> user = userRepo.find(2);
		Assert.assertEquals(user.get().getGuardaRopas().get(0).getPrendas().size(), 4);
	}
	
	@DisplayName("Verifica que la cantidad de usuarios persistidos es correcta")
	@Test
	public void cantidadDeUsuariosPersistidosTest() {
		Assert.assertEquals(userRepo.all().size(), 2);
	}

	@DisplayName("Obtiene al usuario de la bd por id")
	@Test
	public void obtenerUsuarioPorId() {
		Optional<Usuario> user = userRepo.find(1);
		Assert.assertEquals(user.get().getUsername(), "pepeCirco");
	}

	@DisplayName("Obtiene al usuario de la bd por username y password")
	@Test
	public void obtenerUsuarioPorNombreYPassword() {
		Optional<Usuario> pepe = userRepo.find("pepeCirco", "123");
		Assert.assertEquals(pepe.get().getUsername(), "pepeCirco");
	}
	
	@DisplayName("Verifica la carga de eventos")
	@Test
	public void verificarCargaDeEventos() {
		Optional<Usuario> user = userRepo.find(1);
		Assert.assertEquals(user.get().getEventos().size(), 3);
	}
/*	
	@DisplayName("Eliminar eventos del calendario")
	@Test
	public void eliminarEventos() {
		Optional<Usuario> user = userRepo.find(1);
		Assert.assertEquals(user.get().getEventos().remove(1).size(), 2);
	}
*/
	@After
	public void cleanSetUp() {
		userRepo.delete(user1);
		userRepo.delete(user2);
	}
}
