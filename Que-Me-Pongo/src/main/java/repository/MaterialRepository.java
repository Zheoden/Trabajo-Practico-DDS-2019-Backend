package repository;

import modelo.dtos.Material;
import modelo.interfaces.Repository;

import java.util.List;

public class MaterialRepository implements Repository<Material> {
    @Override
    public List<Material> all() {
        return entityManager().createQuery("FROM Material").getResultList();
    }
}
