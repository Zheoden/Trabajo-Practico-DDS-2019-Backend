package modelo.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.google.common.collect.Lists;

public class Atuendo {

	ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	Boolean aceptado;
	Evento evento;

	public Atuendo(Prenda superior, Prenda inferior, Prenda calzado, Prenda accesorio) {
		this.addPrenda(superior);
		this.addPrenda(inferior);
		this.addPrenda(calzado);
		this.addPrenda(accesorio);
	}

	public Atuendo(Prenda superior, Prenda inferior, Prenda calzado, Prenda accesorio, Evento evento) {
		this(superior, inferior, calzado, accesorio);
		this.setEvento(evento);
	}

	public Atuendo(Set<Prenda> superior, Set<Prenda> inferior, Set<Prenda> calzado, Set<Prenda> accesorio) {
		this.addListPrenda(Lists.newArrayList(superior));
		this.addListPrenda(Lists.newArrayList(inferior));
		this.addListPrenda(Lists.newArrayList(calzado));
		this.addListPrenda(Lists.newArrayList(accesorio));
	}

	public Atuendo(Set<Prenda> superior, Set<Prenda> inferior, Set<Prenda> calzado, Set<Prenda> accesorio,
			Evento evento) {
		this(superior, inferior, calzado, accesorio);
		this.setEvento(evento);
	}

	public void modificarEstadoDePrendas(Boolean nuevoEstado) {
		prendas.forEach(elemento -> elemento.setEnUso(nuevoEstado));
	}

	public ArrayList<Prenda> getPrendas() {
		return prendas;
	}

	public void addPrenda(Prenda unaPrenda) {
		this.prendas.add(unaPrenda);
	}

	public void addListPrenda(List<Prenda> prendas) {
		this.prendas.addAll(prendas);
	}

	public void aceptar() {
		this.aceptado = true;
		this.modificarEstadoDePrendas(true);
	}

	public void rechazar() {
		this.aceptado = false;
		this.modificarEstadoDePrendas(false);
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Boolean getAceptado() {
		return aceptado;
	}

	public void setAceptado(Boolean aceptado) {
		this.aceptado = aceptado;
	}

	public Atuendo(ArrayList<Prenda> prendas) {
		this.prendas = prendas;
	}
}
