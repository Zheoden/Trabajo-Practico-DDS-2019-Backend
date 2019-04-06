package TP01;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import modelo.Usuario;
import modelo.GuardaRopas;



public class UserTest {
	public class testCreacionUser {
		
		@Test
		public void crearCorrectamenteUnUsuario()
		{
		GuardaRopas guardaRopas1 = new GuardaRopas();
		GuardaRopas guardaRopas2 = new GuardaRopas();
		ArrayList <GuardaRopas> guardaRopas = new ArrayList <GuardaRopas>();
	    guardaRopas.add(guardaRopas1);
	    guardaRopas.add(guardaRopas2);
		Usuario juanPablo = new Usuario(guardaRopas);
			Assert.assertTrue(juanPablo.equals(Usuario.class));
		}
		}
}
