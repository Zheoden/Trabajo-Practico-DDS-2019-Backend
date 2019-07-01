package modelo.clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Evento {
	private String nombre;
	private Ciudad ciudad;
	private Calendar fecha;

	public Evento(String nombreEvento, Ciudad ciudad, Calendar fecha) {
		this.nombre = nombreEvento;
		this.ciudad = ciudad;
		this.fecha = fecha;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
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
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler scheduler = sf.getScheduler();

			JobDetail job = JobBuilder.newJob(Usuario.class).build();

			Trigger crontrigger = TriggerBuilder.newTrigger().startAt(this.diaAnterior()).endAt(this.getFecha().getTime()).build();

			scheduler.start();
			scheduler.scheduleJob(job, crontrigger);
		} catch (SchedulerException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void iniciar() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Voy a " + this.getNombre() + " en " + this.ciudad.getNombre() + " en la fecha "
				+ dateFormat.format(this.fecha.getTime()));

	}
}
