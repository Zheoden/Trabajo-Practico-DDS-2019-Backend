package modelo.clases;

import java.util.List;
import java.util.Optional;

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
	
	public void calificarAtuendosAceptados(Evento unEvento, List<Guardarropas> guardarropas) {

		unEvento.getAtuendosAceptados().stream()
				.forEach(atuendoAceptado -> this.calificarSegunSensibilidadManos(atuendoAceptado, guardarropas));
		unEvento.getAtuendosAceptados().stream()
				.forEach(atuendoAceptado -> this.calificarSegunSensibilidadCuello(atuendoAceptado, guardarropas));
		unEvento.getAtuendosAceptados().stream()
				.forEach(atuendoAceptado -> this.calificarSegunSensibilidadCabeza(atuendoAceptado, guardarropas));

	}

	public void calificarSegunSensibilidadManos(Atuendo unAtuendo, List<Guardarropas> guardarropas) {
		if (this.esCalurosoDeManos()) {
			unAtuendo.getPrendas().stream()
					.forEach(unaPrenda -> this.sacarPrendaExtra(unAtuendo, unaPrenda, TipoPrenda.GUANTES));
		} else {
			this.agregarPrendaFaltante(unAtuendo, guardarropas, TipoPrenda.GUANTES);
		}
	}

	public void calificarSegunSensibilidadCuello(Atuendo unAtuendo, List<Guardarropas> guardarropas) {
		if (this.esCalurosoDeCuello()) {
			unAtuendo.getPrendas().stream()
					.forEach(unaPrenda -> this.sacarPrendaExtra(unAtuendo, unaPrenda, TipoPrenda.BUFANDA));
		} else {
			this.agregarPrendaFaltante(unAtuendo, guardarropas, TipoPrenda.BUFANDA);
		}
	}

	public void calificarSegunSensibilidadCabeza(Atuendo unAtuendo, List<Guardarropas> guardarropas) {
		if (this.esCalurosoDeCabeza()) {
			unAtuendo.getPrendas().stream()
					.forEach(unaPrenda -> this.sacarPrendaExtra(unAtuendo, unaPrenda, TipoPrenda.GORRA));
		} else {
			this.agregarPrendaFaltante(unAtuendo, guardarropas, TipoPrenda.GORRA);
		}
	}

	public void agregarPrendaFaltante(Atuendo unAtuendo, List<Guardarropas> guardarropas, TipoPrenda unTipoPrenda) {
		if (unAtuendo.getPrendas().stream().noneMatch(prenda -> prenda.getTipo().equals(unTipoPrenda))) {
			guardarropas.stream()
					.forEach(guardarropa -> buscarPrendaEnElGuardarropaIndicado(unAtuendo, guardarropa, unTipoPrenda));
		}
	}

	public void buscarPrendaEnElGuardarropaIndicado(Atuendo unAtuendo, Guardarropas unGuardarropa,
			TipoPrenda unTipoPrenda) {

		Optional<Prenda> prendaABuscar = unAtuendo.getPrendas().stream()
				.filter(prenda -> prenda.getTipo().equals(unTipoPrenda)).findFirst();

		if (prendaABuscar.equals(null)) {
			unGuardarropa.getPrendas().stream()
					.forEach(prenda -> agregarPrendaSiEstaEnElGuardarropa(unAtuendo, prenda, unTipoPrenda));
		}
	}

	public void agregarPrendaSiEstaEnElGuardarropa(Atuendo unAtuendo, Prenda unaPrenda, TipoPrenda unTipoPrenda) {
		if (unaPrenda.getTipo().equals(unTipoPrenda)) {
			unAtuendo.addPrenda(unaPrenda);
		}
	}

	public void sacarPrendaExtra(Atuendo unAtuendo, Prenda prendaARemover, TipoPrenda unTipoPrenda) {
		if (prendaARemover.getTipo().equals(unTipoPrenda)) {
			unAtuendo.getPrendas().remove(prendaARemover);
		}
	}
}