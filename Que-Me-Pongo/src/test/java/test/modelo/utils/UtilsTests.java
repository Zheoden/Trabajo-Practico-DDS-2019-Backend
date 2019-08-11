package test.modelo.utils;

import java.util.Properties;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import junit.framework.Assert;
import utils.Utils;

@DisplayName("Tests para las funciones de utilidad")
public class UtilsTests {

	@Test
	@DisplayName("Tests para obtener correctamente el archivo de propiedades")
	public void getProperties() {
		try {
			Properties aux = Utils.getProyectProperties();
			Assert.assertEquals(aux.getProperty("gmail.mail.smtp.host"), "smtp.gmail.com");
			Assert.assertEquals(aux.getProperty("gmail.mail.smtp.auth"), "true");
			Assert.assertEquals(aux.getProperty("gmail.mail.smtp.port"), "587");
			Assert.assertEquals(aux.getProperty("gmail.mail.smtp.starttls.enable"), "true");
			Assert.assertEquals(aux.getProperty("gmail.mail.from"), "correoDePrubaDDS@gmail.com");
			Assert.assertEquals(aux.getProperty("gmail.mail.user"), "correoDePrubaDDS@gmail.com");
			Assert.assertEquals(aux.getProperty("gmail.mail.password"), "DDS123456");
			
			Assert.assertEquals(aux.getProperty("hotmail.mail.smtp.host"), "smtp.live.com");
			Assert.assertEquals(aux.getProperty("hotmail.mail.smtp.auth"), "true");
			Assert.assertEquals(aux.getProperty("hotmail.mail.smtp.port"), "25");
			Assert.assertEquals(aux.getProperty("hotmail.mail.smtp.starttls.enable"), "true");
			Assert.assertEquals(aux.getProperty("hotmail.mail.from"), "correoDePrubaDDS@gmail.com");
			Assert.assertEquals(aux.getProperty("hotmail.mail.user"), "correoDePrubaDDS@gmail.com");
			Assert.assertEquals(aux.getProperty("hotmail.mail.password"), "diseñoDeSistemas ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
