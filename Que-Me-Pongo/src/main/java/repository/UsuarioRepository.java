package repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import modelo.clases.Usuario;
import modelo.interfaces.Repository;

public class UsuarioRepository implements Repository<Usuario> {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> all() {
		return entityManager().createQuery("FROM Usuario").getResultList();
	}

	public Optional<Usuario> find(long id) {
		Usuario usuario = (Usuario) entityManager().createQuery("SELECT u FROM Usuario u WHERE u.id = :id")
				.setParameter("id", id).getSingleResult();

		return Optional.ofNullable(usuario);
	}

	public Optional<Usuario> find(String username) {
		Query query = entityManager()
				.createQuery("SELECT u FROM Usuario u WHERE u.username = :username")
				.setParameter("username", username)
				.setMaxResults(1);

		try {
			return Optional.of((Usuario) query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
	

}
