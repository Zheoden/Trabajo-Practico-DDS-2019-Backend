package repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.clases.Evento;
import modelo.interfaces.Repository;

public class EventoRepository implements Repository<Evento> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Evento> all() {
		return entityManager().createQuery("FROM Evento").getResultList();
	}

	public Optional<Evento> find(long id) {
		Evento evento = (Evento) entityManager().
				                 createQuery("SELECT e FROM Evento e WHERE e.id = :id").
				                 setParameter("id", id).getSingleResult();

		return Optional.ofNullable(evento);
	}

	
	@SuppressWarnings("unchecked")
	public List<Evento> findAllOfUser(String username) {
		String query = "SELECT e FROM Evento e JOIN Usuario u  on u.id = e.usuario_id  WHERE u.username = :username";
		List<Evento> eventos = entityManager().
				               createQuery(query).
				               setParameter("username", username).
				               getResultList();
		return eventos;
	}

	public Optional<Evento> find(String nombre) {
		Query query = entityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.nombre = :nombre")
				.setParameter("nombre", nombre)
				.setMaxResults(1);

		try {
			return Optional.of((Evento) query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

}
