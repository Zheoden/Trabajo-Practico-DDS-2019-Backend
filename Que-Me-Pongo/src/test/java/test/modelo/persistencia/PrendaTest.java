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
	public void cantidadDePrendasPepeCirco() {
		Optional<Usuario> user = userRepo.find(1);
		Assert.assertEquals(user.get().getGuardaRopas().get(0).getPrendas().size(), 3);
	}

	@DisplayName("Verificar la cantidad de prendas de mamaKondo")
	@Test
	public void cantidadDePrendasMamaKondo() {
		Optional<Usuario> user = userRepo.find(2);
		Assert.assertEquals(user.get().getGuardaRopas().get(0).getPrendas().size(), 4);
	}
}
