package modelo.clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import modelo.clases.Abrigo;
import modelo.dtos.Categoria;

import static com.google.common.collect.Sets.cartesianProduct;
import static com.google.common.collect.Sets.powerSet;

public class Guardarropas {
	public ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	public AdministrarProveedores administrarProveedores = new AdministrarProveedores();

	public Guardarropas(ArrayList<Prenda> prendas, AdministrarProveedores administrarProv) {
		this.setPrendas(prendas);
		this.administrarProveedores = administrarProv;
	}
	
	public Guardarropas(ArrayList<Prenda> prendas) {
		this.setPrendas(prendas);
	}

	public int tamanioGuardarropas() {
		return prendas.size();
	}

	public Set<Prenda> obtenerPrendasSuperiores() {
		return prendas.stream().filter(prenda -> prenda.Categoria() == Categoria.PARTE_SUPERIOR)
				.collect(Collectors.toSet());
	}

	public Set<Prenda> obtenerPrendasInferiores() {
		return prendas.stream().filter(prenda -> prenda.Categoria() == Categoria.PARTE_INFERIOR)
				.collect(Collectors.toSet());
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

	public List<Atuendo> generarSugerencias(Double temperatura) {
		Set<Set<Prenda>> calzados = obtenerCombinacionesNoVacias(obtenerCalzados(), temperatura);
		Set<Set<Prenda>> prendasInferiores = obtenerCombinacionesNoVacias(obtenerPrendasInferiores(), temperatura);
		Set<Set<Prenda>> prendasSuperiores = obtenerCombinacionesNoVacias(obtenerPrendasSuperiores(), temperatura);
		Set<Set<Prenda>> accesorios = obtenerCombinacionesNoVacias(obtenerAccesorios(), temperatura);

		return cartesianProduct(prendasSuperiores, prendasInferiores, calzados, accesorios).stream()
				.map(lista -> new Atuendo(lista.get(0), lista.get(1), lista.get(2), lista.get(3)))
				.collect(Collectors.toList());
	}

	public List<Atuendo> atuendosValidosParaEvento(Evento evento) {
		return generarSugerencias(this.administrarProveedores.obtenerTemperatura(evento.getFechaEvento()));
	}

	public List<Atuendo> atuendosValidosParaAhora() {
		return generarSugerencias(this.administrarProveedores.obtenerTemperaturaActual());
	}

//	public Atuendo generarSugerenciaParaEvento(Evento evento) {
//		return this.obtenerAtuendoRandom(atuendosValidosParaEvento(evento));
//	}
//
//	public Atuendo generarSugerenciaParaAhora() {
//		return this.obtenerAtuendoRandom(this.atuendosValidosParaAhora());
//	}

	public Set<Set<Prenda>> obtenerCombinacionesDePrenda(Set<Prenda> prendas, Double temperatura) {
		return powerSet(prendas).stream().filter(this::prendasTienenNivelesDeCapaValidos)
				.filter(conjuntoDePrendas -> this.prendasTienenNivelesDeAbrigoValidos(conjuntoDePrendas, temperatura))
				.collect(Collectors.toSet());
	}

	public Boolean prendasTienenNivelesDeAbrigoValidos(Set<Prenda> conjuntoDePrendas, Double temperatura) {
		return conjuntoDePrendas.stream().anyMatch(prenda -> prenda.getTipo().nivelDeAbrigo() == 0)
				|| Abrigo.obtenerNivelesDeAbrigo(temperatura).contains(this.obtenerPuntosDeAbrigo(conjuntoDePrendas));
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

	public Set<Set<Prenda>> obtenerCombinacionesNoVacias(Set<Prenda> prendas, Double temperatura) {
		return obtenerCombinacionesDePrenda(prendas, temperatura).stream().filter(set -> !set.isEmpty())
				.collect(Collectors.toSet());
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
	
	public boolean laPrendaEstaEnElGuardaRopa(Prenda unaPrenda) {
		return prendas.contains(unaPrenda);
	}

}
