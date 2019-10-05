package modelo.clases;

import javax.persistence.Entity;

import modelo.interfaces.Suscripcion;


public class SuscripcionGratuita implements Suscripcion {

	@Override
	public boolean cantidadPrendasPermitidas(int unNumero) {
		return unNumero < 5;
	}

}
