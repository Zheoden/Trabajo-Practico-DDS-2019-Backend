package repository;

import modelo.ropa.Material;
import modelo.interfaces.Repository;

import java.util.List;

public class MaterialRepository implements Repository<Material> {
	@SuppressWarnings("unchecked")
	@Override
    public List<Material> all() {
        return entityManager().createQuery("FROM Material").getResultList();
    }
}
