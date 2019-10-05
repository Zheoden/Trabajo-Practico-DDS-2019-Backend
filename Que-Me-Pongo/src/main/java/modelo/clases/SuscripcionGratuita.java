package modelo.clases;

import modelo.interfaces.Suscripcion;


public class SuscripcionGratuita implements Suscripcion {

	@Override
	public boolean cantidadPrendasPermitidas(int unNumero) {
		return unNumero < 5;
	}

}
