package repository;

import java.util.List;

import java.util.Optional;

import modelo.clases.Atuendo;

import modelo.interfaces.Repository;

public class AtuendoRepository implements Repository<Atuendo> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Atuendo> all() {
		return entityManager().createQuery("FROM Atuendo").getResultList();
	}

	public Optional<Atuendo> find(long id) {
		Atuendo atuendo = (Atuendo) entityManager().createQuery("SELECT a FROM Atuendo  WHERE a.id = :id").
				                    setParameter("id", id).
				                    getSingleResult();

		return Optional.ofNullable(atuendo);
	}

	@SuppressWarnings("unchecked")
	public List<Atuendo> findSugerenciasAceptadas() {
		String query = "SELECT a FROM Atuendo a JOIN evento e on (e.evento_id = : a.evento_id WHERE a.aceptado = true)";
		List<Atuendo> atuendos = entityManager().createQuery(query).getResultList();
		return atuendos;
	}

}
