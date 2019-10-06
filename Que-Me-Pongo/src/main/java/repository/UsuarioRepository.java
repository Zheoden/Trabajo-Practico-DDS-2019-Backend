package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.SuscripcionGratuita;
import modelo.clases.SuscripcionPremium;
import modelo.clases.Usuario;
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
		Guardarropas guardaRopa1 = new Guardarropas(new ArrayList<Prenda>());
		Guardarropas guardaRopa2 = new Guardarropas(new ArrayList<Prenda>());
		ArrayList<Guardarropas> guardaRopas1 = new ArrayList<Guardarropas>();
		ArrayList<Guardarropas> guardaRopas2 = new ArrayList<Guardarropas>();
		guardaRopas1.add(guardaRopa1);
		guardaRopas2.add(guardaRopa2);
		Suscripcion subs = new SuscripcionPremium();
		Suscripcion subs2 = new SuscripcionGratuita();
		Usuario user1 = new Usuario(guardaRopas1, subs, "test@test.com", "12341234110", 0);
		Usuario user2 = new Usuario(guardaRopas2, subs2, "test2@test.com", "1122112209", 0);
		user1.setUsername("pepeCirco");
		user2.setUsername("mamaKondo");
		user1.setPassword("123");
		user2.setPassword("456");
		List<Usuario> usuarios = this.all();
		entityManager().getTransaction().begin();
		if (!usuarios.isEmpty()) {
			usuarios.forEach(usuario -> entityManager().remove(usuario));
		}
		entityManager().persist(user1);
		entityManager().persist(user2);

		entityManager().getTransaction().commit();
	}
}
