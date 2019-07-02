package modelo.clases;

public abstract class Suscripcion {

	abstract public int cantidadSegunTipo();

	public int cantidadPrendasPermitidas() {
		return this.cantidadSegunTipo();
	}

}
