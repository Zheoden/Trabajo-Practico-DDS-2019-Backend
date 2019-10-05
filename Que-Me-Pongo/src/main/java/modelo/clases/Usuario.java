package modelo.clases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import modelo.interfaces.Suscripcion;
import utils.emailSender;

@Entity
@Table(name = "usuario")
@Inheritance (strategy= InheritanceType.SINGLE_TABLE)
public class Usuario implements Job {

	@Id
	@GeneratedValue
	long id;
	@Column (name = "user")
    String nombreUsuario;
	@Column (name = "password")
    String passwordUsuario;
	@Transient
    int rangoDeSensibilidad; //Numero negativo es friolento (transforma de 20 grados a 15 grados por ejemplo). Numero positivo es caruloso (transfroma de 15 grados a 20 grados por ejemplo)
	@Transient
	Suscripcion suscripcion;
	@Transient
	String email;
	@Transient
	ArrayList<Guardarropas> guardarropas = new ArrayList<Guardarropas>();
	@Transient
	ArrayList<Evento> eventos = new ArrayList<Evento>();
	@Transient
	String Email;
	@Transient
	String NumeroTelefono;
	
	public Usuario() {}

	public Usuario(ArrayList<Guardarropas> guardarropas, Suscripcion unaSuscripcion, String email, String numeroTelefono, int rangoDeSensibilidad) {

		if (guardarropas.stream().allMatch( guardarropa -> unaSuscripcion.cantidadPrendasPermitidas(guardarropa.tamanioGuardarropas()))) {
			this.setGuardaRopas(guardarropas);			
		} else {
			System.out.print("No se puede asignar esta lista de guardarropas porque no es complatible con la subscripcion seleccionada.");
		}

		this.setSuscripcion(unaSuscripcion);
		this.setSensibilidadCuerpo(rangoDeSensibilidad);
		this.setEmail(email);
		this.setNumeroTelefono(numeroTelefono);
	}
	
	public ArrayList<Atuendo> todosLosAtuendosAceptados() {
		List<ArrayList<Atuendo>> todosLosAtuendos = this.getEventos().stream().map(evento -> evento.getAtuendosAceptados()).collect(Collectors.toList());
		ArrayList<Atuendo> aux = new ArrayList<Atuendo>();
		todosLosAtuendos.forEach( listaAtuendos -> {
			listaAtuendos.forEach(atuendo -> aux.add(atuendo));
		});
		return aux;
	}
	
	public void evaluarRangoSensibilidad(Evento evento, Atuendo atuendo) {
		double temperatura = new AdministrarProveedores().obtenerTemperatura(evento.getFechaEvento());

		if (temperatura > 11.0 && temperatura < 26.0) {
			int puntosDeAbrigo = atuendo.getPrendas().stream().mapToInt(prenda -> prenda.getTipo().nivelDeAbrigo()).sum();
			Abrigo nivelDeAbrigo = Abrigo.obtenerNivelDeAbrigo(temperatura);
			int indice = nivelDeAbrigo.getNivelesDeAbrigo().indexOf(puntosDeAbrigo);
			
			if ( nivelDeAbrigo.getNivelesDeAbrigo().size() - (indice + 1) == 0 ) { // es friolento
				this.rangoDeSensibilidad--;
			}

			if ( nivelDeAbrigo.getNivelesDeAbrigo().size() - (indice + 1) == 2 ) { // es caluroso
				this.rangoDeSensibilidad++;
			}
		}
	}

	public List<Atuendo> todosPosiblesAtuendosPorGuardarropaParaAhora() {
		List<Atuendo> atuendosValidos = new ArrayList<Atuendo>();
		this.guardarropas.forEach(guardarropa -> atuendosValidos.addAll(guardarropa.atuendosValidosParaAhora(this.rangoDeSensibilidad)));
		return atuendosValidos;
	}

	public List<Atuendo> todosPosiblesAtuendosPorGuardarropaParaEvento(Evento evento) {
		List<Atuendo> atuendosValidos = new ArrayList<Atuendo>();
		this.guardarropas.forEach(guardarropa -> atuendosValidos.addAll(guardarropa.atuendosValidosParaEvento(evento, this.rangoDeSensibilidad)));
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPasswordUsuario() {
		return passwordUsuario;
	}

	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}

	public void cargarEvento(Evento unEvento) {
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
		this.evaluarRangoSensibilidad(unAtuendo.getEvento(), unAtuendo);
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
	
	public int getSensibilidadCuerpo() {
		return rangoDeSensibilidad;
	}

	public void setSensibilidadCuerpo(int sensibilidad) {
		this.rangoDeSensibilidad = sensibilidad;
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
       //La idea es obtener todos los eventos del usuario de la bd
	   // por el momento se hardcodea para los test
	   //List<Evento> = Evento.findAll();
	   //El mail tmb lo harcodeo pero se obtendria todo por bd
	   //Usuario user = findUserById(this.getId());
		
	emailSender notification = new emailSender();
	   List<Evento> eventos = new ArrayList<Evento>();
	   Calendar fecha1 = GregorianCalendar.getInstance();
	   fecha1.set(2019, 10, 12);
	   Evento alamo = new Evento("ir al alamo","palermo",fecha1);
	   this.setEmail("facufulop@hotmail.com");
	   eventos.add(alamo);
	   eventos.forEach(evento -> {
			try {
				notification.emailSend("gmail", this, evento);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
