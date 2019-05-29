package modelo;

import static spark.Spark.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		port(7071);
		new Usuario(new ArrayList<Guardarropas>());
	}
}
