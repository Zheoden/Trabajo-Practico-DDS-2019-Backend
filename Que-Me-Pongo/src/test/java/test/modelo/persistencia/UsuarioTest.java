package test.modelo.persistencia;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.clases.Usuario;
import repository.UsuarioRepository;

public class UsuarioTest {
	UsuarioRepository userRepo = new UsuarioRepository();

	@DisplayName("Verifica que la cantidad de usuarios persistidos es correcta")
	@Test
	public void cantidadDeUsuariosPersistidosTest() {
		Assert.assertEquals(userRepo.all().size(), 2);
	}

	@DisplayName("Obtiene al usuario de la bd por id")
	@Test
	public void obtenerUsuarioPorId() {
		Optional<Usuario> user = userRepo.find(1);
		Assert.assertEquals(user.get().getUsername(), "pepeCirco");
	}

	@DisplayName("Obtiene al usuario de la bd por username y password")
	@Test
	public void obtenerUsuarioPorNombreYPassword() {
		Optional<Usuario> pepe = userRepo.find("pepeCirco", "123");
		Assert.assertEquals(pepe.get().getUsername(), "pepeCirco");
	}

}
