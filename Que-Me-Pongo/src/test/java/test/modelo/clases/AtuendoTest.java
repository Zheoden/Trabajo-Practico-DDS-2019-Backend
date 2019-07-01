package test.modelo.clases;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.clases.Atuendo;
import modelo.clases.Prenda;
import modelo.enums.*;
import modelo.enums.comportamiento.TipoAccesorio;
import modelo.enums.comportamiento.TipoSuperior;

import java.util.ArrayList;

@DisplayName("Tests para los Atuendos")
public class AtuendoTest {

	@Test
	@DisplayName("Test para verificar la creacion de un Atuendo")
	public void atuendoCreadoCorrectamente() {
		Atuendo atuendo = new Atuendo(new ArrayList<Prenda>());
		Assert.assertEquals(atuendo.getClass(), Atuendo.class);
	}
	
	
	@Test
	@DisplayName("Test para verificar que se agreguen prendas correctamente")
	public void agregarPrendasAlAtuendo() {
		Prenda prenda1 = new Prenda(TipoAccesorio.AROS, Color.AMARILLO);
		Prenda prenda2 = new Prenda(TipoSuperior.REMERACORTA,Material.ALGODON, Color.ROSA);		
		ArrayList<Prenda> prendas = new ArrayList<Prenda>();
		prendas.add(prenda1);
		prendas.add(prenda2);
		Atuendo atuendo = new Atuendo(prendas);
		Assert.assertEquals(atuendo.getPrendas(), prendas);
	}
}
