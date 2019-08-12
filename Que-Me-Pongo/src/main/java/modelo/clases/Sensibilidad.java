package modelo.clases;

import java.util.List;
import java.util.Optional;

import modelo.dtos.Categoria;
import modelo.dtos.TipoPrenda;

public class Sensibilidad {
	
	//Lo Establezco Por Ahora Como Boolean - Cambiaria A String A Menos Que Se Considere Como Sensibilidad Normal
	private boolean calurosoGlobal;
	private boolean calurosoManos;
	private boolean calurosoCuello;
	private boolean calurosoCabeza;
	
	public Sensibilidad() {}
	
	public Sensibilidad (boolean global, boolean manos, boolean cuello, boolean cabeza) {
		this.calurosoGlobal = global;
		this.calurosoManos = manos;
		this.calurosoCuello = cuello;
		this.calurosoCabeza = cabeza;
	}

	public boolean esCalurosoGeneralmente() {
		return calurosoGlobal;
	}

	public boolean esCalurosoDeManos() {
		return calurosoManos;
	}

	public boolean esCalurosoDeCuello() {
		return calurosoCuello;
	}

	public boolean esCalurosoDeCabeza() {
		return calurosoCabeza;
	}

	public void setCalurosoGlobal(boolean calurosoGlobal) {
		this.calurosoGlobal = calurosoGlobal;
	}

	public void setCalurosoManos(boolean calurosoManos) {
		this.calurosoManos = calurosoManos;
	}

	public void setCalurosoCuello(boolean calurosoCuello) {
		this.calurosoCuello = calurosoCuello;
	}

	public void setCalurosoCabeza(boolean calurosoCabeza) {
		this.calurosoCabeza = calurosoCabeza;
	}
	
	//Funcion Principal Para Calificar Los Distintos Atuendos Aceptados Para Un Evento
	public void calificarAtuendosAceptados(Evento unEvento, List<Guardarropas> guardarropas) {

		unEvento.getAtuendosAceptados().stream()
				.forEach(atuendoAceptado -> this.calificarSegunSensibilidadGlobal(atuendoAceptado, guardarropas));	
		unEvento.getAtuendosAceptados().stream()
				.forEach(atuendoAceptado -> this.calificarSegunSensibilidadManos(atuendoAceptado, guardarropas));
		unEvento.getAtuendosAceptados().stream()
				.forEach(atuendoAceptado -> this.calificarSegunSensibilidadCuello(atuendoAceptado, guardarropas));
		unEvento.getAtuendosAceptados().stream()
				.forEach(atuendoAceptado -> this.calificarSegunSensibilidadCabeza(atuendoAceptado, guardarropas));

	}
	
	//Funcion Para Ver Que Hacer En Cuanto A La Sensibilidad Global
	public void calificarSegunSensibilidadGlobal(Atuendo unAtuendo, List<Guardarropas> guardarropas) {
		
		//Si Es Caluroso Remuevo Las Prendas Que Cumplen La Condicion
		if (esCalurosoGeneralmente()) {
			this.retirarPrendasCalurosas(unAtuendo);
		}
		
		else {}
		
	}
	
	private void retirarPrendasCalurosas(Atuendo unAtuendo) {
		unAtuendo.getPrendas().removeIf(
				prenda -> prenda.getTipo().categoria() != Categoria.CALZADO && prenda.getTipo().nivelDeCapa() > 0);
	}

	//Funcion Para Ver Que Hacer En Cuanto A La Sensibilidad De Manos
	public void calificarSegunSensibilidadManos(Atuendo unAtuendo, List<Guardarropas> guardarropas) {
		if (this.esCalurosoDeManos()) {
			unAtuendo.getPrendas().stream()
					.forEach(unaPrenda -> this.sacarPrendaExtra(unAtuendo, unaPrenda, TipoPrenda.GUANTES));
		} else {
			this.agregarPrendaFaltante(unAtuendo, guardarropas, TipoPrenda.GUANTES);
		}
	}

	//Funcion Para Ver Que Hacer En Cuanto A La Sensibilidad De Cuello
	public void calificarSegunSensibilidadCuello(Atuendo unAtuendo, List<Guardarropas> guardarropas) {
		if (this.esCalurosoDeCuello()) {
			unAtuendo.getPrendas().stream()
					.forEach(unaPrenda -> this.sacarPrendaExtra(unAtuendo, unaPrenda, TipoPrenda.BUFANDA));
		} else {
			this.agregarPrendaFaltante(unAtuendo, guardarropas, TipoPrenda.BUFANDA);
		}
	}

	//Funcion Para Ver Que Hacer En Cuanto A La Sensibilidad De Cabeza
	public void calificarSegunSensibilidadCabeza(Atuendo unAtuendo, List<Guardarropas> guardarropas) {
		if (this.esCalurosoDeCabeza()) {
			unAtuendo.getPrendas().stream()
					.forEach(unaPrenda -> this.sacarPrendaExtra(unAtuendo, unaPrenda, TipoPrenda.GORRA));
		} else {
			this.agregarPrendaFaltante(unAtuendo, guardarropas, TipoPrenda.GORRA);
		}
	}

	private void agregarPrendaFaltante(Atuendo unAtuendo, List<Guardarropas> guardarropas, TipoPrenda unTipoPrenda) {
		if (unAtuendo.getPrendas().stream().noneMatch(prenda -> prenda.getTipo().equals(unTipoPrenda))) {
			
			//Busco El Guardarropa Correspondiente A Las Prendas Que Forman El Atuendo
			Optional<Guardarropas> guardarropaConteniente = guardarropas.stream().filter(guardarropa -> devolverGuardarropaEnBaseAlAtuendo(guardarropa, unAtuendo.getPrendas().get(0))).findFirst();
			this.buscarPrendaEnElGuardarropaIndicado(unAtuendo, guardarropaConteniente, unTipoPrenda);
		}
	}

	private boolean devolverGuardarropaEnBaseAlAtuendo(Guardarropas unGuardarropa, Prenda unaPrenda){
		return unGuardarropa.getPrendas().stream().anyMatch(prenda -> prenda.equals(unaPrenda));
	}
	
	//Funcion Para Buscar La Prenda Especifica En Guardarropa Y De Tenerla Agregarla Al Atuendo, Si No Esta
	private void buscarPrendaEnElGuardarropaIndicado(Atuendo unAtuendo, Optional<Guardarropas> unGuardarropa,
			TipoPrenda unTipoPrenda) {
		
		Optional<Prenda> prendaABuscar = unAtuendo.getPrendas().stream()
				.filter(prenda -> prenda.getTipo().equals(unTipoPrenda)).findFirst();
		if (!prendaABuscar.isPresent()) {
			
			Guardarropas elGuardarropa = unGuardarropa.get();
			elGuardarropa.getPrendas().stream()
					.forEach(prenda -> agregarPrendaSiEstaEnElGuardarropa(unAtuendo, prenda, unTipoPrenda));
		}
	}

	private void agregarPrendaSiEstaEnElGuardarropa(Atuendo unAtuendo, Prenda unaPrenda, TipoPrenda unTipoPrenda) {
		if (unaPrenda.getTipo().equals(unTipoPrenda)) {
			unAtuendo.addPrenda(unaPrenda);
		}
	}

	//Funcion Para Remover Las Prenda Especifica
	private void sacarPrendaExtra(Atuendo unAtuendo, Prenda prendaARemover, TipoPrenda unTipoPrenda) {

		if (prendaARemover.getTipo().equals(unTipoPrenda)) {
			unAtuendo.getPrendas().remove(prendaARemover);
		}
	}
}