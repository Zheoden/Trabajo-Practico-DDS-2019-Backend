package modelo.clases;

public abstract class Suscripcion {

	abstract public boolean cantidadSegunTipo(int unNumero);

	public boolean cantidadPrendasPermitidas(int unNumero) {
		return this.cantidadSegunTipo(unNumero);
	}

}
