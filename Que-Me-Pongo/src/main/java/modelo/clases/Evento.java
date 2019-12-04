package modelo.clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Evento")
public class Evento {

	@Id
	@GeneratedValue
	
	private Long id;
	
	String nombre;
	
	String ciudad;
	
	Calendar fecha;

	@OneToMany(mappedBy = "eventoAA", cascade = CascadeType.ALL)
	List<Atuendo> atuendosAceptados = new ArrayList<>();
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	List<Atuendo> atuendosMovimientos = new ArrayList<>();
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	@JsonIgnore
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Evento() {
	}

	public Evento(String nombreEvento, String ciudad, Calendar fecha) {
		this.nombre = nombreEvento;
		this.ciudad = ciudad;
		this.fecha = fecha;
	}

	public Date diaAnterior() {
		this.fecha.add(Calendar.DATE, -1);
		return this.fecha.getTime();
	}

	public void iniciar() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		System.out.println("Voy a " + this.getNombre() + " en " + this.getCiudad() + " en la fecha "
				+ dateFormat.format(this.fecha.getTime()));
	}

	public void aceptarAtuendo(Atuendo unAtuendo) {
		unAtuendo.aceptar();
		this.atuendosAceptados.add(unAtuendo);
	}

	public void rechazarAtuendo(Atuendo unAtuendo) {
		unAtuendo.rechazar();
		if(!this.atuendosMovimientos.contains(unAtuendo)) {
		this.atuendosMovimientos.add(unAtuendo);
		}
	}

	public void deshacer() {
		Atuendo unAtuendo = atuendosMovimientos.get(atuendosMovimientos.size() - 1); // necesito el ultimo elemento de
																						// la lista de movimientos
		if (unAtuendo.getAceptado()) { // si fue aceptado lo saco de la lista
			atuendosAceptados.remove(atuendosAceptados.size() - 1); // si fue aceptado lo saco de la lista
			atuendosMovimientos.remove(atuendosMovimientos.size() - 1); // tambien de la lista de movimientos
		} else {
			atuendosMovimientos.remove(atuendosMovimientos.size() - 1); // solo lo saco de la lista de movimientos,
																		// consultar si deberiamos hacer otra cosa
		}
	}

	public List<Atuendo> getAtuendosAceptados() {

		return this.atuendosAceptados;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAtuendosAceptados(ArrayList<Atuendo> atuendosAceptados) {
		this.atuendosAceptados = atuendosAceptados;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	public Calendar getFecha() {
		return fecha;
	}
	
	@JsonIgnore
	public LocalDate getFechaEvento() {
		return LocalDateTime.ofInstant(this.fecha.toInstant(), this.fecha.getTimeZone().toZoneId()).toLocalDate();
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Atuendo> getAtuendosMovimientos() {
		return atuendosMovimientos;
	}

	public void setAtuendosMovimientos(List<Atuendo> atuendosMovimientos) {
		this.atuendosMovimientos = atuendosMovimientos;
	}

	public void setId(Long id) {
		this.id = id;
	}
}