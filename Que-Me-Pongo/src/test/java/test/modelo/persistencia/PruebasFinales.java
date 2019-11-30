package test.modelo.persistencia;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import junit.framework.Assert;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.SuscripcionGratuita;
import modelo.clases.SuscripcionPremium;
import modelo.clases.Usuario;
import modelo.dtos.Color;
import modelo.dtos.Material;
import modelo.dtos.TipoPrenda;
import modelo.interfaces.Suscripcion;
import repository.UsuarioRepository;

public class PruebasFinales {

	static UsuarioRepository userRepo = new UsuarioRepository();
	
	//Objetos Para El Usuario Alejandro
	static ArrayList<Prenda> listaDePrendasUser1 = new ArrayList<Prenda>();
	static ArrayList<Guardarropas> listaGuardarropasUser1 = new ArrayList<Guardarropas>();
	static Guardarropas user1Guardarropa = new Guardarropas(listaDePrendasUser1, "GuardarropaDeAlejandro");
	static Suscripcion user1Suscripcion = new SuscripcionGratuita();
	static Usuario user1 = new Usuario("Alejandro", "Roco", listaGuardarropasUser1, user1Suscripcion, "alejandroRoco@gmail.com", "1514325585", 0);
	
	//Prendas Para Alejandro
	static Prenda prenda1Ale = new Prenda("Remera cuello redondo manga corta", TipoPrenda.REMERACORTA, Material.ALGODON, Color.NEGRO);
	static Prenda prenda2Ale = new Prenda("Remera escote V manga corta", TipoPrenda.REMERACORTA, Material.LYCRA, Color.BLANCO);
	//Este tiene que ser Musculosa
	static Prenda prenda3Ale = new Prenda("Musculosa", TipoPrenda.REMERACORTA, Material.ALGODON, Color.NEGRO);
	static Prenda prenda4Ale = new Prenda("Campera", TipoPrenda.CAMPERA, Material.CUERO, Color.BLANCO);
	static Prenda prenda5Ale = new Prenda("Sueter", TipoPrenda.SWEATER, Material.POLIESTER, Color.BLANCO);
	static Prenda prenda6Ale = new Prenda("Bermuda", TipoPrenda.BERMUDAS, Material.JEAN, Color.CELESTE);
	//Diferenciar entre Pantalon Largo y Corto
	static Prenda prenda7Ale = new Prenda("Pantalon Largo", TipoPrenda.PANTALON, Material.NYLON, Color.GRIS);
	static Prenda prenda8Ale = new Prenda("Zapatillas", TipoPrenda.ZAPATILLAS, Material.NYLON, Color.BORDO);
	static Prenda prenda9Ale = new Prenda("Zapatos", TipoPrenda.ZAPATOS, Material.CUERO, Color.NEGRO);

//-------------------------------------------------------------------------------------------------------------//	
	
	//Objetos Para El Usuario Julieta
	static ArrayList<Prenda> listaDePrendasUser2 = new ArrayList<Prenda>();
	static ArrayList<Guardarropas> listaGuardarropasUser2 = new ArrayList<Guardarropas>();
	static Guardarropas user2Guardarropa = new Guardarropas(listaDePrendasUser2, "GuardarropaDeJulieta");
	static Suscripcion user2Suscripcion = new SuscripcionPremium();
	
	static Usuario user2 = new Usuario("Julieta", "Azul", listaGuardarropasUser2, user2Suscripcion, "julietaAzul@hotmail.com", "1566953120", 0);
	
	//Prendas Para Alejandro
	static Prenda prenda1Juli = new Prenda("Remera cuello redondo manga larga", TipoPrenda.REMERALARGA, Material.LYCRA, Color.AMARILLO);
	static Prenda prenda2Juli = new Prenda("Remera escote V manga larga", TipoPrenda.REMERALARGA, Material.ALGODON, Color.BLANCO);
	//Este tiene que ser Musculosa
	static Prenda prenda3Juli = new Prenda("Musculosa", TipoPrenda.REMERACORTA, Material.ALGODON, Color.NEGRO);
	static Prenda prenda4Juli = new Prenda("Sueter", TipoPrenda.SWEATER, Material.POLIESTER, Color.GRIS);
	static Prenda prenda5Juli = new Prenda("Pollera", TipoPrenda.POLLERA, Material.SEDA, Color.NEGRO);
	static Prenda prenda6Juli = new Prenda("Calza", TipoPrenda.CALZAS, Material.NYLON, Color.NEGRO);
	static Prenda prenda7Juli = new Prenda("Buzo", TipoPrenda.BUZO, Material.ALGODON, Color.BLANCO);
	static Prenda prenda8Juli = new Prenda("Zapatos", TipoPrenda.ZAPATOS, Material.CUERO, Color.NEGRO);
	static Prenda prenda9Juli = new Prenda("Sandalias", TipoPrenda.OJOTAS, Material.CUERO, Color.NEGRO);
	
//-------------------------------------------------------------------------------------------------------------//	
	
	@BeforeClass
	public static void setUp() {

		// Establecimiento de Atributos para Ale
		user1.setUsername("aroco");
		user1.setPassword("123456");

		listaDePrendasUser1.add(prenda1Ale);
		listaDePrendasUser1.add(prenda2Ale);
		listaDePrendasUser1.add(prenda3Ale);
		listaDePrendasUser1.add(prenda4Ale);
		listaDePrendasUser1.add(prenda5Ale);
		listaDePrendasUser1.add(prenda6Ale);
		listaDePrendasUser1.add(prenda7Ale);
		listaDePrendasUser1.add(prenda8Ale);
		listaDePrendasUser1.add(prenda9Ale);

		listaGuardarropasUser1.add(user1Guardarropa);

		// Establecimiento de Atributos para Juli

		user2.setUsername("jazul");
		user2.setPassword("123456");

		listaDePrendasUser2.add(prenda1Juli);
		listaDePrendasUser2.add(prenda2Juli);
		listaDePrendasUser2.add(prenda3Juli);
		listaDePrendasUser2.add(prenda4Juli);
		listaDePrendasUser2.add(prenda5Juli);
		listaDePrendasUser2.add(prenda6Juli);
		listaDePrendasUser2.add(prenda7Juli);
		listaDePrendasUser2.add(prenda8Juli);
		listaDePrendasUser2.add(prenda9Juli);

		listaGuardarropasUser2.add(user2Guardarropa);

		userRepo.persist(user1);
		userRepo.persist(user2);

	}

	@SuppressWarnings("deprecation")
	@DisplayName("Test Para Poder Persistir Los Datos De Entrega")
	@Test
	public void test() {
		Assert.assertEquals(2, userRepo.all().size());
	}

	@AfterClass
	public static void clearSetUp() {
		userRepo.delete(user1);
		userRepo.delete(user2);
	}
}