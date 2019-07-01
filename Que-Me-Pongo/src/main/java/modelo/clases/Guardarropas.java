package modelo.clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.Sets.cartesianProduct;
import static com.google.common.collect.Sets.powerSet;

import modelo.enums.*;

public class Guardarropas {
	ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	AdministrarProveedores administrarProveedores = new AdministrarProveedores();

	public Guardarropas(ArrayList<Prenda> prendas) {
		this.setPrendas(prendas);
	}
	
	public Set<Prenda> obtenerPrendasSuperiores() {
		return prendas.stream().filter(prenda -> prenda.Categoria() == Categoria.PARTE_SUPERIOR).collect(Collectors.toSet());
	}

	public Set<Prenda> obtenerPrendasInferiores() {
		return prendas.stream().filter(prenda -> prenda.Categoria() == Categoria.PARTE_INFERIOR).collect(Collectors.toSet());
	}

	public Set<Prenda> obtenerCalzados() {
		return prendas.stream().filter(prenda -> prenda.Categoria() == Categoria.CALZADO).collect(Collectors.toSet());
	}

	public Set<Prenda> obtenerAccesorios() {
		return prendas.stream().filter(prenda -> prenda.Categoria() == Categoria.ACCESORIO).collect(Collectors.toSet());
	}
	
	public Atuendo obtenerAtuendoRandom(List<Atuendo> combinaciones) {
		Collections.shuffle(combinaciones);
		return combinaciones.get(0);
	}
	
	public List<Atuendo> generarSugerencias(int temperatura) {
		Set<Set<Prenda>> calzados = obtenerCombinacionesNoVacias(obtenerCalzados(), temperatura);
		Set<Set<Prenda>> prendasInferiores = obtenerCombinacionesNoVacias(obtenerPrendasInferiores(), temperatura);
		Set<Set<Prenda>> prendasSuperiores = obtenerCombinacionesNoVacias(obtenerPrendasSuperiores(), temperatura);
		Set<Set<Prenda>> accesorios = obtenerCombinacionesNoVacias(obtenerAccesorios(), temperatura);

		return cartesianProduct(prendasSuperiores, prendasInferiores, calzados, accesorios).stream()
				.map(lista -> new Atuendo(
						lista.get(0),
						lista.get(1),
						lista.get(2),
						lista.get(3)
						)).collect(Collectors.toList());
	}

//	public List<Atuendo> atuendosValidosParaEvento(Evento evento) {
//		return generarSugerencias(this.administrarProveedores.obtenerTemperatura(evento.getFecha()));
//	}
//
//	public List<Atuendo> atuendosValidosParaAhora() {
//		return generarSugerencias(this.administrarProveedores.obtenerTemperaturaActual());
//	}
//
//	public Atuendo generarSugerenciaParaEvento(Evento evento) {
//		return this.obtenerAtuendoRandom(atuendosValidosParaEvento(evento));
//	}
//
//	public Atuendo generarSugerenciaParaAhora() {
//		return this.obtenerAtuendoRandom(this.atuendosValidosParaAhora());
//	}
	
	private Set<Set<Prenda>> obtenerCombinacionesDePrenda(Set<Prenda> prendas, int temperatura) {
		return powerSet(prendas).stream().filter(this::prendasTienenNivelesDeCapaValidos)
				.filter(conjuntoDePrendas -> this.prendasTienenNivelesDeAbrigoValidos(conjuntoDePrendas, temperatura))
				.collect(Collectors.toSet());
	}
	
	//Recibe conjunto de prendas y dice si es valido para la temperatura indicada.
	private Boolean prendasTienenNivelesDeAbrigoValidos(Set<Prenda> conjuntoDePrendas, int temperatura) {
		return true;
//		return conjuntoDePrendas.stream().anyMatch(prenda -> prenda.getTipo().nivelDeAbrigo().equals(0)) ||
//				Abrigo.obtenerNivelesDeAbrigo(temperatura).contains(this.obtenerPuntosDeAbrigo(conjuntoDePrendas));
	}
	
//	private Integer obtenerPuntosDeAbrigo(Set<Prenda> prendas) {
//		return prendas.stream().mapToInt(prenda -> prenda.getTipo().nivelDeAbrigo()).sum();
//	}
	
	private Boolean prendasTienenNivelesDeCapaValidos(Set<Prenda> conjuntoDePrendas) {
		List<Integer> nivelesDeCapa =
				conjuntoDePrendas.stream()
						.map(prenda -> prenda.getTipo().nivelDeCapa())
						.collect(Collectors.toList());

		return nivelesDeCapa.size() == nivelesDeCapa.stream().distinct().collect(Collectors.toList()).size();
	}
	
	private Set<Set<Prenda>> obtenerCombinacionesNoVacias(Set<Prenda> prendas, int temperatura) {
		return obtenerCombinacionesDePrenda(prendas, temperatura).stream().filter(set -> !set.isEmpty()).collect(Collectors.toSet());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public Prenda getRandomPrendaByCategoria(Categoria categoria) {
		Random rand = new Random();
		List<Prenda> aux = this.prendas.stream().filter( e -> e.isCategoria(categoria) ).collect(Collectors.toList());
		int n = rand.nextInt(aux.size());
		
		return aux.get(n);
	}
	
	public boolean existPrendaByCategoria(Categoria categoria) {
		return this.prendas.stream().anyMatch(e -> e.isCategoria(categoria));
	}
	
	public void addPrenda(Prenda unaPrenda) {
		this.prendas.add(unaPrenda);
	}

	public void setPrendas(ArrayList<Prenda> prendasNuevas) {
		this.prendas = prendasNuevas;
	}

	public ArrayList<Prenda> getPrendas() {
		return this.prendas;
	}
	
}
