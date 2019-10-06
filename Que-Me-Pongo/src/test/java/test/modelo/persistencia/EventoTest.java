package test.modelo.persistencia;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.clases.Usuario;

import repository.UsuarioRepository;


public class EventoTest {

	UsuarioRepository userRepo = new UsuarioRepository();	

	@Test
	@DisplayName("Verificar la cantidad de eventos de pepeCirco")
	public void cantidadDeEventosPepeCirco() {
		Optional<Usuario> user = userRepo.find(1);
		Assert.assertEquals(user.get().getEventos().size(), 2);
	}

	@Test
	@DisplayName("Verificar la cantidad de eventos de pepeCirco")
	public void cantidadDeEventosMamaKondo() {
		Optional<Usuario> user = userRepo.find(2);
		Assert.assertEquals(user.get().getEventos().size(), 1);
	}
	
}
