package TP1;

import java.util.ArrayList;



import modelo.GuardaRopas;
import modelo.Usuario;

import org.junit.Assert;
import org.junit.Test;

public class UsuarioTest {
	
	@Test
	public void crearUsuarioCorrectamente ()
	{
		GuardaRopas guardaRopa1 = new GuardaRopas();
		GuardaRopas guardaRopa2 = new GuardaRopas();
		ArrayList <GuardaRopas> guardaRopas = new ArrayList <GuardaRopas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);
		Usuario pepe = new Usuario(guardaRopas);
		Assert.assertEquals(pepe.getClass(), Usuario.class);
	}
	

}
