package modelo.interfaces;

import modelo.clases.Evento;
import modelo.clases.Usuario;

public interface Notificador {
	
	public void notificar(String servidor,Usuario user,Evento evento);

}
