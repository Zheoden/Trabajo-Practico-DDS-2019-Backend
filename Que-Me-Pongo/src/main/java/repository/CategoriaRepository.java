package repository;

import modelo.ropa.Categoria;
import modelo.interfaces.Repository;

import java.util.List;

public class CategoriaRepository implements Repository<Categoria> {
	@SuppressWarnings("unchecked")
	@Override
    public List<Categoria> all() {
        return entityManager().createQuery("FROM Categoria").getResultList();
    }
}
