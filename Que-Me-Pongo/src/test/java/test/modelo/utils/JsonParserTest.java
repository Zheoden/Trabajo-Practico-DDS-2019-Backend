package test.modelo.utils;

import modelo.clases.Prenda;
import modelo.utils.JsonParser;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

public class JsonParserTest {

    private JsonParser parser = new JsonParser();

    @Test
    public void getClientsFromJSON() {
    	String testJson = "{\"coord\":{\"lon\":-58.45,\"lat\":-34.6},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":282.36,\"pressure\":1011,\"humidity\":61,\"temp_min\":280.93,\"temp_max\":283.15},\"visibility\":10000,\"wind\":{\"speed\":5.1,\"deg\":310},\"clouds\":{\"all\":0},\"dt\":1562029296,\"sys\":{\"type\":1,\"id\":8224,\"message\":0.0076,\"country\":\"AR\",\"sunrise\":1561978883,\"sunset\":1562014412},\"timezone\":-10800,\"id\":3433955,\"name\":\"Ciudad Autónoma de Buenos Aires\",\"cod\":200}";
    	Prenda prenda = parser.read(testJson, new TypeReference<Prenda>() {});
    	System.out.println(prenda);
    }
}
