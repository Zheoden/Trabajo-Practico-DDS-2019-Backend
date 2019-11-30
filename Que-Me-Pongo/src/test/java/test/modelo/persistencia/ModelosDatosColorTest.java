package test.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;

import junit.framework.Assert;
import modelo.ropa.Color;
import repository.ColorRepository;

public class ModelosDatosColorTest {

	static List<Color> listaColores = new ArrayList<>();
	static ColorRepository repoColores = new ColorRepository();

	static Color color1 = new Color("BLANCO");
	static Color color2 = new Color("NEGRO");
	static Color color3 = new Color("AZUL");
	static Color color4 = new Color("ROJO");
	static Color color5 = new Color("VERDE");
	static Color color6 = new Color("AMARILLO");
	static Color color7 = new Color("VIOLETA");
	static Color color8 = new Color("ROSA");
	static Color color9 = new Color("SALMON");
	static Color color10 = new Color("MARRON");
	static Color color11 = new Color("GRIS");
	static Color color12 = new Color("NARANJA");
	static Color color13 = new Color("CELESTE");
	static Color color14 = new Color("BORDO");
	static Color color15 = new Color("BEIGE");
	static Color color16 = new Color("CAQUI");
	static Color color17 = new Color("CARMESI");
	static Color color18 = new Color("TURQUESA");

	@BeforeClass
	public static void setUp() {
		
		listaColores.add(color1);
		listaColores.add(color2);
		listaColores.add(color3);
		listaColores.add(color4);
		listaColores.add(color5);
		listaColores.add(color6);
		listaColores.add(color7);
		listaColores.add(color8);
		listaColores.add(color9);
		listaColores.add(color10);
		listaColores.add(color11);
		listaColores.add(color12);
		listaColores.add(color13);
		listaColores.add(color14);
		listaColores.add(color15);
		listaColores.add(color16);
		listaColores.add(color17);
		listaColores.add(color18);

		if (repoColores.all().isEmpty()) {
			listaColores.stream().forEach(color -> repoColores.persist(color));
		}

	}

	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Persistencia de todos los colores a disponer")
	public void persistenciaDeColores() {
		List<Color> colores = repoColores.all();
		Assert.assertEquals(18, colores.size());	
	}
	
	@AfterClass
	public static void clearSetUp() {
		listaColores.stream().forEach(color -> repoColores.delete(color));
	}
}