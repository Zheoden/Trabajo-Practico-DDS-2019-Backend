package modelo.clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

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

	public void recordatorio() {

		  //Se ejecutaria 1 vez antes de 5 minutos del evento
		  ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
				  ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(
					        () -> System.out.println("Recordatorio de evento " +this.getNombre()),
					        0,
					        5, TimeUnit.MINUTES);
					    scheduler.schedule(
					        () -> {
					            future.cancel(true);
					            scheduler.shutdown();
					        }, 1, TimeUnit.MINUTES);
					    scheduler.shutdown();
	}

			


	

	public void iniciar() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Voy a " + this.getNombre() + " en " + this.getCiudad() + " en la fecha "
				+ dateFormat.format(this.fecha.getTime()));

	}
}
