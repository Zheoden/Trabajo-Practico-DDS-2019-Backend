package modelo.clases;

public class SuscripcionGratuita extends Suscripcion {

	public boolean cantidadSegunTipo(int unNumero) {
		return unNumero <= 5;
	}

}
