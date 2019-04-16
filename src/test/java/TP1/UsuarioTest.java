package TP1;

import java.util.ArrayList;
import modelo.Guardarropas;
import modelo.Prenda;
import modelo.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class UsuarioTest {
	
	@Test
	public void crearUsuarioCorrectamente() {
		Guardarropas guardaRopa1 = new Guardarropas(new ArrayList<Prenda>());
		Guardarropas guardaRopa2 = new Guardarropas(new ArrayList<Prenda>());
		ArrayList <Guardarropas> guardaRopas = new ArrayList <Guardarropas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);
		Usuario pepe = new Usuario(guardaRopas);
		Assert.assertEquals(pepe.getClass(), Usuario.class);
	}

}
