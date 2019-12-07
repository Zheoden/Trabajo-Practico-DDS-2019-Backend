package http.routes;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import com.fasterxml.jackson.core.type.TypeReference;

import modelo.clases.Atuendo;
import modelo.clases.Evento;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.Usuario;
import modelo.ropa.TipoPrenda;
import modelo.ropa.Categoria;
import modelo.ropa.Color;
import modelo.ropa.Material;
import repository.AtuendoRepository;
import repository.CategoriaRepository;
import repository.ColorRepository;
import repository.EventoRepository;
import repository.GuardarropaRepository;
import repository.MaterialRepository;
import repository.PrendaRepository;
import repository.TipoPrendaRepository;
import repository.UsuarioRepository;
import utils.JsonParser;

public class Router {

	public static void register() {
		
		UsuarioRepository userService = new UsuarioRepository();
		GuardarropaRepository guardarropaService = new GuardarropaRepository();
		EventoRepository eventoService = new EventoRepository();
		AtuendoRepository atuendoService = new AtuendoRepository();
		PrendaRepository prendaService = new PrendaRepository();
		ColorRepository colorService = new ColorRepository();
        CategoriaRepository categoriaService = new CategoriaRepository();
        MaterialRepository materialService = new MaterialRepository();
        TipoPrendaRepository tipoPrendaService = new TipoPrendaRepository();

		get("/", (req, res) -> "Home");
		
// Controladores Sobre Usuarios ----------------------------------------------------------------------------------------------	
		
		get("/users", "application/json", (req, res) -> {
			try {
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(userService.all());
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});
		
		post("/login" ,"application/json",(req, res) -> {
			try {
				Usuario userFind = JsonParser.read(req.body(), new TypeReference<Usuario>(){}); 
				Optional<Usuario> usuarioLogin = userService.findUserByLogin(userFind.getUsername(), userFind.getPassword());
			
				if(usuarioLogin.isPresent()) {
					res.status(200);
					return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(usuarioLogin.get());
				}
				
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Usuario/Contraseña incorrectos");
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});

		get("/users/:id", "application/json" ,(req, res) -> {
			try {
				String id = req.params(":id");
				Optional<Usuario> usuarioBuscado = userService.findById(Integer.parseInt(id));
				if (usuarioBuscado.isPresent()) {
					res.status(200);
					return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(usuarioBuscado.get());
				}
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("No se encontro el Usuario con id: " + id);
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});
		
		delete("/users/:id/eliminarUsuario", "application/json", (req, res) -> {
			try {
				long id = Integer.parseInt(req.params(":id"));
				Optional<Usuario> userABuscar = userService.findById(id);
				
				if(!userABuscar.isPresent()) {
					res.status(400);
					return JsonParser.getObjectMapper().writeValueAsString("No existe el usuario con id: " + id);
				}
				
					userService.delete(userABuscar.get());
					res.status(200);
					return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString("Se elimino el usuario con id: " + id);
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});	
		
// Controladores Sobre Guardarropas ----------------------------------------------------------------------------------------------				
		
		post("/users/:id/crearGuardarropa", "application/json", (req, res) -> {
			try {
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
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});
		
		delete("/users/:id/guardarropas/:idGuardarropa/eliminarGuardarropa", "application/json", (req, res) -> {
			try {
				long id = Integer.parseInt(req.params(":id"));
				Optional<Usuario> userABuscar = userService.findById(id);
				
				if(!userABuscar.isPresent()) {
					res.status(400);
					return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
				}
				
				long idGuardarropa = Integer.parseInt(req.params("idGuardarropa"));
				Optional<Guardarropas> guardarropaBuscado = guardarropaService.findWardrobeById(id, idGuardarropa);
				
				if(!guardarropaBuscado.isPresent()) {
					res.status(400);
					return JsonParser.getObjectMapper().writeValueAsString("No se encontro el Guardarropa con el id: " + idGuardarropa);
				}
				
				Guardarropas guardarropaEncontrado = guardarropaBuscado.get();
				
				guardarropaService.delete(guardarropaEncontrado);
	
				Usuario userActualizado = userService.findById(id).get();
				res.status(200);
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(userActualizado.getGuardarropas());
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});
		
// Controladores Sobre Prendas ----------------------------------------------------------------------------------------------		
		
		post("/users/:id/guardarropas/:idGuarda/createPrenda", "application/json" ,(req, res) -> {
			try {
				long id = Integer.parseInt(req.params(":id"));
				Optional<Usuario> userABuscar = userService.findById(id);
				
				if(!userABuscar.isPresent()) {
					res.status(400);
					return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
				}
				
				Usuario userEncontrado = userABuscar.get();
				long idGuardarropa = Integer.parseInt(req.params("idGuarda"));
				Optional<Guardarropas> guardarropaBuscado = guardarropaService.findWardrobeById(id, idGuardarropa);
				
				if(!guardarropaBuscado.isPresent()) {
					res.status(400);
					return JsonParser.getObjectMapper().writeValueAsString("No se encontro el Guardarropa con el nombre: " + guardarropaBuscado.get().getNombre());
				}
				
				Guardarropas guardarropaEncontrado = guardarropaBuscado.get();
				Prenda prendaCreada = JsonParser.read(req.body(), new TypeReference<Prenda>(){}); 
				prendaCreada.setGuardarropas(guardarropaEncontrado);
				guardarropaEncontrado.getPrendas().add(prendaCreada);
				userService.update(userEncontrado);
				Guardarropas guardarropaActualizado = guardarropaService.findWardrobeById(id, idGuardarropa).get();
				res.status(200);
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(guardarropaActualizado);
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});
		
		delete("/users/:id/guardarropas/:idGuardarropa/prendas/:idPrenda/eliminarPrenda", "application/json", (req, res) -> {
			try {
				long id = Integer.parseInt(req.params(":id"));
				Optional<Usuario> userABuscar = userService.findById(id);
				
				if(!userABuscar.isPresent()) {
					res.status(400);
					return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
				}
				
				long idGuardarropa = Integer.parseInt(req.params("idGuardarropa"));
				Optional<Guardarropas> guardarropaBuscado = guardarropaService.findWardrobeById(id, idGuardarropa);
				
				if(!guardarropaBuscado.isPresent()) {
					res.status(400);
					return JsonParser.getObjectMapper().writeValueAsString("No se encontro el Guardarropa con el id: " + guardarropaBuscado.get().getId());
				}
				
				long idPrenda = Integer.parseInt(req.params("idPrenda"));
				Optional<Prenda> prendaABuscar = prendaService.findPrendaInGuardarropaById(idGuardarropa, idPrenda);
				
				if(!prendaABuscar.isPresent()) {
					res.status(400);
					return JsonParser.getObjectMapper().writeValueAsString("No se encontro la Prenda con el id: " + idPrenda);
				}
				
				prendaService.delete(prendaABuscar.get());
				Guardarropas guardarropaActualizado = guardarropaService.findWardrobeById(id, idGuardarropa).get();
				res.status(200);
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(guardarropaActualizado.getPrendas());
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});
		
// Controladores Sobre Eventos ----------------------------------------------------------------------------------------------		
		
		post("/users/:id/eventos/crearEvento", "application/json", (req, res) ->{
			try {
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
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});
		
		delete("/users/:id/eventos/:idEvento/eliminarEvento", "application/json", (req, res) -> {
			try {
				long id = Integer.parseInt(req.params(":id"));
				Optional<Usuario> userABuscar = userService.findById(id);
				
				if(!userABuscar.isPresent()) {
					res.status(400);
					return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
				}
	
				long idEvento = Integer.parseInt(req.params("idEvento"));
				Optional<Evento> eventoBuscado = eventoService.find(id, idEvento);
	
				if(!eventoBuscado.isPresent()) {
					res.status(400);
					return JsonParser.getObjectMapper().writeValueAsString("No se encontro el Evento con el id: " + idEvento);
				}
	
				eventoService.delete(eventoBuscado.get());
				Usuario usuarioActualizado = userService.findById(id).get();
				res.status(200);
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(usuarioActualizado.getEventos());
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});
		
// Controladores Sobre Atuendos ----------------------------------------------------------------------------------------------
		
		get("/users/:id/eventos/:idEvento/sugerenciasAceptadas",(req,res) -> {
			try {
				long id = Integer.parseInt(req.params(":id"));
				long idEvento = Integer.parseInt(req.params(":idEvento"));
				Optional<Usuario> userABuscar = userService.findById(id);
				
				if(!userABuscar.isPresent()) {
					res.status(404);
					return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
				}
				
				Optional <Evento> nuevoEvento = eventoService.find(id,idEvento);
				if(!nuevoEvento.isPresent()) {
					res.status(400);
					return JsonParser.getObjectMapper().writeValueAsString("El Evento: " + idEvento + " no existe");
				}
	
				List<Atuendo> atuendosAceptados = atuendoService.findSugerenciasAceptadasParaEventoPuntaje(idEvento, id);
				res.status(200);
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(atuendosAceptados);
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});
		
		get("/users/:id/eventos/:idEvento/sugerencias",(req,res) -> {
			try {
				long id = Integer.parseInt(req.params(":id"));
				long idEvento = Integer.parseInt(req.params(":idEvento"));
				Optional<Usuario> userABuscar = userService.findById(id);
				
				if(!userABuscar.isPresent()) {
					res.status(404);
					return JsonParser.getObjectMapper().writeValueAsString("El Usuario No Existe");
				}
				
				Optional <Evento> eventoABuscar = eventoService.find(id,idEvento);
				
				if(!eventoABuscar.isPresent()) {
					res.status(404);
					return JsonParser.getObjectMapper().writeValueAsString("El Evento con el id: " + idEvento + " no existe");
				}
				
				Evento eventoEncontrado = eventoABuscar.get();
				Usuario userEncontrado = userABuscar.get();
				int diferenciaDeDias = eventoEncontrado.getFecha().get(Calendar.DAY_OF_YEAR) - Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
				
				if (diferenciaDeDias >= 7) {
					res.status(404);
					return JsonParser.getObjectMapper().writeValueAsString("Falta mas de una semana para el evento seleccionado. Vuelva mas tarde para obtener sus sugerencias.");
				}
					
				if(eventoEncontrado.getAtuendosMovimientos().isEmpty()) {
					List<Atuendo> atuendosSugeridos = userEncontrado.todosPosiblesAtuendosPorGuardarropaParaEvento(eventoEncontrado);
					eventoEncontrado.setAtuendosMovimientos(atuendosSugeridos);
					atuendosSugeridos.stream().forEach(atuendo -> atuendoService.persist(atuendo));
					eventoService.update(eventoEncontrado);
					userService.update(userEncontrado);
					Evento eventoActualizado = eventoService.find(id,idEvento).get();
					res.status(200);
					return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(eventoActualizado.getAtuendosMovimientos());
				}
				res.status(200);
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(eventoEncontrado.getAtuendosMovimientos());
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});
		
		post("/users/:userid/atuendo/:id/calificarAtuendo/:calificacion", "application/json", (req, res) -> {
			try {
	            Integer numero = Integer.parseInt(req.params(":calificacion"));	
	            long id = Integer.parseInt(req.params(":id"));	
	            Optional<Atuendo> atuendoBuscado = atuendoService.find(id);	
	
	            if (!atuendoBuscado.isPresent()) {	
	                res.status(404);	
	                return JsonParser.getObjectMapper().writeValueAsString("El atuendo no existe");	
	            }	
	
	            Atuendo atuendoEncontrado = atuendoBuscado.get();	
	            atuendoEncontrado.setCalificacion(numero);	
	
	            atuendoService.update(atuendoEncontrado);	
	            res.status(200);	
	            return JsonParser.getObjectMapper().writeValueAsString(atuendoEncontrado);
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
        });
		
		post("/atuendo/:id/aceptarSugerencia", "application/json", (req, res) -> {
			try {
	            long id = Integer.parseInt(req.params(":id"));
	            Optional<Atuendo> atuendoBuscado = atuendoService.find(id);
				
				 if (!atuendoBuscado.isPresent()) {
	                res.status(404);
	                return JsonParser.getObjectMapper().writeValueAsString("El atuendo no existe");
	            }
				
				Atuendo atuendoEncontrado = atuendoBuscado.get();
	            atuendoEncontrado.aceptar();
				
				 atuendoService.update(atuendoEncontrado);
	            res.status(200);
	            return JsonParser.getObjectMapper().writeValueAsString(atuendoEncontrado);
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
        });
		
// Controladores Sobre Enumeradores ----------------------------------------------------------------------------------------------
		
		get("/categorias", "application/json", (req, res) -> {
			try {
				List<Categoria> categorias = categoriaService.all();
				res.status(200);
				return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(categorias);
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
		});

        get("/colores", "application/json", (req, res) -> {
        	try {
	            List<Color> colores = colorService.all();
	            res.status(200);
	            return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(colores);
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
        });


        get("/materiales", "application/json" ,(req, res) -> {
        	try {
	            List<Material> materiales = materialService.all();
	            res.status(200);
	            return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(materiales);
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
        });
        
        get("/tipoPrendas", "application/json" ,(req, res) -> {
        	try {
	            List<TipoPrenda> prendas = tipoPrendaService.all();
	            res.status(200);
	            return JsonParser.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(prendas);
			} catch(NoResultException  e) {
				res.status(400);
				return JsonParser.getObjectMapper().writeValueAsString("Ups! Algo salio mal en el backend! Intenta en un ratito mientras solucionamos los problemas de persistencia!");
			}
        });
	}	
}