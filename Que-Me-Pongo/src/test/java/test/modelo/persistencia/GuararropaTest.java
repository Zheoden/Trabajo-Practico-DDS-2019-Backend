package test.modelo.persistencia;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.clases.Guardarropas;
import modelo.clases.Usuario;
import repository.UsuarioRepository;

public class GuararropaTest {

	UsuarioRepository userRepo = new UsuarioRepository();

	@Test
	@DisplayName("Verificar la cantidad de guardarropas del usuario")
	public void cantidadDeGuardarropas() {
		Optional<Usuario> user = userRepo.find("mamaKondo", "456");
		Assert.assertEquals(user.get().getGuardaRopas().size(), 1);
	}
	
	@Test
	@DisplayName("Verificar que el Usuario tiene el Guardarropa id asociado")
	public void coincidenElId() {	
		Optional<Usuario> user = userRepo.find(1);
		Guardarropas guardarropa = user.get().getGuardaRopas().get(0);
		Optional<Guardarropas> guardarropaId = userRepo.findWardrobe(1);
		Guardarropas guardarropaEncontrado = guardarropaId.get();
		Assert.assertTrue(guardarropa.getId() == (guardarropaEncontrado.getId()));
	}
	
}
