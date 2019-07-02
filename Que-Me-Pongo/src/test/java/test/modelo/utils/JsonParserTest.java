package test.modelo.utils;

import modelo.clases.Prenda;
import modelo.utils.JsonParser;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

public class JsonParserTest {

    private JsonParser parser = new JsonParser();

    @Test
    public void getClientsFromJSON() {
    	String testJson = "{\"tipo\":\"REMERACORTA\",\"colorPrimario\":\"ROJO\",\"material\":\"ALGODON\"}";
    	Prenda prenda = parser.read(testJson, new TypeReference<Prenda>() {});
    	System.out.println(prenda);
    }
}
