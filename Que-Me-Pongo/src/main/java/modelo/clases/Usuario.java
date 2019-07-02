package modelo.clases;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Usuario {

	Suscripcion suscripcion;
	ArrayList<Guardarropas> guardarropas = new ArrayList<Guardarropas>();
	ArrayList<Evento> eventos = new ArrayList<Evento>();

	public Usuario(ArrayList<Guardarropas> guardaRopas, Suscripcion unaSuscripcion) {
		this.setGuardaRopas(guardaRopas);
		this.setSuscripcion(unaSuscripcion);
	}

	public List<Atuendo> todosPosiblesAtuendosPorGuardarropa() {
		List<Atuendo> atuendosValidos = new ArrayList<Atuendo>();
		this.guardarropas.forEach(guardarropa -> atuendosValidos.addAll(guardarropa.atuendosValidosParaAhora()));
		return atuendosValidos;
	}

	public Suscripcion getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(Suscripcion unaSuscripcion) {
		suscripcion = unaSuscripcion;
	}

	public boolean listaDeGuardarropasValida(ArrayList<Guardarropas> guardaRopas) {
		return guardaRopas.stream().allMatch(
				unGuardarropa -> unGuardarropa.tamanioGuardarropas() <= suscripcion.cantidadPrendasPermitidas());
	}

	public ArrayList<Guardarropas> getGuardaRopas() {
		return guardarropas;
	}

	public void setGuardaRopas(ArrayList<Guardarropas> guardaRopas) {
		this.guardarropas = guardaRopas;
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public Evento getEvento(Evento unEvento) {
		return this.eventos.stream().filter(evento -> evento.getNombre() == unEvento.getNombre()).findFirst().get();
	}

	public void cargarEvento(Evento unEvento) throws ParseException{
		this.eventos.add(unEvento);
		this.getEvento(unEvento).recordatorio(1); 	//Avisa del evento un minuto antes en este caso
		
	}
	public void irAEventos() {
		this.eventos.forEach(evento -> evento.iniciar());
	}

	public void irAElEvento(Evento unEvento) {
		if (this.eventos.contains(unEvento)) {
			unEvento.iniciar();
		} else {
			System.out.println("No estas invitado al evento " + unEvento.getNombre());
		}
	}

}
