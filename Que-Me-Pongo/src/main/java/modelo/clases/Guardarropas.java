package modelo.clases;

import static com.google.common.collect.Sets.cartesianProduct;
import static com.google.common.collect.Sets.powerSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nullable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import modelo.dtos.Categoria;

@Entity
@Table (name="Guardarropa")
public class Guardarropas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	private String nombre;
	@OneToMany(mappedBy = "guardarropas", cascade = CascadeType.ALL)
	private List<Prenda> prendas = new ArrayList<>();
	@Transient
	@JsonIgnore
	public AdministrarProveedores administrarProveedores = new AdministrarProveedores();
	@ManyToMany (mappedBy="guardarropas")
	@JsonIgnore
	private List<Usuario> usuarios = new ArrayList<>();

	
	public Guardarropas(ArrayList<Prenda> prendas, AdministrarProveedores administrarProv, String nombre) {
		this.setNombre(nombre);
		this.setPrendas(prendas);
		this.administrarProveedores = administrarProv;
	}
    public Guardarropas() {
    	
    }
	
	public Guardarropas(ArrayList<Prenda> prendas, String nombre) {
		this.setNombre(nombre);
		this.setPrendas(prendas);
	}

	public int tamanioGuardarropas() {
		return prendas.size();
	}

	public Set<Prenda> obtenerPrendasSuperiores() {
		return prendas.stream().filter(prenda -> (prenda.Categoria() == Categoria.PARTE_SUPERIOR && !prenda.enUso))
				.collect(Collectors.toSet());
	}

	public Set<Prenda> obtenerPrendasInferiores() {
		return prendas.stream().filter(prenda -> (prenda.Categoria() == Categoria.PARTE_INFERIOR && !prenda.enUso))
				.collect(Collectors.toSet());
	}

	public Set<Prenda> obtenerCalzados() {
		return prendas.stream().filter(prenda -> (prenda.Categoria() == Categoria.CALZADO && !prenda.enUso))
				.collect(Collectors.toSet());
	}

	public Set<Prenda> obtenerAccesorios() {
		return prendas.stream().filter(prenda -> (prenda.Categoria() == Categoria.ACCESORIO && !prenda.enUso))
				.collect(Collectors.toSet());
	}

	public Atuendo obtenerAtuendoRandom(List<Atuendo> combinaciones) {
		Collections.shuffle(combinaciones);
		return combinaciones.get(0);
	}

	public List<Atuendo> generarSugerencias(Double temperatura, int sensibilidad, @Nullable Evento evento) {
		Set<Set<Prenda>> calzados = obtenerCombinacionesNoVacias(obtenerCalzados(), temperatura, sensibilidad);
		Set<Set<Prenda>> prendasInferiores = obtenerCombinacionesNoVacias(obtenerPrendasInferiores(), temperatura,
				sensibilidad);
		Set<Set<Prenda>> prendasSuperiores = obtenerCombinacionesNoVacias(obtenerPrendasSuperiores(), temperatura,
				sensibilidad);
		Set<Set<Prenda>> accesorios = obtenerCombinacionesNoVacias(obtenerAccesorios(), temperatura, sensibilidad);

		if(accesorios.isEmpty()) {
			if (evento != null) {
				return cartesianProduct(prendasSuperiores, prendasInferiores, calzados).stream()
						.map(lista -> new Atuendo(lista.get(0), lista.get(1), lista.get(2), evento))
						.collect(Collectors.toList());
			} else {
				return cartesianProduct(prendasSuperiores, prendasInferiores, calzados).stream()
						.map(lista -> new Atuendo(lista.get(0), lista.get(1), lista.get(2)))
						.collect(Collectors.toList());
			}
		} else {
			if (evento != null) {
				return cartesianProduct(prendasSuperiores, prendasInferiores, calzados, accesorios).stream()
						.map(lista -> new Atuendo(lista.get(0), lista.get(1), lista.get(2), lista.get(3), evento))
						.collect(Collectors.toList());
			} else {
				return cartesianProduct(prendasSuperiores, prendasInferiores, calzados, accesorios).stream()
						.map(lista -> new Atuendo(lista.get(0), lista.get(1), lista.get(2), lista.get(3)))
						.collect(Collectors.toList());
			}
		}
	}

	public List<Atuendo> atuendosValidosParaEvento(Evento evento, int sensibilidad) {
		return generarSugerencias(this.administrarProveedores.obtenerTemperatura(evento.getFechaEvento()), sensibilidad,
				evento);
	}

	public List<Atuendo> atuendosValidosParaAhora(int sensibilidad) {
		return generarSugerencias(this.administrarProveedores.obtenerTemperaturaActual(), sensibilidad, null);
	}

//	public Atuendo generarSugerenciaParaEvento(Evento evento) {
//		return this.obtenerAtuendoRandom(atuendosValidosParaEvento(evento));
//	}
//
//	public Atuendo generarSugerenciaParaAhora() {
//		return this.obtenerAtuendoRandom(this.atuendosValidosParaAhora());
//	}

	public Set<Set<Prenda>> obtenerCombinacionesDePrenda(Set<Prenda> prendas, Double temperatura, int sensibilidad) {
		return powerSet(prendas)
				.stream().filter(this::prendasTienenNivelesDeCapaValidos).filter(conjuntoDePrendas -> this
						.prendasTienenNivelesDeAbrigoValidos(conjuntoDePrendas, temperatura, sensibilidad))
				.collect(Collectors.toSet());
	}

	public Boolean prendasTienenNivelesDeAbrigoValidos(Set<Prenda> conjuntoDePrendas, Double temperatura,
			int sensibilidad) {
		return conjuntoDePrendas.stream().anyMatch(prenda -> prenda.getTipo().nivelDeAbrigo() == 0)
				|| Abrigo.obtenerNivelesDeAbrigo(temperatura, sensibilidad)
						.contains(this.obtenerPuntosDeAbrigo(conjuntoDePrendas));
	}

	public int obtenerPuntosDeAbrigo(Set<Prenda> prendas) {
		return prendas.stream().mapToInt(prenda -> prenda.getTipo().nivelDeAbrigo()).sum();
	}

	public Boolean prendasTienenNivelesDeCapaValidos(Set<Prenda> conjuntoDePrendas) {
		List<Integer> nivelesDeCapa = conjuntoDePrendas.stream().map(prenda -> prenda.getTipo().nivelDeCapa())
				.collect(Collectors.toList());

		return nivelesDeCapa.contains(0)
				&& nivelesDeCapa.size() == nivelesDeCapa.stream().distinct().collect(Collectors.toList()).size();
	}

	public Set<Set<Prenda>> obtenerCombinacionesNoVacias(Set<Prenda> prendas, Double temperatura, int sensibilidad) {
		return obtenerCombinacionesDePrenda(prendas, temperatura, sensibilidad).stream().filter(set -> !set.isEmpty())
				.collect(Collectors.toSet());
	}

	public void addPrenda(Prenda unaPrenda) {
		this.prendas.add(unaPrenda);
	}

	public void setPrendas(List<Prenda> prendasNuevas) {
		this.prendas = prendasNuevas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Prenda> getPrendas() {
		return this.prendas;
	}

	public boolean laPrendaEstaEnElGuardaRopa(Prenda unaPrenda) {
		return prendas.contains(unaPrenda);
	}
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios){
		this.usuarios = usuarios;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}