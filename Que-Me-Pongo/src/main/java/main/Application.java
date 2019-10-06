package main;

import java.util.concurrent.TimeUnit;

import http.routes.Router;
import modelo.clases.Usuario;
import repository.UsuarioRepository;
import spark.Spark;
import utils.JobScheduler;

public class Application {
	public static void main(String[] args) {
		
		// Manda el mail cada 5 minutos
		Usuario pepe = new Usuario();
		new JobScheduler(0, 5, TimeUnit.MINUTES).run(pepe::notifyUser);
		
		Spark.port(7071);
		Router.register();
		UsuarioRepository repo = new UsuarioRepository();
		repo.init();
	}
}
