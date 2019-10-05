package modelo.clases;

import javax.persistence.Entity;

import modelo.interfaces.Suscripcion;

public class SuscripcionPremium implements Suscripcion {

	@Override
	public boolean cantidadPrendasPermitidas(int unNumero) {
		return true;
	}

}
