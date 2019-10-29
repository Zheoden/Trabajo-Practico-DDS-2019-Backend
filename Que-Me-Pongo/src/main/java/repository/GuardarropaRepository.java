package repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

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
	
	@SuppressWarnings("unchecked")
	public List<Guardarropas> findByUserId(long id){
		String query = "FROM Usuario u JOIN u.guardarropas  where u.id= :id ";
	    List<Guardarropas> guardarropas = entityManager().createQuery(query).setParameter("id",id).getResultList();
	    return guardarropas;
	}
	
	public Optional<Guardarropas> findWardrobeById(long id, long idGuardarropa){
		String query = "SELECT g FROM Usuario u JOIN u.guardarropas g WHERE u.id = :id AND g.id = :idGuardarropa";

		try {
		   return Optional.of((Guardarropas) entityManager()
					.createQuery(query)
					.setParameter("id", id)
					.setParameter("idGuardarropa",idGuardarropa)
					.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
}