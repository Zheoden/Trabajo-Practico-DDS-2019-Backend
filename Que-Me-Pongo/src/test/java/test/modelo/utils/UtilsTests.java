package test.modelo.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.mail.internet.ParseException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import junit.framework.Assert;
import main.Application;
import modelo.clases.Evento;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.SuscripcionPremium;
import modelo.clases.Usuario;
import modelo.dtos.Color;
import modelo.dtos.Material;
import modelo.dtos.TipoPrenda;
import modelo.interfaces.Suscripcion;
import utils.Utils;

@DisplayName("Tests para las funciones de utilidad")
public class UtilsTests extends Application {

	@Test
	@DisplayName("Tests para obtener correctamente el archivo de propiedades")
	public void getProperties() {
		try {
			Properties aux1 = Utils.getProyectProperties("gmail");
			//Assert.assertEquals(aux1.getProperty("mail.smtp.host"), "smtp.gmail.com");
			Assert.assertEquals(aux1.getProperty("mail.smtp.auth"), "true");
			//Assert.assertEquals(aux1.getProperty("mail.smtp.port"), "587");
			Assert.assertEquals(aux1.getProperty("mail.smtp.starttls.enable"), "true");
			Assert.assertEquals(aux1.getProperty("gmail.mail.from"), "correoDePrubaDDS@gmail.com");
			Assert.assertEquals(aux1.getProperty("gmail.mail.user"), "correoDePrubaDDS@gmail.com");
			Assert.assertEquals(aux1.getProperty("gmail.mail.password"), "DDS123456");

			Properties aux2 = Utils.getProyectProperties("hotmail");
			//Assert.assertEquals(aux2.getProperty("mail.smtp.host"), "smtp.live.com");
			Assert.assertEquals(aux2.getProperty("mail.smtp.auth"), "true");
			//Assert.assertEquals(aux2.getProperty("mail.smtp.port"), "25");
			Assert.assertEquals(aux2.getProperty("mail.smtp.starttls.enable"), "true");
			Assert.assertEquals(aux2.getProperty("hotmail.mail.from"), "correoDePrubaDDS@hotmail.com");
			Assert.assertEquals(aux2.getProperty("hotmail.mail.user"), "correoDePrubaDDS@hotmail.com");
			Assert.assertEquals(aux2.getProperty("hotmail.mail.password"), "DDS123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


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
	ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	ArrayList<Prenda> prendas1 = new ArrayList<Prenda>();

	@Test
	public void enviarMailDeRecordatorioCadaCincoMinutosTest() throws Exception
	{
		Guardarropas guardaRopa1 = new Guardarropas(new ArrayList<Prenda>());
		Guardarropas guardaRopa2 = new Guardarropas(new ArrayList<Prenda>());
		ArrayList<Guardarropas> guardaRopas = new ArrayList<Guardarropas>();
		guardaRopas.add(guardaRopa1);
		guardaRopas.add(guardaRopa2);
		Suscripcion subs = new SuscripcionPremium();
		Usuario pepe = new Usuario("axelfulop@hotmail.com",guardaRopas, subs);
		String [] args = null;
		main(args);
	}
}
