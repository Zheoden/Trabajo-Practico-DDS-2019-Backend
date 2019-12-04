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
		Atuendo atuendo = (Atuendo) entityManager().createQuery("SELECT a FROM Atuendo a WHERE a.id = :id").
				                    setParameter("id", id).
				                    getSingleResult();

		return Optional.ofNullable(atuendo);
	}


	@SuppressWarnings("unchecked")
	public List<Atuendo> findSugerenciasAceptadasParaEvento(long idEvento, long idUsuario) {
		String query = "SELECT a FROM Atuendo a JOIN a.evento e JOIN e.usuario u WHERE a.aceptado = true AND e.id = :idEvento AND u.id = :idUsuario";
		List<Atuendo> atuendosXEvento = entityManager().createQuery(query).setParameter("idEvento", idEvento).setParameter("idUsuario", idUsuario).getResultList();
		return atuendosXEvento;
	}

	@SuppressWarnings("unchecked")
	public List<Atuendo> findSugerenciasAceptadas() {
		String query = "SELECT a FROM Atuendo a JOIN a.evento WHERE a.aceptado = true";
		List<Atuendo> atuendos = entityManager().createQuery(query).getResultList();
		return atuendos;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Atuendo> findSugerenciasParaEvento(long idEvento, long idUsuario) {
		String query = "SELECT a FROM Atuendo a JOIN a.evento e JOIN e.usuario u WHERE e.id = :idEvento AND u.id = :idUsuario AND (a.calificacion IS NULL OR a.calificacion = 0)";
		List<Atuendo> atuendosXEvento = entityManager().createQuery(query).setParameter("idEvento", idEvento).setParameter("idUsuario", idUsuario).getResultList();
		return atuendosXEvento;
	}

}
