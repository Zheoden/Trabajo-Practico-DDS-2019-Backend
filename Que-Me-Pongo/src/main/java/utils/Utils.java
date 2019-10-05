package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

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
		InputStream input = null;
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

	public static String generarHash256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static Properties getPropertiesForServer(String tipoServidor) throws Exception {
		Properties properties = Utils.getProyectProperties();
		Properties aux = new Properties();
		Set<String> propertyNames = properties.stringPropertyNames();
		for (String name : propertyNames) {
			if (name.matches(".*" + tipoServidor + ".*")) {
				String propertyValue = properties.getProperty(name);
				aux.setProperty(name.substring(tipoServidor.length() + 1), propertyValue);
			}
		}
		return aux;
	}

}
