package test.modelo.persistencia;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
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
import modelo.clases.Evento;

public class PersistirUsuarios {
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
	UsuarioRepository userRepo = new UsuarioRepository();
	Calendar fecha1 = GregorianCalendar.getInstance();
	Calendar fecha2 = GregorianCalendar.getInstance();
	Evento irAlAlamo = new Evento("AlamosNigth", "Adrogue", fecha2);
	Evento developer = new Evento("Desarrollar software", "Azul", fecha1);
	Evento poolParty = new Evento("pool party", "Azul", fecha1);
	Evento prueba = new Evento("prueba", "Azul", fecha1);
	
	
	
	
	@Test
	public void persistirUsuario() {
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

		userRepo.entityManager().getTransaction().begin();
		userRepo.entityManager().persist(user1);
		userRepo.entityManager().persist(user2);
		userRepo.entityManager().getTransaction().commit();
		List<Usuario> usuarios = userRepo.all();
		Assert.assertEquals(2, usuarios.size());
	}

}
