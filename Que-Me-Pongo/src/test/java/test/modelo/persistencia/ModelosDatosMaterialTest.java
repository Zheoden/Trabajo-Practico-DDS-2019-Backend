package test.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;

import junit.framework.Assert;
import modelo.ropa.Material;
import repository.MaterialRepository;

public class ModelosDatosMaterialTest {

	static List<Material> listaMateriales = new ArrayList<>();
	static MaterialRepository repoMateriales = new MaterialRepository();
	
	static Material material1 = new Material("ALGODON");
	static Material material2 = new Material("CUERO");
	static Material material3 = new Material("GABARDINA");
	static Material material4 = new Material("JEAN");
	static Material material5 = new Material("LINO");
	static Material material6 = new Material("LYCRA");
	static Material material7 = new Material("OXFORD");
	static Material material8 = new Material("POLAR");
	static Material material9 = new Material("SEDA");
	static Material material10 = new Material("TERCIOPELO");
	
	@BeforeClass
	public static void setUp() {
		
		listaMateriales.add(material1);
		listaMateriales.add(material2);
		listaMateriales.add(material3);
		listaMateriales.add(material4);
		listaMateriales.add(material5);
		listaMateriales.add(material6);
		listaMateriales.add(material7);
		listaMateriales.add(material8);
		listaMateriales.add(material9);
		listaMateriales.add(material10);

		if (repoMateriales.all().isEmpty()) {
			listaMateriales.stream().forEach(material -> repoMateriales.persist(material));
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Persistencia de todos los materiales a disponer")
	public void persistenciaDeMateriales() {
		List<Material> materiales = repoMateriales.all();
		Assert.assertEquals(10, materiales.size());	
	}
	
	@AfterClass
	public static void clearSetUp() {
		listaMateriales.stream().forEach(material -> repoMateriales.delete(material));
	}
}