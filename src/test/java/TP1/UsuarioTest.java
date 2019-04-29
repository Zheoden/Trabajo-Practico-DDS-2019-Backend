package TP1;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import modelo.Atuendo;
import modelo.Guardarropas;
import modelo.Prenda;
import modelo.Usuario;
import modelo.enums.Categoria;
import modelo.enums.Color;
import modelo.enums.Tela;
import modelo.enums.Tipo;

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
	
	@Test
	public void verificarGuardarropasTrue() {
		Prenda prenda = new Prenda(Tipo.REMERACORTA, Tela.ALGODON, Color.AZUL);
		Prenda prenda1 = new Prenda(Tipo.PANTALON, Tela.ALGODON, Color.AZUL);
		Prenda prenda2 = new Prenda(Tipo.ZAPATILLAS, Tela.ALGODON, Color.AZUL);
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		Guardarropas guardaRopa1 = new Guardarropas(prendas);
		
		Prenda prenda3 = new Prenda(Tipo.REMERACORTA, Tela.ALGODON, Color.ROJO);
		Prenda prenda4 = new Prenda(Tipo.PANTALON, Tela.ALGODON, Color.ROJO);
		Prenda prenda5 = new Prenda(Tipo.ZAPATILLAS, Tela.ALGODON, Color.ROJO);
		ArrayList <Prenda> prendas1 = new ArrayList <Prenda>();
		prendas1.add(prenda3);
		prendas1.add(prenda4);
		prendas1.add(prenda5);
		Guardarropas guardaRopa2 = new Guardarropas(prendas1);
		
		ArrayList <Guardarropas> guardaRopas = new ArrayList <Guardarropas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);
		Usuario pepe = new Usuario(guardaRopas);
		Assert.assertEquals(pepe.verificarGuardarropas(Categoria.PARTEINFERIOR), true);
	}
	
	@Test
	public void verificarGuardarropasFalse() {
		Prenda prenda = new Prenda(Tipo.REMERACORTA, Tela.ALGODON, Color.AZUL);
		Prenda prenda1 = new Prenda(Tipo.PANTALON, Tela.ALGODON, Color.AZUL);
		Prenda prenda2 = new Prenda(Tipo.ZAPATILLAS, Tela.ALGODON, Color.AZUL);
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		Guardarropas guardaRopa1 = new Guardarropas(prendas);
		
		Prenda prenda3 = new Prenda(Tipo.REMERACORTA, Tela.ALGODON, Color.ROJO);
		Prenda prenda4 = new Prenda(Tipo.PANTALON, Tela.ALGODON, Color.ROJO);
		Prenda prenda5 = new Prenda(Tipo.ZAPATILLAS, Tela.ALGODON, Color.ROJO);
		ArrayList <Prenda> prendas1 = new ArrayList <Prenda>();
		prendas1.add(prenda3);
		prendas1.add(prenda4);
		prendas1.add(prenda5);
		Guardarropas guardaRopa2 = new Guardarropas(prendas1);
		
		ArrayList <Guardarropas> guardaRopas = new ArrayList <Guardarropas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);
		Usuario pepe = new Usuario(guardaRopas);
		Assert.assertEquals(pepe.verificarGuardarropas(Categoria.ACCESORIO), false);
	}
	
	@Test
	public void getGuardarropasConPrenda() {
		Prenda prenda = new Prenda(Tipo.REMERACORTA, Tela.ALGODON, Color.AZUL);
		Prenda prenda2 = new Prenda(Tipo.ZAPATILLAS, Tela.ALGODON, Color.AZUL);
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		prendas.add(prenda);
		prendas.add(prenda2);
		Guardarropas guardaRopa1 = new Guardarropas(prendas);
		
		Prenda prenda4 = new Prenda(Tipo.PANTALON, Tela.ALGODON, Color.ROJO);
		Prenda prenda5 = new Prenda(Tipo.ZAPATILLAS, Tela.ALGODON, Color.ROJO);
		ArrayList <Prenda> prendas1 = new ArrayList <Prenda>();
		prendas1.add(prenda4);
		prendas1.add(prenda5);
		Guardarropas guardaRopa2 = new Guardarropas(prendas1);
		
		ArrayList <Guardarropas> guardaRopas = new ArrayList <Guardarropas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);
		Usuario pepe = new Usuario(guardaRopas);
		Assert.assertEquals(pepe.getGuardarropasConPrenda(Categoria.PARTESUPERIOR), guardaRopa1);
		Assert.assertEquals(pepe.getGuardarropasConPrenda(Categoria.PARTEINFERIOR), guardaRopa2);
	}
	
	@Test
	public void generarAtuendoValido() throws Exception {
		Prenda prenda = new Prenda(Tipo.REMERACORTA, Tela.ALGODON, Color.AZUL);
		Prenda prenda1 = new Prenda(Tipo.PANTALON, Tela.ALGODON, Color.AZUL);
		Prenda prenda2 = new Prenda(Tipo.ZAPATILLAS, Tela.ALGODON, Color.AZUL);
		ArrayList <Prenda> prendas = new ArrayList <Prenda>();
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		Guardarropas guardaRopa1 = new Guardarropas(prendas);
		
		Prenda prenda3 = new Prenda(Tipo.REMERACORTA, Tela.ALGODON, Color.ROJO);
		Prenda prenda4 = new Prenda(Tipo.PANTALON, Tela.ALGODON, Color.ROJO);
		Prenda prenda5 = new Prenda(Tipo.ZAPATILLAS, Tela.ALGODON, Color.ROJO);
		ArrayList <Prenda> prendas1 = new ArrayList <Prenda>();
		prendas1.add(prenda3);
		prendas1.add(prenda4);
		prendas1.add(prenda5);
		Guardarropas guardaRopa2 = new Guardarropas(prendas1);
		
		ArrayList <Guardarropas> guardaRopas = new ArrayList <Guardarropas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);
		Usuario pepe = new Usuario(guardaRopas);
		Atuendo atuendo = pepe.sugerirAtuendo();
		Assert.assertEquals(atuendo.getPrendas().get(0).Categoria(), Categoria.PARTEINFERIOR);
		Assert.assertEquals(atuendo.getPrendas().get(1).Categoria(), Categoria.PARTESUPERIOR);
		Assert.assertEquals(atuendo.getPrendas().get(2).Categoria(), Categoria.CALZADO);
	}
	
	@Test(expected = Exception.class)
	public void generarAtuendoError() throws Exception {
		ArrayList <Guardarropas> guardaRopas = new ArrayList <Guardarropas>();
		Usuario pepe = new Usuario(guardaRopas);
		pepe.sugerirAtuendo();
	}

}
