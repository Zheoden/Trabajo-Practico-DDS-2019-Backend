package repository;

import modelo.dtos.Categoria;
import modelo.interfaces.Repository;

import java.util.List;

public class CategoriaRepository implements Repository<Categoria> {
    @Override
    public List<Categoria> all() {
        return entityManager().createQuery("FROM Categoria").getResultList();
    }
}
