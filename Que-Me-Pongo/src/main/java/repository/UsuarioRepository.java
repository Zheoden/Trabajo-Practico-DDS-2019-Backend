package repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.clases.Evento;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.SuscripcionGratuita;
import modelo.clases.SuscripcionPremium;
import modelo.clases.Usuario;
import modelo.dtos.Color;
import modelo.dtos.Material;
import modelo.dtos.TipoPrenda;
import modelo.interfaces.Repository;
import modelo.interfaces.Suscripcion;
import utils.Utils;

public class UsuarioRepository implements Repository<Usuario> {

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> all() {
		return entityManager().createQuery("FROM Usuario").getResultList();
	}

	public Optional<Usuario> find(long id) {
		Usuario usuario = (Usuario) entityManager().createQuery("SELECT u FROM Usuario u WHERE u.id = :id")
				.setParameter("id", id).getSingleResult();

		return Optional.ofNullable(usuario);
	}

	public Optional<Usuario> find(String username, String password) {
		Query query = entityManager()
				.createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password")
				.setParameter("username", username).setParameter("password", Utils.generarHash256(password))
				.setMaxResults(1);

		try {
			return Optional.of((Usuario) query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	public void init() {
		Prenda prenda1 = new Prenda(TipoPrenda.CAMISA, Material.ALGODON, Color.ROJO, Color.BLANCO);
		Prenda prenda2 = new Prenda(TipoPrenda.CAMPERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
		Prenda prenda3 = new Prenda(TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROJO, Color.BLANCO);
		Prenda prenda4 = new Prenda(TipoPrenda.BERMUDAS, Material.ALGODON, Color.ROJO, Color.BLANCO);
		Prenda prenda5 = new Prenda(TipoPrenda.CALZAS, Material.LYCRA, Color.ROJO, Color.BLANCO);
		Prenda prenda6 = new Prenda(TipoPrenda.PANTALON, Material.ALGODON, Color.ROJO, Color.BLANCO);
		
		ArrayList<Prenda> listaDePrendas = new ArrayList<Prenda>();
		listaDePrendas.add(prenda1);
		listaDePrendas.add(prenda2);
		listaDePrendas.add(prenda3);
		
		ArrayList<Prenda> listaDePrendas2 = new ArrayList<Prenda>();
		listaDePrendas.add(prenda4);
		listaDePrendas.add(prenda5);
		listaDePrendas.add(prenda6);
		Guardarropas guardaRopa1 = new Guardarropas(listaDePrendas);
		Guardarropas guardaRopa2 = new Guardarropas(listaDePrendas2);
		ArrayList<Guardarropas> guardaRopas1 = new ArrayList<Guardarropas>();
		ArrayList<Guardarropas> guardaRopas2 = new ArrayList<Guardarropas>();
		guardaRopas1.add(guardaRopa1);
		guardaRopas2.add(guardaRopa2);
		Suscripcion subs = new SuscripcionPremium();
		Suscripcion subs2 = new SuscripcionGratuita();
		Usuario user1 = new Usuario(guardaRopas1, subs, "test@test.com", "12341234110", 0);
		Usuario user2 = new Usuario(guardaRopas2, subs2, "test2@test.com", "1122112209", 0);
		
		Calendar fecha1 = GregorianCalendar.getInstance();
		Calendar fecha2 = GregorianCalendar.getInstance();
		fecha1.set(2019, 10, 12);
		fecha1.set(Calendar.HOUR_OF_DAY, 07);
		fecha1.set(Calendar.MINUTE, 30);
		fecha2.set(2019, 06, 29);
		fecha2.set(Calendar.HOUR_OF_DAY, 21);
		fecha2.set(Calendar.MINUTE, 30);
		Evento irAlAlamo = new Evento("AlamosNigth", "Adrogue", fecha2);
		Evento developer = new Evento("Desarrollar software", "Azul", fecha1);
		Evento poolParty = new Evento("pool party", "Azul", fecha1);
		Evento prueba = new Evento("prueba", "Azul", fecha1);
		
		user1.cargarEvento(irAlAlamo);
		user1.cargarEvento(poolParty);
		user1.cargarEvento(developer);
		user2.cargarEvento(prueba);
		
		user1.setUsername("pepeCirco");
		user2.setUsername("mamaKondo");
		user1.setPassword("123");
		user2.setPassword("456");
		List<Usuario> usuarios = this.all();
		if (usuarios.isEmpty()) {
		entityManager().getTransaction().begin();
		entityManager().persist(user1);
		entityManager().persist(user2);
		entityManager().getTransaction().commit();
		}
	}
}
