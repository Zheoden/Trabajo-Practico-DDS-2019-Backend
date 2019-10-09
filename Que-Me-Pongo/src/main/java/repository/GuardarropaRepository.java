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
		String query = "SELECT g FROM Guardarropas g JOIN GuardarropaPorUsuario gpu ON (gpu.guardarropas_id = g.id) JOIN Usuario u ON(gpu.usuario_id = u.id) WHERE u.username = :username";
	    List<Guardarropas> guardarropas = entityManager().createQuery(query).getResultList();
	    return guardarropas;
	}

}
