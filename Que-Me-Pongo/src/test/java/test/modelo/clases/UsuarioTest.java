package test.modelo.clases;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.Suscripcion;
import modelo.clases.SuscripcionPremium;
import modelo.clases.Usuario;
import modelo.enums.Color;
import modelo.enums.Material;
import modelo.enums.comportamiento.TipoAccesorio;
import modelo.enums.comportamiento.TipoCalzado;
import modelo.enums.comportamiento.TipoInferior;
import modelo.enums.comportamiento.TipoSuperior;

@DisplayName("Tests para los Usuarios")
public class UsuarioTest {
	
	Prenda prenda = new Prenda(TipoSuperior.BUZO, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda1 = new Prenda(TipoSuperior.CAMISA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda2 = new Prenda(TipoSuperior.CAMPERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda3 = new Prenda(TipoSuperior.REMERACORTA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda4 = new Prenda(TipoSuperior.REMERALARGA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	
	Prenda prenda5 = new Prenda(TipoInferior.BERMUDAS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda6 = new Prenda(TipoInferior.CALZAS, Material.LYCRA, Color.ROJO, Color.BLANCO);
	Prenda prenda7 = new Prenda(TipoInferior.PANTALON, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda8 = new Prenda(TipoInferior.POLLERA, Material.ALGODON, Color.ROJO, Color.BLANCO);
	Prenda prenda9 = new Prenda(TipoInferior.SHORTS, Material.ALGODON, Color.ROJO, Color.BLANCO);
	
	Prenda prenda10 = new Prenda(TipoCalzado.OJOTAS, Material.CUERO, Color.ROJO, Color.BLANCO);

	Prenda prenda11 = new Prenda(TipoAccesorio.ANTEOJOS, Material.CUERO, Color.ROJO, Color.BLANCO);
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
		
	}
	
	@Test
	@DisplayName("Verificar que todos los guardarropas del usuario sean acorde a su suscripcion")
	public void listaDeGuardarropasValida() {

	}

}
