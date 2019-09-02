package modelo.clases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import modelo.interfaces.Suscripcion;
import utils.Utils;


public class Usuario implements Job {

	Sensibilidad sensibilidadCuerpo;
	Suscripcion suscripcion;
	String email;
	ArrayList<Guardarropas> guardarropas = new ArrayList<Guardarropas>();
	ArrayList<Evento> eventos = new ArrayList<Evento>();
	String Email;
	String NumeroTelefono;

	public Usuario(ArrayList<Guardarropas> guardarropas, Suscripcion unaSuscripcion, String email, String numeroTelefono, Sensibilidad cuerpoSensible) {

		if (guardarropas.stream().allMatch( guardarropa -> unaSuscripcion.cantidadPrendasPermitidas(guardarropa.tamanioGuardarropas()))) {
			this.setGuardaRopas(guardarropas);			
		} else {
			System.out.print("No se puede asignar esta lista de guardarropas porque no es complatible con la subscripcion seleccionada.");
		}

		this.setSuscripcion(unaSuscripcion);
		this.setSensibilidadCuerpo(cuerpoSensible);
		this.setEmail(email);
		this.setNumeroTelefono(numeroTelefono);
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

	public void agregarPrendaAGuardaRopas(Prenda unaPrenda, Guardarropas guardaRopas) {
		if (this.suscripcion.cantidadPrendasPermitidas(guardaRopas.tamanioGuardarropas())) {
			guardaRopas.addPrenda(unaPrenda);
		} else {
			System.out.println(
					"El guardaRopas posee la cantidad maxima de prendas permitidas por la suscripcion del ususario");
		}
	}

	public Evento getEvento(Evento unEvento) {
		return this.eventos.stream().filter(evento -> evento.getNombre() == unEvento.getNombre()).findFirst().get();
	}

	public void cargarEvento(Evento unEvento) throws Exception {
		this.eventos.add(unEvento);
		//Utils.recordatorio(1, unEvento, this); // Avisa del evento un minuto antes en este caso
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

	public ArrayList<Guardarropas> getGuardaRopas() {
		return this.guardarropas;
	}

	public void setGuardaRopas(ArrayList<Guardarropas> guardaRopas) {
		this.guardarropas = guardaRopas;
	}

	public Suscripcion getSuscripcion() {
		return this.suscripcion;
	}

	public void setSuscripcion(Suscripcion unaSuscripcion) {
		this.suscripcion = unaSuscripcion;
	}

	public ArrayList<Evento> getEventos() {
		return this.eventos;
	}

	public ArrayList<Evento> setEventos(ArrayList<Evento> eventos) {
		return this.eventos = eventos;
	}

	public ArrayList<Guardarropas> getGuardarropas() {
		return guardarropas;
	}

	public void setGuardarropas(ArrayList<Guardarropas> guardarropas) {
		this.guardarropas = guardarropas;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getNumeroTelefono() {
		return NumeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		NumeroTelefono = numeroTelefono;
	}	

	public ArrayList<Guardarropas> getGuardarropas() {
		return guardarropas;
	}
	
	public Sensibilidad getSensibilidadCuerpo() {
		return sensibilidadCuerpo;
	}

	public void setSensibilidadCuerpo(Sensibilidad sensibilidad) {
		this.sensibilidadCuerpo = sensibilidad;
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
       //La idea es obtener todos los eventos del usuario de la bd
	   // por el momento se hardcodea para los test
	   //List<Evento> = Evento.findAll();
	   //El mail tmb lo harcodeo pero se obtendria todo por bd
	   //Usuario user = findUserById(this.getId());
		
	   List<Evento> eventos = new ArrayList<Evento>();
	   Calendar fecha1 = GregorianCalendar.getInstance();
	   fecha1.set(2019, 10, 12);
	   Evento alamo = new Evento("ir al alamo","palermo",fecha1);
	   this.setEmail("axelfulop@hotmail.com");
	   eventos.add(alamo);
	   eventos.forEach(evento -> {
			try {
				Utils.emailSender("gmail", this, evento);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
