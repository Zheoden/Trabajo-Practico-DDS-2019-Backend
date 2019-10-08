package test.loadUsers;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import modelo.clases.Usuario;

import repository.UsuarioRepository;

public class EliminarUsuarios {

	@Test
	public void eliminarUsuarios() {

		UsuarioRepository userRepo = new UsuarioRepository();
		List<Usuario> usuarios = userRepo.all();
		userRepo.entityManager().getTransaction().begin();
		usuarios.forEach(usuario -> userRepo.entityManager().remove(usuario));
		userRepo.entityManager().flush();
		userRepo.entityManager().getTransaction().commit();
		Assert.assertEquals(0, usuarios.size());
	}
}
