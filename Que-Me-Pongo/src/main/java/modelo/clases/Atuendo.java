package modelo.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

@Entity
@Table (name = "Atuendo")
public class Atuendo {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToMany (mappedBy = "atuendos", cascade = CascadeType.ALL)
	List<Prenda> prendas = new ArrayList<>();
	Boolean aceptado;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "evento_id")
	@JsonIgnore
	Evento evento;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "eventoAA_id")
	@JsonIgnore
	Evento eventoAA;
	
	Integer calificacion;
	
	public Atuendo() {}

	public Atuendo(Prenda superior, Prenda inferior, Prenda calzado, Prenda accesorio) {
		this.addPrenda(superior);
		this.addPrenda(inferior);
		this.addPrenda(calzado);
		this.addPrenda(accesorio);
		this.setAceptado(false);
		this.setCalificacion(-1);
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
		this.setAceptado(false);
		this.setCalificacion(-1);
	}

	public Atuendo(Set<Prenda> superior, Set<Prenda> inferior, Set<Prenda> calzado, Set<Prenda> accesorio,
			Evento evento) {
		this(superior, inferior, calzado, accesorio);
		this.setEvento(evento);
	}
	
	public Atuendo(Set<Prenda> superior, Set<Prenda> inferior, Set<Prenda> calzado) {
		this.addListPrenda(Lists.newArrayList(superior));
		this.addListPrenda(Lists.newArrayList(inferior));
		this.addListPrenda(Lists.newArrayList(calzado));
		this.setAceptado(false);
		this.setCalificacion(-1);
	}

	public Atuendo(Set<Prenda> superior, Set<Prenda> inferior, Set<Prenda> calzado,
			Evento evento) {
		this(superior, inferior, calzado);
		this.setEvento(evento);
	}

	public void modificarEstadoDePrendas(Boolean nuevoEstado) {
		prendas.forEach(elemento -> elemento.setEnUso(nuevoEstado));
	}

	public List<Prenda> getPrendas() {
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

	public Evento getEventoAA() {
		return eventoAA;
	}

	public void setEventoAA(Evento eventoAA) {
		this.eventoAA = eventoAA;
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
	
	public void setPrendas(List<Prenda> prendas) {
		this.prendas = prendas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setCalificacion(Integer numero) {
		this.calificacion = numero;
	}
	
	public Integer getCalificacion() {
		return calificacion;
	}
}