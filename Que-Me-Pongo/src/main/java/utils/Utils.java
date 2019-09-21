package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.smtp.SMTPTransport;

import java.util.Properties;

import modelo.clases.Atuendo;
import modelo.clases.Evento;
import modelo.clases.Usuario;

public class Utils {
	@SuppressWarnings("resource")
	public static String readFileFromResources(String path) {

		InputStream resourceStream = Utils.class.getClassLoader().getResourceAsStream(path);
		if (resourceStream == null)
			return "";

		return new Scanner(resourceStream, "UTF-8").useDelimiter("\\A").next();
	}

	public static Double kelvinToCelsius(Double kelvin) {
		return kelvin - 273.00;
	}
	
	public static void recordatorio(int minutosAnteriores, Evento evento, Usuario usuario) {
		int minutos = evento.getFecha().get(Calendar.MINUTE);
		int minutosAntes = minutos - minutosAnteriores;
		Calendar aux = GregorianCalendar.getInstance();
		aux.set(Calendar.MINUTE, minutosAntes);
		Date dateAux = aux.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String despertador = dateFormat.format(dateAux);

		Timer t = new Timer();
		try {
			t.schedule(new TimerTask() {
				public void run() {
					List<Atuendo> atuendosValidos = usuario.todosPosiblesAtuendosPorGuardarropaParaEvento(evento);
					System.out.println(atuendosValidos);
					System.out.print("Recordatorio de evento Ir al alamo");
				}
			}, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(despertador));
		} catch (ParseException e) {
			System.out.println(e);
		}
	}
	
	public static Properties getProyectProperties() throws Exception {
		Properties prop = new Properties();
		InputStream input = null ;
		try {
			input = new FileInputStream("system.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("No se encuentra el archivo de properties.");
		}

		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("No se pudo cargar el archivo de properties.");
		}	

		return prop;
	}
	
	public static Properties getPropertiesForServer(String tipoServidor) throws Exception {
		Properties properties = Utils.getProyectProperties();
		Properties aux = new Properties();
	    Set<String> propertyNames = properties.stringPropertyNames();
	    for (String name : propertyNames) {
	    	if(name.matches(".*" + tipoServidor + ".*")) {
		    	String propertyValue = properties.getProperty(name);
		    	aux.setProperty(name.substring(tipoServidor.length() + 1), propertyValue);
	    	}
	    }
	    return aux;
	}

	
}
	