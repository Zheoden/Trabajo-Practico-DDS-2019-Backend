package http.routes;

import static spark.Spark.*;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;

import modelo.clases.Evento;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.Usuario;
import repository.EventoRepository;
import repository.GuardarropaRepository;
import repository.UsuarioRepository;
import utils.JsonParser;

public class Router {

	@SuppressWarnings("unlikely-arg-type")
	public static void register() {
		
		UsuarioRepository userService = new UsuarioRepository();
		GuardarropaRepository guardarropaService = new GuardarropaRepository();
		EventoRepository eventoService = new EventoRepository();

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
		get("/users/:id", "application/json" ,(req, res) -> {
			String id = req.params(":id");
			Optional<Usuario> usuarioBuscado = userService.findById(Integer.parseInt(id));
			if (usuarioBuscado.isPresent()) {
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(usuarioBuscado.get());
			}
			res.status(400);
			return JsonParser.getObjectMapper().writeValueAsString("No se encontro el Usuario con id: " + id);
		});
		
		post("/users/:username/crearGuardarropa", "application/json", (req, res) -> {
			String username = req.params(":username");
			List<Guardarropas> guardarropas = guardarropaService.findByUser(username);
			if (guardarropas.size() < 2) {
				Usuario user = userService.find(username).get();
				Guardarropas guardarropaCreado = JsonParser.read(req.body(), new TypeReference<Guardarropas>(){}); 	
				user.agregarGuardarropa(guardarropaCreado);
				userService.update(user);
				return JsonParser.getObjectMapper().writeValueAsString("Se agrego un nuevo guardarropa");
			}	
			res.status(403);
			return JsonParser.getObjectMapper().writeValueAsString("Solo puede tener como maximo 2 Guardarropas");
		});
		
		post("/users/:username/guardarropas/:nombre/createPrenda", "application/json" ,(req, res) -> {
			
			String username = req.params(":username");
			Optional<Usuario> userABuscar = userService.find(username);
			
			if(!userABuscar.isPresent()) {
				res.status(404);
				return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
			}
			
			Usuario userEncontrado = userABuscar.get();
			String nombre = req.params("nombre");
			Optional<Guardarropas> guardarropaBuscado = guardarropaService.findWardrobeByName(username, nombre);
			
			if(!guardarropaBuscado.isPresent()) {
				res.status(404);
				return JsonParser.getObjectMapper().writeValueAsString("No se encontro el Guardarropa con el nombre: " + nombre);
			}
			
			Guardarropas guardarropaEncontrado = guardarropaBuscado.get();
			Prenda prendaCreada = JsonParser.read(req.body(), new TypeReference<Prenda>(){}); 
			guardarropaEncontrado.getPrendas().add(prendaCreada);
			userService.update(userEncontrado);
			res.status(200);
			return JsonParser.getObjectMapper().writeValueAsString("Se agrego la nueva Prenda");		
		});
		
		post("/users/:username/eventos/crearEvento", "application/json", (req, res) ->{
			
			String username = req.params(":username");
			Optional<Usuario> userABuscar = userService.find(username);
			
			if(!userABuscar.isPresent()) {
				res.status(404);
				return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
			}
			
			Usuario userEncontrado = userABuscar.get();
			Evento eventoCreado = JsonParser.read(req.body(), new TypeReference<Evento>(){}); 	
			eventoCreado.setUsuario(userEncontrado);
			userEncontrado.getEventos().add(eventoCreado);
			userService.update(userEncontrado);
			res.status(200);
			return JsonParser.getObjectMapper().writeValueAsString("Se creo el nuevo Evento");	
		});
		
		get("/users/:username/eventos/:evento",(req,res) -> {
			
			String username = req.params(":username");
			String evento = req.params(":evento");
			Optional<Usuario> userABuscar = userService.find(username);
			
			if(!userABuscar.isPresent()) {
				res.status(404);
				return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
			}
			
			Optional <Evento> nuevoEvento = eventoService.find(username,evento);
			if(!nuevoEvento.isPresent()) {
				res.status(404);
				return JsonParser.getObjectMapper().writeValueAsString("El Evento:" + evento + "no existe");
			}
			Evento eventoEncontrado = nuevoEvento.get(); 
			res.status(203);
			return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(eventoEncontrado.getAtuendosAceptados());
		});
		
		get("/users/:username/eventosAll/:evento",(req,res) -> {
			
			String username = req.params(":username");
			String evento = req.params(":evento");
			Optional<Usuario> userABuscar = userService.find(username);
			
			if(!userABuscar.isPresent()) {
				res.status(404);
				return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
			}
			
			Optional <Evento> eventoABuscar = eventoService.find(username,evento);
			
			if(!eventoABuscar.isPresent()) {
				res.status(404);
				return JsonParser.getObjectMapper().writeValueAsString("El Evento:" + evento + "no existe");
			}
			Evento eventoEncontrado = eventoABuscar.get(); 
			res.status(203);					
			
			Usuario usuarioEncontrado = userABuscar.get(); 
			res.status(203);					
			
			return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(usuarioEncontrado.todosPosiblesAtuendosPorGuardarropaParaEvento(eventoEncontrado));
			
		});
		
		
		/*
		post("/users/:username/eventos/:evento/calificarAtuendo", (req,res) -> {
			
			Optional <Evento> nuevoEvento = eventoService.find(username,evento);
			if(!nuevoEvento.isPresent()) {
				res.status(404);
				return JsonParser.getObjectMapper().writeValueAsString("El Evento:" + evento + "no existe");
			}
			Evento eventoEncontrado = nuevoEvento.get(); 
			res.status(203);
			return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(eventoEncontrado.getAtuendosAceptados());

		});
		*/
		
		get("/user/create", (req, res) -> "Create User");
		get("/user/sugerir-atuendo", (req, res) -> "Sugerir Atuendo");
		get("/prenda", (req, res) -> "Get Prenda");
		get("/guardarropas", (req, res) -> "Get Guardarropas");
		get("/guardarropas/create", (req, res) -> "Create Guardarropas");
		get("/Atuendos", (req, res) -> "Atuendos");
	
	}
	
}
