package repository;

import modelo.dtos.Color;
import modelo.interfaces.Repository;

import java.util.List;

public class ColorRepository implements Repository<Color> {
    @Override
    public List<Color> all() {

        return entityManager().createQuery("FROM Color").getResultList();
    }
}
