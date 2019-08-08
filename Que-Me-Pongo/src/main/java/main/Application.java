package main;

import http.routes.Router;
import spark.Spark;

public class Application {
	public static void main(String[] args) {
		Spark.port(7071);
		Router.register();
	}
}
