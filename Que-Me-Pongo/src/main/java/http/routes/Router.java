package http.routes;

import static spark.Spark.*;

import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.clases.Guardarropas;
import modelo.clases.Usuario;
import repository.GuardarropaRepository;
import repository.UsuarioRepository;

public class Router {

	public static void register() {
		
		UsuarioRepository userService = new UsuarioRepository();
		GuardarropaRepository guardarropaService = new GuardarropaRepository();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		get("/", (req, res) -> "Home");
		
		get("/users", (req, res) -> {
			res.type("application/json");
			return userService.all();
		}, gson::toJson);

		//Lo mas probable es que cambie a buscarse por medio del nombre de Usuario
		get("/user/:id",(req, res) -> {
			String id = req.params(":id");
			Optional<Usuario> usuarioBuscado = userService.findById(Integer.parseInt(id));
			if (usuarioBuscado.isPresent()) {
				res.type("application/json");
				return usuarioBuscado.get();
			}
			res.status(400);
			res.type("application/json");
			return String.format("No se encontro el Usuario con id: %s", id);

		}, gson::toJson);
		
		post("/user/:username/crearGuardarropa", "application/json" ,(req, res) -> {
			String username = req.params(":username");
			List<Guardarropas> guardarropas = guardarropaService.findByUser(username);
			if (guardarropas.size() < 2) {
				Usuario user = userService.find(username).get();
				user.agregarGuardarropa(new Guardarropas());
				userService.update(user);
				res.type("application/json");
				return String.format("Se agrego un nuevo guardarropa");
			}	
			res.status(301);
			res.type("application/json");
			return String.format("Solo puede tener como maximo 2 Guardarropas");
		}, gson::toJson);
		
		get("/user/create", (req, res) -> "Create User");
		get("/user/sugerir-atuendo", (req, res) -> "Sugerir Atuendo");
		get("/prenda/create", (req, res) -> "Create Prenda");
		get("/prenda", (req, res) -> "Get Prenda");
		get("/guardarropas", (req, res) -> "Get Guardarropas");
		get("/guardarropas/create", (req, res) -> "Create Guardarropas");
		get("/Atuendos", (req, res) -> "Atuendos");
	
	}
}
