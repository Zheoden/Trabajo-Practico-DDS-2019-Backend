package test.modelo.clases;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.clases.Atuendo;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.Suscripcion;
import modelo.clases.SuscripcionPremium;
import modelo.clases.Usuario;
import modelo.enums.Color;
import modelo.enums.Material;
import modelo.enums.comportamiento.TipoPrenda;

@DisplayName("Tests para los Usuarios")
public class UsuarioTest {
	Prenda prenda = new Prenda(TipoPrenda.BUZO, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda1 = new Prenda(TipoPrenda.CAMISA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda2 = new Prenda(TipoPrenda.CAMPERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda3 = new Prenda(TipoPrenda.REMERACORTA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda4 = new Prenda(TipoPrenda.REMERALARGA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	
	Prenda prenda5 = new Prenda(TipoPrenda.BERMUDAS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda6 = new Prenda(TipoPrenda.CALZAS, Material.LYCRA, Color.ROJO, Color.BLANCO);
	Prenda prenda7 = new Prenda(TipoPrenda.PANTALON, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda8 = new Prenda(TipoPrenda.POLLERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda9 = new Prenda(TipoPrenda.SHORTS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	
	Prenda prenda10 = new Prenda(TipoPrenda.OJOTAS, Material.CUERO, Color.ROJO, Color.BLANCO);

	Prenda prenda11 = new Prenda(TipoPrenda.ANTEOJOS, Material.CUERO, Color.ROJO, Color.BLANCO);
	ArrayList <Prenda> prendas = new ArrayList <Prenda>();
	ArrayList <Prenda> prendas1 = new ArrayList <Prenda>();
	
	@Test
	@DisplayName("Verificar la creacion de un usuario")
	public void crearUsuarioCorrectamente() {
		Guardarropas guardaRopa1 = new Guardarropas(new ArrayList<Prenda>());
		Guardarropas guardaRopa2 = new Guardarropas(new ArrayList<Prenda>());
		ArrayList <Guardarropas> guardaRopas = new ArrayList <Guardarropas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);
		Suscripcion subs = new SuscripcionPremium();
		Usuario pepe = new Usuario(guardaRopas, subs);
		Assert.assertEquals(pepe.getClass(), Usuario.class);
		Assert.assertEquals(pepe.getGuardaRopas(), guardaRopas);
		Assert.assertEquals(pepe.getSuscripcion(), subs);
	}
	
	@Test
	@DisplayName("Solicitar a todos los guardarropas todos los conjuntos que pueden armar y unificarlos")
	public void todosPosiblesAtuendosPorGuardarropa() {
		prendas.add(prenda);
		prendas.add(prenda1);
		prendas.add(prenda2);
		prendas.add(prenda5);
		prendas.add(prenda6);
		prendas.add(prenda10);
		prendas.add(prenda11);
		
		prendas1.add(prenda4);
		prendas1.add(prenda3);
		prendas1.add(prenda7);
		prendas1.add(prenda8);
		prendas1.add(prenda10);
		prendas1.add(prenda11);

		Guardarropas guardaRopa1 = new Guardarropas(prendas);
		Guardarropas guardaRopa2 = new Guardarropas(prendas1);
		ArrayList <Guardarropas> guardaRopas = new ArrayList <Guardarropas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);
		Usuario pepe = new Usuario(guardaRopas, new SuscripcionPremium());
		List<Atuendo> atuendos = pepe.todosPosiblesAtuendosPorGuardarropa();
		Assert.assertEquals(atuendos.size(), 1);
	}
	
	@Test
	@DisplayName("Verificar que todos los guardarropas del usuario sean acorde a su suscripcion")
	public void listaDeGuardarropasValida() {

	}

}
