package modelo.clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Evento {
	private String nombre;
	private String ciudad;
	private Calendar fecha;

	public Evento(String nombreEvento, String ciudad, Calendar fecha) {
		this.nombre = nombreEvento;
		this.ciudad = ciudad;
		this.fecha = fecha;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Calendar getFecha() {
		return fecha;
	}

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

	public Date diaAnterior() {
		this.fecha.add(Calendar.DATE, -1);
		return this.fecha.getTime();
	}

	public void iniciar() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		System.out.println("Voy a " + this.getNombre() + " en " + this.getCiudad() + " en la fecha "
				+ dateFormat.format(this.fecha.getTime()));

	}
}
