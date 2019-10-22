package repository;

import java.util.List;

import modelo.clases.Guardarropas;
import modelo.interfaces.Repository;

public class GuardarropaRepository implements Repository<Guardarropas> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Guardarropas> all() {
		return entityManager().createQuery("FROM Guardarropas").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Guardarropas> findByUser(String username){
		String query = "FROM Usuario u JOIN u.guardarropas  where u.username= :username ";
	    List<Guardarropas> guardarropas = entityManager().createQuery(query).setParameter("username",username).getResultList();
	    return guardarropas;
	}

}
