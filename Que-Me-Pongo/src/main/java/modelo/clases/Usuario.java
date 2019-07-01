package modelo.clases;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Usuario implements Job {
	
	Suscripcion suscripcion;
	ArrayList<Guardarropas> guardarropas = new ArrayList<Guardarropas>();
	ArrayList<Evento> eventos = new ArrayList<Evento>();
	
	public Usuario(ArrayList <Guardarropas> guardaRopas, Suscripcion unaSuscripcion) throws Exception  {
		this.setGuardaRopas(guardaRopas);
		this.setSuscripcion(unaSuscripcion);
	}
	
	public List<Atuendo> todosPosiblesAtuendosPorGuardarropa() {
		List<Atuendo> atuendosValidos = new ArrayList<Atuendo>();
		this.guardarropas.forEach( guardarropa -> atuendosValidos.addAll(guardarropa.atuendosValidosParaAhora()));
		return atuendosValidos;
	}
	
	public Suscripcion getSuscripcion() {
		return suscripcion;
	}
	
	public void setSuscripcion(Suscripcion unaSuscripcion) {
		suscripcion = unaSuscripcion;
	}
	
	public boolean listaDeGuardarropasValida(ArrayList<Guardarropas> guardaRopas) {
		return guardaRopas.stream().allMatch(unGuardarropa -> unGuardarropa.tamanioGuardarropas() <= suscripcion.cantidadPrendasPermitidas());
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
	
	public void cargarEvento(Evento unEvento)
	{
	this.eventos.add(unEvento);	
	unEvento.recordatorio();
	}
	
	public void irAEventos()
	{
	this.eventos.forEach(evento -> evento.iniciar());	
	}
	
	
	public void irAElEvento(Evento unEvento) throws Exception 
	{
	if(this.eventos.contains(unEvento))
	{
    unEvento.iniciar();
	}
	else
	{
	throw new Exception("No estas inivitado al evento " + unEvento.getNombre());	
	}
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		this.getGuardaRopas().forEach(guarda-> System.out.println("Me sugirieron "+ 
	    guarda.atuendosValidosParaAhora().toString()));
		
	}
}
