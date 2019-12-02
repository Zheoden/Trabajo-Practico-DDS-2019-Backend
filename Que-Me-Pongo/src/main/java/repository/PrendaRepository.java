package repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import modelo.clases.Prenda;
import modelo.interfaces.Repository;

public class PrendaRepository implements Repository<Prenda> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Prenda> all() {
		return entityManager().createQuery("FROM Prenda").getResultList();
	}

	public Optional<Prenda> find(long id) {
		Prenda prenda = (Prenda) entityManager().createQuery("SELECT p FROM Prenda p WHERE e.id = :id")
				.setParameter("id", id).getSingleResult();

		return Optional.ofNullable(prenda);
	}

	public Optional<Prenda> findPrendaInGuardarropaById(long idGuardarropa, long idPrenda) {

		String query = "Select p FROM Guardarropas g JOIN g.prendas p WHERE g.id = :idGuardarropa AND p.id = :idPrenda";

		try {
			return Optional.of((Prenda) entityManager().createQuery(query).setParameter("idGuardarropa", idGuardarropa)
					.setParameter("idPrenda", idPrenda).getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Prenda> findAllByGuardarropaId(long guardarropaId) {
		String query = "FROM Guardarropas g JOIN g.prendas p WHERE  g.id = :id";
		List<Prenda> prendas = entityManager().createNativeQuery(query).setParameter("id", guardarropaId).getResultList();
		return prendas;
	}
}