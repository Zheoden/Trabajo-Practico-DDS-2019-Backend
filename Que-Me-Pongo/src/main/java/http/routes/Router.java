package http.routes;

import static spark.Spark.*;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;

import modelo.clases.Evento;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.Usuario;
import modelo.clases.Atuendo;
import repository.EventoRepository;
import repository.GuardarropaRepository;
import repository.UsuarioRepository;
import repository.AtuendoRepository;
import utils.JsonParser;

public class Router {

	public static void register() {
		
		UsuarioRepository userService = new UsuarioRepository();
		GuardarropaRepository guardarropaService = new GuardarropaRepository();
		EventoRepository eventoService = new EventoRepository();
		AtuendoRepository atuendoService = new AtuendoRepository();

		get("/", (req, res) -> "Home");
		
		post("/login" ,"application/json",(req, res) -> {

			Usuario userFind = JsonParser.read(req.body(), new TypeReference<Usuario>(){}); 
			Optional<Usuario> usuarioLogin = userService.findUserByLogin(userFind.getUsername(), userFind.getPassword());
			
			if(usuarioLogin.isPresent()) {
				res.status(200);
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
				res.status(200);
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(usuarioBuscado.get());
			}
			res.status(400);
			return JsonParser.getObjectMapper().writeValueAsString("No se encontro el Usuario con id: " + id);
		});
		
		post("/users/:id/crearGuardarropa", "application/json", (req, res) -> {
			long id = Integer.parseInt(req.params(":id"));
			List<Guardarropas> guardarropas = guardarropaService.findByUserId(id);
			if (guardarropas.size() < 2) {
				Usuario user = userService.findById(id).get();
				Guardarropas guardarropaCreado = JsonParser.read(req.body(), new TypeReference<Guardarropas>(){}); 	
				user.agregarGuardarropa(guardarropaCreado);
				userService.update(user);
				Usuario userActualizado = userService.findById(id).get();
				res.status(200);
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(userActualizado.getGuardarropas());
			}	
			res.status(400);
			return JsonParser.getObjectMapper().writeValueAsString("Solo puede tener como maximo 2 Guardarropas");
		});
		
		post("/users/:id/guardarropas/:nombre/createPrenda", "application/json" ,(req, res) -> {
			
			long id = Integer.parseInt(req.params(":id"));
			Optional<Usuario> userABuscar = userService.findById(id);
			
			if(!userABuscar.isPresent()) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
			}
			
			Usuario userEncontrado = userABuscar.get();
			String nombre = req.params("nombre");
			Optional<Guardarropas> guardarropaBuscado = guardarropaService.findWardrobeByName(id, nombre);
			
			if(!guardarropaBuscado.isPresent()) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("No se encontro el Guardarropa con el nombre: " + nombre);
			}
			
			Guardarropas guardarropaEncontrado = guardarropaBuscado.get();
			Prenda prendaCreada = JsonParser.read(req.body(), new TypeReference<Prenda>(){}); 
			guardarropaEncontrado.getPrendas().add(prendaCreada);
			userService.update(userEncontrado);
			Guardarropas guardarropaActualizado = guardarropaService.findWardrobeById(id, nombre).get();
			res.status(200);
			return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(guardarropaActualizado);		
		});
		
		post("/users/:id/eventos/crearEvento", "application/json", (req, res) ->{
			
			long id = Integer.parseInt(req.params(":id"));
			Optional<Usuario> userABuscar = userService.findById(id);
			
			if(!userABuscar.isPresent()) {
				res.status(404);
				return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
			}
			
			Usuario userEncontrado = userABuscar.get();
			Evento eventoCreado = JsonParser.read(req.body(), new TypeReference<Evento>(){}); 	
			eventoCreado.setUsuario(userEncontrado);
			userEncontrado.getEventos().add(eventoCreado);
			userService.update(userEncontrado);
			Usuario userActualizado = userService.findById(id).get();
			res.status(200);
			return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(userActualizado.getEventos());	
		});
		
		get("/users/:id/eventos/:evento/sugerenciasAceptadas",(req,res) -> {
			
			long id = Integer.parseInt(req.params(":id"));
			String evento = req.params(":evento");
			Optional<Usuario> userABuscar = userService.findById(id);
			
			if(!userABuscar.isPresent()) {
				res.status(404);
				return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
			}
			
			Optional <Evento> nuevoEvento = eventoService.find(id,evento);
			if(!nuevoEvento.isPresent()) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("El Evento:" + evento + "no existe");
			}

			String nombreUser = userABuscar.get().getUsername();
			List<Atuendo> atuendosAceptados = atuendoService.findSugerenciasAceptadasParaEvento(evento, nombreUser);
			res.status(200);
			return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(atuendosAceptados);
		});
		
		get("/users/:id/eventos/:evento/sugerencias",(req,res) -> {
			
			long id = Integer.parseInt(req.params(":id"));
			String evento = req.params(":evento");
			Optional<Usuario> userABuscar = userService.findById(id);
			
			if(!userABuscar.isPresent()) {
				res.status(404);
				return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
			}
			
			Optional <Evento> eventoABuscar = eventoService.find(id,evento);
			
			if(!eventoABuscar.isPresent()) {
				res.status(404);
				return JsonParser.getObjectMapper().writeValueAsString("El Evento:" + evento + "no existe");
			}
			
			String nombreUser = userABuscar.get().getUsername();
			List<Atuendo> atuendosSugeridos = atuendoService.findSugerenciasParaEvento(evento, nombreUser);
			res.status(200);
			return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(atuendosSugeridos);
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
	}
	
}
