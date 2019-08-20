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
	
	@Test
	@DisplayName("Tests para Enviar un correo electronico")
	public void mailSender() {
		Usuario pepe = new Usuario(new ArrayList<Guardarropas>(), new SuscripcionPremium(), "schifferJulian@gmail.com", "12341234");
		try {
			Utils.emailSender("gmail", pepe, new Evento("Tu vieja", "Tu hermana", GregorianCalendar.getInstance()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
