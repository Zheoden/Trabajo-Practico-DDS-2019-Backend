package repository;

import modelo.ropa.Color;
import modelo.interfaces.Repository;

import java.util.List;

public class ColorRepository implements Repository<Color> {
    @SuppressWarnings("unchecked")
	@Override
    public List<Color> all() {

        return entityManager().createQuery("FROM Color").getResultList();
    }
}
