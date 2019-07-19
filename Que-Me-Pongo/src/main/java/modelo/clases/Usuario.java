package modelo.clases;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import modelo.interfaces.Suscripcion;
import modelo.utils.Utils;

public class Usuario {

	Suscripcion suscripcion;
	ArrayList<Guardarropas> guardarropas = new ArrayList<Guardarropas>();
	ArrayList<Evento> eventos = new ArrayList<Evento>();

	public Usuario(ArrayList<Guardarropas> guardaRopas, Suscripcion unaSuscripcion) {
		this.setGuardaRopas(guardaRopas);
		this.setSuscripcion(unaSuscripcion);
	}

	public List<Atuendo> todosPosiblesAtuendosPorGuardarropaParaAhora() {
		List<Atuendo> atuendosValidos = new ArrayList<Atuendo>();
		this.guardarropas.forEach(guardarropa -> atuendosValidos.addAll(guardarropa.atuendosValidosParaAhora()));
		return atuendosValidos;
	}

	public List<Atuendo> todosPosiblesAtuendosPorGuardarropaParaEvento(Evento evento) {
		List<Atuendo> atuendosValidos = new ArrayList<Atuendo>();
		this.guardarropas.forEach(guardarropa -> atuendosValidos.addAll(guardarropa.atuendosValidosParaEvento(evento)));
		return atuendosValidos;
	}

	public Suscripcion getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(Suscripcion unaSuscripcion) {
		suscripcion = unaSuscripcion;
	}

	public void agregarAGuardaRopas(Prenda unaPrenda, Guardarropas guardaRopas) {
		if (this.suscripcion.cantidadPrendasPermitidas(guardaRopas.tamanioGuardarropas())) {
			guardaRopas.addPrenda(unaPrenda);
		} else {
			System.out.println(
					"El guardaRopas posee la cantidad maxima de prendas permitidas por la suscripcion del ususario");
		}
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

	public void cargarEvento(Evento unEvento) throws ParseException {
		this.eventos.add(unEvento);
		Utils.recordatorio(1, unEvento, this); // Avisa del evento un minuto antes en este caso
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

	public void aceptarAtuendo (Atuendo unAtuendo) {
		unAtuendo.getEvento().aceptarAtuendo(unAtuendo);
	}	
	
	public void rechazarAtuendos (Atuendo unAtuendo) {
		unAtuendo.getEvento().rechazarAtuendo(unAtuendo);
	}	
	
	public void deshacer (Evento evento) {
		evento.deshacer();
	}	
	
}
