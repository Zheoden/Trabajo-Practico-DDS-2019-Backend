package test.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import modelo.ropa.Categoria;
import repository.CategoriaRepository;

public class ModelosDatosCategoriaTest {

	static List<Categoria> listaCategorias = new ArrayList<>();
	static CategoriaRepository repoCategorias = new CategoriaRepository();

	static Categoria categoria1 = new Categoria("PARTE_SUPERIOR");
	static Categoria categoria2 = new Categoria("PARTE_INFERIOR");
	static Categoria categoria3 = new Categoria("CALZADO");
	static Categoria categoria4 = new Categoria("ACCESORIO");
	
	@BeforeClass
	public static void setUp() {
		
		listaCategorias.add(categoria1);
		listaCategorias.add(categoria2);
		listaCategorias.add(categoria3);
		listaCategorias.add(categoria4);
		
		listaCategorias.stream().forEach(categoria -> repoCategorias.persist(categoria));
	}
	
//	@Test
//	@DisplayName("Persistencia de todos las categorias a disponer")
//	public void persistenciaDeCategorias() {
//		List<Categoria> categorias = repoCategorias.all();
//		Assert.assertEquals(4, categorias.size());	
//	}
	
//	@AfterClass
//	public static void clearSetUp() {
//		listaCategorias.stream().forEach(categoria -> repoCategorias.delete(categoria));
//	}
}
