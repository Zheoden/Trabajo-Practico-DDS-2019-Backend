package modelo.clases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.*;
import modelo.interfaces.Suscripcion;

import utils.Utils;
import utils.EmailSender;

@Entity
@Table(name = "Usuario")

public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	String username;
	String password;
	int rangoDeSensibilidad; // Numero negativo es friolento (transforma de 20 grados a 15 grados por
								// ejemplo). Numero positivo es caruloso (transfroma de 15 grados a 20 grados
								// por ejemplo)

	@Transient
	Suscripcion suscripcion;

	String email;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "GuardarropaPorUsuario", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "guardarropa_id"))
	List<Guardarropas> guardarropas = new ArrayList<Guardarropas>();

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Evento> eventos;

	String NumeroTelefono;

	public Usuario() {
	}

	public Usuario(ArrayList<Guardarropas> guardarropas, Suscripcion unaSuscripcion, String email,
			String numeroTelefono, int rangoDeSensibilidad) {

		if (guardarropas.stream()
				.allMatch(guardarropa -> unaSuscripcion.cantidadPrendasPermitidas(guardarropa.tamanioGuardarropas()))) {
			this.setGuardarropas(guardarropas);
		} else {
			System.out.print(
					"No se puede asignar esta lista de guardarropas porque no es compatible con la subscripcion seleccionada.");
		}

		this.setSuscripcion(unaSuscripcion);
		this.setSensibilidadCuerpo(rangoDeSensibilidad);
		this.setEmail(email);
		this.setNumeroTelefono(numeroTelefono);
	}

	public List<Atuendo> todosLosAtuendosAceptados() {
		List<List<Atuendo>> todosLosAtuendos = this.getEventos().stream()
				.map(evento -> evento.getAtuendosAceptados()).collect(Collectors.toList());
		ArrayList<Atuendo> aux = new ArrayList<Atuendo>();
		todosLosAtuendos.forEach(listaAtuendos -> {
			listaAtuendos.forEach(atuendo -> aux.add(atuendo));
		});
		return aux;
	}

	public void evaluarRangoSensibilidad(Evento evento, Atuendo atuendo) {
		double temperatura = new AdministrarProveedores().obtenerTemperatura(evento.getFechaEvento());

		if (temperatura > 11.0 && temperatura < 26.0) {
			int puntosDeAbrigo = atuendo.getPrendas().stream().mapToInt(prenda -> prenda.getTipo().nivelDeAbrigo())
					.sum();
			Abrigo nivelDeAbrigo = Abrigo.obtenerNivelDeAbrigo(temperatura);
			int indice = nivelDeAbrigo.getNivelesDeAbrigo().indexOf(puntosDeAbrigo);

			if (nivelDeAbrigo.getNivelesDeAbrigo().size() - (indice + 1) == 0) { // es friolento
				this.rangoDeSensibilidad--;
			}

			if (nivelDeAbrigo.getNivelesDeAbrigo().size() - (indice + 1) == 2) { // es caluroso
				this.rangoDeSensibilidad++;
			}
		}
	}

	public List<Atuendo> todosPosiblesAtuendosPorGuardarropaParaAhora() {
		List<Atuendo> atuendosValidos = new ArrayList<Atuendo>();
		this.guardarropas.forEach(
				guardarropa -> atuendosValidos.addAll(guardarropa.atuendosValidosParaAhora(this.rangoDeSensibilidad)));
		return atuendosValidos;
	}

	public List<Atuendo> todosPosiblesAtuendosPorGuardarropaParaEvento(Evento evento) {
		List<Atuendo> atuendosValidos = new ArrayList<Atuendo>();
		this.guardarropas.forEach(guardarropa -> atuendosValidos
				.addAll(guardarropa.atuendosValidosParaEvento(evento, this.rangoDeSensibilidad)));
		atuendosValidos.stream().forEach(atuendo -> atuendo.getPrendas().stream().forEach(prenda -> prenda.agregarseAlAtuendo(atuendo)));
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Utils.generarHash256(password);
	}

	public void cargarEvento(Evento unEvento) {
		if (eventos == null) {
			eventos = new ArrayList<Evento>();
		}
		eventos.add(unEvento);
		// Utils.recordatorio(1, unEvento, this); // Avisa del evento un minuto antes en
		// este caso
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

	public void aceptarAtuendo(Atuendo unAtuendo) {
		unAtuendo.getEvento().aceptarAtuendo(unAtuendo);
		this.evaluarRangoSensibilidad(unAtuendo.getEvento(), unAtuendo);
	}

	public void rechazarAtuendos(Atuendo unAtuendo) {
		unAtuendo.getEvento().rechazarAtuendo(unAtuendo);
	}

	public void deshacer(Evento evento) {
		evento.deshacer();
	}

   public void removeGuardarropas(List<Guardarropas> guardarropas)
   {
	this.guardarropas.removeAll(guardarropas);
   }

	public Suscripcion getSuscripcion() {
		return this.suscripcion;
	}

	public void setSuscripcion(Suscripcion unaSuscripcion) {
		this.suscripcion = unaSuscripcion;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public List<Evento> setEventos(ArrayList<Evento> eventos) {
		return this.eventos = eventos;
	}

	public void setGuardarropas(ArrayList<Guardarropas> guardarropas) {
		this.guardarropas = guardarropas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String setEmail) {
		email = setEmail;
	}

	public String getNumeroTelefono() {
		return NumeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		NumeroTelefono = numeroTelefono;
	}

	public List<Guardarropas> getGuardarropas() {
		return guardarropas;
	}

	public int getSensibilidadCuerpo() {
		return rangoDeSensibilidad;
	}

	public void setSensibilidadCuerpo(int sensibilidad) {
		this.rangoDeSensibilidad = sensibilidad;
	}

	public Void notifyUser(Void t) {

		// La idea es obtener todos los eventos del usuario de la bd
		// por el momento se hardcodea para los test
		// List<Evento> = Evento.findAll();
		// El mail tmb lo harcodeo pero se obtendria todo por bd
		// Usuario user = findUserById(this.getId());

		EmailSender notification = new EmailSender();
		List<Evento> eventos = new ArrayList<Evento>();
		Calendar fecha1 = GregorianCalendar.getInstance();
		fecha1.set(2019, 10, 12);
		Evento alamo = new Evento("ir al alamo", "palermo", fecha1);
		this.setEmail("sculian@gmail.com");
		eventos.add(alamo);
		eventos.forEach(evento -> {
			try {
				notification.emailSend("gmail", this, evento);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return t;
	}
}
