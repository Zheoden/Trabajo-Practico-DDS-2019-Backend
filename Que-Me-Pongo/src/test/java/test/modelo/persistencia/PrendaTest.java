package test.modelo.persistencia;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.clases.Usuario;
import repository.UsuarioRepository;

public class PrendaTest {
	UsuarioRepository userRepo = new UsuarioRepository();
	
	@DisplayName("Verificar la cantidad de prendas de pepeCirco")
	@Test
	public void cantidadDePrendas() {
		Optional<Usuario> user = userRepo.find(1);
		Assert.assertEquals(user.get().getGuardaRopas().size(), 1);
	}
/*
	private Object findFirst(List<Guardarropas> guardaRopas) {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
