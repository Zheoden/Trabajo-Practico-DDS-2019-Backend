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
	public List<Atuendo> findSugerenciasAceptadasParaEvento(String nombreEvento, String nombreUsuario) {
		String query = "SELECT a FROM Atuendo a JOIN a.evento e JOIN e.usuario u WHERE a.aceptado = true AND e.nombre = :nombreEvento AND u.username = :nombreUsuario";
		List<Atuendo> atuendosXEvento = entityManager().createQuery(query).setParameter("nombreEvento", nombreEvento).setParameter("nombreUsuario", nombreUsuario).getResultList();
		return atuendosXEvento;
	}
	//		String query = "FROM Guardarropas g JOIN g.prendas p WHERE  g.id = :id";
	@SuppressWarnings("unchecked")
	public List<Atuendo> findSugerenciasAceptadas() {
		String query = "SELECT a FROM Atuendo a JOIN a.evento WHERE a.aceptado = true";
		List<Atuendo> atuendos = entityManager().createQuery(query).getResultList();
		return atuendos;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Atuendo> findSugerenciasParaEvento(String nombreEvento, String nombreUsuario) {
		String query = "SELECT a FROM Atuendo a JOIN a.evento e JOIN e.usuario u WHERE e.nombre = :nombreEvento AND u.username = :nombreUsuario";
		List<Atuendo> atuendosXEvento = entityManager().createQuery(query).setParameter("nombreEvento", nombreEvento).setParameter("nombreUsuario", nombreUsuario).getResultList();
		return atuendosXEvento;
	}

}
