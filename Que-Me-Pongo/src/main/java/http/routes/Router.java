package http.routes;

import static spark.Spark.get;

public class Router {

    public static void register() {
    	get("/", (req,res) -> "Home");
    	
    	get("/user", (req,res) -> "Get User");
    	get("/user/create", (req,res) -> "Create User");
    	get("/user/sugerir-atuendo", (req,res) -> "Sugerir Atuendo");
    	get("/prenda/create", (req,res) -> "Create Prenda");
    	get("/prenda", (req,res) -> "Get Prenda");
    	get("/guardarropas", (req,res) -> "Get Guardarropas");
    	get("/guardarropas/create", (req,res) -> "Create Guardarropas");
    }
}
