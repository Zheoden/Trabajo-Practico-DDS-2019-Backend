package modelo.interfaces;

import java.util.Properties;

import modelo.clases.Evento;
import modelo.clases.Usuario;

public interface NotificationManager {

	Properties getPropertiesForServer(String tipoServidor) throws Exception;
	Properties getProyectProperties() throws Exception;
	void emailSend(String tipoServidor, Usuario user, Evento evento) throws Exception;
	
}
