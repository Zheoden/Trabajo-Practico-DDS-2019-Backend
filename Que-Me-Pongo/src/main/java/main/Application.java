package main;

import http.routes.Router;
import spark.Spark;
import utils.CorsFilter;

public class Application {
	public static void main(String[] args) {

		
		// Manda el mail cada 5 minutos
//		Usuario pepe = new Usuario();
//		new JobScheduler(0, 5, TimeUnit.MINUTES).run(pepe::notifyUser);
//
		Spark.port(80);
		Router.register();
		CorsFilter.enableCORS("*", "*", "*");

	}
}
 
