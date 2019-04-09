package TP1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import modelo.ClasesUtilsPrendas;
import modelo.Guardarropas;
import modelo.Prenda;
import modelo.Tipo;
import modelo.Usuario;

public class PrendasTest {
/*
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
*/
	@Test
	public void obtenerCategoriaDeUnTipo() {
	
		Prenda prenda = new Prenda(Tipo.Tipos.AROS, ClasesUtilsPrendas.Color.AMARILLO);
		Assert.assertEquals(Tipo.getCategoria(prenda.getTipo()), Tipo.Categoria.ACCESORIO);
		
	}
	
}
