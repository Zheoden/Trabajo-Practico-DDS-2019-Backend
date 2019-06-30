package modelo.clases;

import java.util.ArrayList;
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
	
	public void generarSugerencias(Double temperatura) {
		Set<Set<Prenda>> calzados = obtenerCombinacionesNoVacias(obtenerCalzados(), temperatura);
		Set<Set<Prenda>> prendasInferiores = obtenerCombinacionesNoVacias(obtenerPrendasInferiores(), temperatura);
		Set<Set<Prenda>> prendasSuperiores = obtenerCombinacionesNoVacias(obtenerPrendasSuperiores(), temperatura);
		Set<Set<Prenda>> accesorios = obtenerCombinacionesDePrenda(obtenerAccesorios(), temperatura);
		Set<List<Prenda>> aux = (Set<List<Prenda>>) cartesianProduct(prendasSuperiores, prendasInferiores, calzados, accesorios).stream();
		System.out.print(aux);
		
		return;
	}

	public List<Atuendo> atuendosValidosParaEvento(Evento evento) {
		return generarSugerencias(administradorDeProveedores.obtenerTemperatura(evento.getFecha()).getGrados());
	}

	public List<Atuendo> atuendosValidosParaAhora() {
		return generarSugerencias(administradorDeProveedores.obtenerTemperaturaActual().getGrados());
	}

	public Atuendo generarSugerenciaParaEvento(Evento evento) {
		return this.obtenerAtuendoRandom(atuendosValidosParaEvento(evento));
	}

	public Atuendo generarSugerenciaParaAhora() {
		return this.obtenerAtuendoRandom(atuendosValidosParaAhora());
	}
	
	private Set<Set<Prenda>> obtenerCombinacionesDePrenda(Set<Prenda> prendas, Double temperatura) {
		return powerSet(prendas).stream().filter(this::prendasTienenNivelesDeCapaValidos)
				.filter(conjuntoDePrendas -> this.prendasTienenNivelesDeAbrigoValidos(conjuntoDePrendas, temperatura))
				.collect(Collectors.toSet());
	}
	
	private Boolean prendasTienenNivelesDeAbrigoValidos(Set<Prenda> conjuntoDePrendas, Double temperatura) {
		return conjuntoDePrendas.stream().anyMatch(prenda -> prenda.getTipo().nivelDeAbrigo().equals(0)) ||
				Abrigo.obtenerNivelesDeAbrigo(temperatura).contains(this.obtenerPuntosDeAbrigo(conjuntoDePrendas));
	}
	
	private Integer obtenerPuntosDeAbrigo(Set<Prenda> prendas) {
		return prendas.stream().mapToInt(prenda -> prenda.getTipo().nivelDeAbrigo()).sum();
	}
	
	private Boolean prendasTienenNivelesDeCapaValidos(Set<Prenda> conjuntoDePrendas) {
		List<Integer> nivelesDeCapa =
				conjuntoDePrendas.stream()
						.map(prenda -> prenda.getTipo().nivelDeCapa())
						.filter(capa -> capa > 0)
						.collect(Collectors.toList());

		return nivelesDeCapa.stream().allMatch(valor -> Collections.frequency(nivelesDeCapa, valor) == 1);
	}
	
	private Set<Set<Prenda>> obtenerCombinacionesNoVacias(Set<Prenda> prendas, Double temperatura) {
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
