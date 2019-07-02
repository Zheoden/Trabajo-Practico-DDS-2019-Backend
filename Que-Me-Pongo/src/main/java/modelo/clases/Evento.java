package modelo.clases;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
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

	public void recordatorio(int minutosAnteriores) throws ParseException {

		int minutos = getFecha().get(Calendar.MINUTE);
		int minutosAntes = minutos - minutosAnteriores;
		Calendar aux =  GregorianCalendar.getInstance();
		aux.set(Calendar.MINUTE,minutosAntes);
		Date dateAux = aux.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String despertador = dateFormat.format(dateAux);
	
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//Date date = dateFormat.parse(dateFormat.format(getFecha().getTime()));
		Timer t=new Timer();
		t.schedule(new TimerTask() {
		    public void run() {
		    	System.out.println("Recordatorio de evento Ir al alamo");
		    }
		},new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(despertador));
	   
	
		
		
		/*ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		Runnable record = () -> System.out.println("Recordatorio de evento " + this.getNombre());
		ses.schedule(record, minutosAntes, TimeUnit.MINUTES);
		ses.shutdown();*/

	}

	public void iniciar() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		System.out.println("Voy a " + this.getNombre() + " en " + this.getCiudad() + " en la fecha "
				+ dateFormat.format(this.fecha.getTime()));

	}
}
