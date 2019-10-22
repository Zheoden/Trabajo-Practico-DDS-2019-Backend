package http.routes;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;

import modelo.clases.Guardarropas;
import modelo.clases.Usuario;
import repository.GuardarropaRepository;
import repository.UsuarioRepository;
import utils.JsonParser;

public class Router {

	public static void register() {
		
		UsuarioRepository userService = new UsuarioRepository();
		GuardarropaRepository guardarropaService = new GuardarropaRepository();
		
		get("/", (req, res) -> "Home");
		
		post("/login" ,"application/json",(req, res) -> {

			Usuario userFind = JsonParser.read(req.body(), new TypeReference<Usuario>(){}); 
			Optional<Usuario> usuarioLogin = userService.findUserByLogin(userFind.getUsername(), userFind.getPassword());
			
			if(usuarioLogin.isPresent()) {
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(usuarioLogin.get());
			}
			
			res.status(400);
			return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(null);		
		});
		
		get("/users", "application/json", (req, res) -> {
			return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(userService.all());
		});

		//Lo mas probable es que cambie a buscarse por medio del nombre de Usuario
		get("/user/:id", "application/json" ,(req, res) -> {
			String id = req.params(":id");
			Optional<Usuario> usuarioBuscado = userService.findById(Integer.parseInt(id));
			if (usuarioBuscado.isPresent()) {
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(usuarioBuscado.get());
			}
			res.status(400);
			return JsonParser.getObjectMapper().writeValueAsString("No se encontro el Usuario con id: " + id);
		});
		
		post("/user/:username/crearGuardarropa", "application/json", (req, res) -> {
			String username = req.params(":username");
			List<Guardarropas> guardarropas = guardarropaService.findByUser(username);
			if (guardarropas.size() < 2) {
				Usuario user = userService.find(username).get();
				user.agregarGuardarropa(new Guardarropas());
				userService.update(user);
				return JsonParser.getObjectMapper().writeValueAsString("Se agrego un nuevo guardarropa");
			}	
			res.status(403);
			return JsonParser.getObjectMapper().writeValueAsString("Solo puede tener como maximo 2 Guardarropas");
		});
		
		get("/user/create", (req, res) -> "Create User");
		get("/user/sugerir-atuendo", (req, res) -> "Sugerir Atuendo");
		get("/prenda/create", (req, res) -> "Create Prenda");
		get("/prenda", (req, res) -> "Get Prenda");
		get("/guardarropas", (req, res) -> "Get Guardarropas");
		get("/guardarropas/create", (req, res) -> "Create Guardarropas");
		get("/Atuendos", (req, res) -> "Atuendos");
	
	}
}
