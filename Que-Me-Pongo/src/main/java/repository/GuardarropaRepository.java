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
	
	public Optional<Guardarropas> findWardrobeById(String username, long id){
		String query = "SELECT g FROM Usuario u JOIN u.guardarropas g WHERE u.username = :username AND g.id = :id";

		try {
		   return Optional.of((Guardarropas) entityManager()
					.createQuery(query)
					.setParameter("username", username)
					.setParameter("id",id)
					.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
	
	public Optional<Guardarropas> findWardrobeByName(String username, String nombre){
		String query = "SELECT g FROM Usuario u JOIN u.guardarropas g WHERE u.username = :username AND g.nombre = :nombre";

		try {
		   return Optional.of((Guardarropas) entityManager()
					.createQuery(query)
					.setParameter("username", username)
					.setParameter("nombre",nombre)
					.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

}
