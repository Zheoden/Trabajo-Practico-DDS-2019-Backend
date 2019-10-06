package test.modelo.persistencia;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import modelo.clases.Usuario;
import repository.UsuarioRepository;

public class UsuarioTest {
	UsuarioRepository userRepo =  new UsuarioRepository();
	@Test
	public void cantidadDeUsuariosPersistidosTest()
	{
	Assert.assertEquals(userRepo.all().size(),2);
	}
	

	@Test
	public void obtenerUsuarioPorId()
	{
	Optional<Usuario> user = userRepo.find(1);
	Assert.assertEquals(user.get().getUsername(),"pepeCirco");
	}

}
