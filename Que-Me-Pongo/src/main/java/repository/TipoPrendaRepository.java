package repository;

import java.util.List;

import modelo.interfaces.Repository;
import modelo.ropa.TipoPrenda;

public class TipoPrendaRepository implements Repository<TipoPrenda> {
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoPrenda> all() {
		return entityManager().createQuery("FROM TipoPrenda").getResultList();
	}
}
