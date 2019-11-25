package test.modelo.persistencia;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;

import junit.framework.Assert;
import modelo.ropa.TipoPrenda;
import repository.TipoPrendaRepository;

public class ModelosDatosTipoPrendaTest {

	static List<TipoPrenda> listaPrendas = new ArrayList<>();
	static TipoPrendaRepository repoPrendas = new TipoPrendaRepository();
	
	static TipoPrenda prenda1 = new TipoPrenda("BUZO");
	static TipoPrenda prenda2 = new TipoPrenda("CAMISA");
	static TipoPrenda prenda3 = new TipoPrenda("CAMPERA");
	static TipoPrenda prenda4 = new TipoPrenda("REMERACORTA");
	static TipoPrenda prenda5 = new TipoPrenda("REMERALARGA");
	static TipoPrenda prenda6 = new TipoPrenda("SWEATER");
	static TipoPrenda prenda7 = new TipoPrenda("ZAPATILLAS");
	static TipoPrenda prenda8 = new TipoPrenda("ZAPATOS");
	static TipoPrenda prenda9 = new TipoPrenda("ZAPATOSDETACON");
	static TipoPrenda prenda10 = new TipoPrenda("OJOTAS");
	static TipoPrenda prenda11 = new TipoPrenda("BERMUDAS");
	static TipoPrenda prenda12 = new TipoPrenda("MEDIAS");
	static TipoPrenda prenda13 = new TipoPrenda("CALZAS");
	static TipoPrenda prenda14 = new TipoPrenda("PANTALON");
	static TipoPrenda prenda15 = new TipoPrenda("POLLERA");
	static TipoPrenda prenda16 = new TipoPrenda("SHORTS");
	static TipoPrenda prenda17 = new TipoPrenda("ANTEOJOS");
	static TipoPrenda prenda18 = new TipoPrenda("BUFANDA");
	static TipoPrenda prenda19 = new TipoPrenda("GORRA");
	static TipoPrenda prenda20 = new TipoPrenda("GUANTES");
	static TipoPrenda prenda21 = new TipoPrenda("COLLAR");
	static TipoPrenda prenda22 = new TipoPrenda("LENTES");
	static TipoPrenda prenda23 = new TipoPrenda("AROS");
	
	@BeforeClass
	public static void setUp() {
		
		listaPrendas.add(prenda1);
		listaPrendas.add(prenda2);
		listaPrendas.add(prenda3);
		listaPrendas.add(prenda4);
		listaPrendas.add(prenda5);
		listaPrendas.add(prenda6);
		listaPrendas.add(prenda7);
		listaPrendas.add(prenda8);
		listaPrendas.add(prenda9);
		listaPrendas.add(prenda10);
		listaPrendas.add(prenda11);
		listaPrendas.add(prenda12);
		listaPrendas.add(prenda13);
		listaPrendas.add(prenda14);
		listaPrendas.add(prenda15);
		listaPrendas.add(prenda16);
		listaPrendas.add(prenda17);
		listaPrendas.add(prenda18);
		listaPrendas.add(prenda19);
		listaPrendas.add(prenda20);
		listaPrendas.add(prenda21);
		listaPrendas.add(prenda22);
		listaPrendas.add(prenda23);
		
		if (repoPrendas.all().isEmpty()) {
			listaPrendas.stream().forEach(tipoPrenda -> repoPrendas.persist(tipoPrenda));
		}
	}
	
	@Test
	@DisplayName("Persistencia de todos los tipos de prendas a disponer")
	public void persistenciaDeTipoPrendas() {
		List<TipoPrenda> prendas = repoPrendas.all();
		Assert.assertEquals(23, prendas.size());	
	}

}
