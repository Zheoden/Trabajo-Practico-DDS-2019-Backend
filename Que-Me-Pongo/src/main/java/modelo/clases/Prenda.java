package modelo.clases;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import modelo.dtos.Categoria;
import modelo.dtos.Color;
import modelo.dtos.Material;
import modelo.dtos.TipoPrenda;

@Entity
@Table(name = "Prenda")
public class Prenda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	private String nombre;
	@Column(columnDefinition="varchar(21000)")
	String imagen;
	@Enumerated(EnumType.STRING)
	TipoPrenda tipo;
	@Enumerated(EnumType.STRING)
	Material material;
	@Enumerated(EnumType.STRING)
	Color colorPrimario;
	@Enumerated(EnumType.STRING)
	Color colorSecundario;
	Boolean enUso;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "guardarropas_id", referencedColumnName = "id")
	@JsonIgnore
	Guardarropas guardarropas;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "AtuendoPorPrenda", joinColumns = @JoinColumn(name = "prenda_id"), inverseJoinColumns = @JoinColumn(name = "atuendo_id"))
	@JsonIgnore
	List<Atuendo> atuendos = new ArrayList<>();
	
	public Prenda(String nombrePrenda, TipoPrenda tipo, Material material, Color colorPrimario, Color colorSecundario) {
		this(nombrePrenda, tipo, material, colorPrimario);
		this.setEnUso(false);
		if (colorPrimario != colorSecundario) {
			this.setColorSecundario(colorSecundario);
		} else {
			System.out
					.println("Se intento asignar un color secundario igual al primario. No se realizo dicha asignacion");
		}
	}

	public Prenda(String nombrePrenda, TipoPrenda tipo, Material material, Color colorPrimario) {
		this(nombrePrenda, tipo, colorPrimario);
		this.setEnUso(false);
		if (this.esMaterialValido(tipo, material)) {
			this.setMaterial(material);
		} else {
			System.out.println(
					"Se intento crear una prenda con una combinacion Tipo - Material invalida. Se dejo el material como nulo.");
		}
	}

	public Prenda(String nombrePrenda, TipoPrenda tipo, Color colorPrimario, String imagen) {
		this(nombrePrenda, tipo, colorPrimario);
		this.setImagen(imagen);
	}
	
	public Prenda(String nombrePrenda, TipoPrenda tipo, Color colorPrimario) {
		this.setNombre(nombrePrenda);
		this.setTipo(tipo);
		this.setColorPrimario(colorPrimario);
		this.setEnUso(false);
	}
	
	public Prenda()
	{
		
	}

	public boolean esMaterialValido(TipoPrenda tipo, Material material) {
		return tipo.esMaterialValido(material);
	}

	public Categoria Categoria() {
		return tipo.categoria();
	}

	public TipoPrenda getTipo() {
		return tipo;
	}

	public void setTipo(TipoPrenda tipo) {
		this.tipo = tipo;
	}

	public Color getColorPrimario() {
		return colorPrimario;
	}

	public void setColorPrimario(Color colorPrimario) {
		this.colorPrimario = colorPrimario;
	}

	public Color getColorSecundario() {
		return colorSecundario;
	}

	public void setColorSecundario(Color colorSecundario) {
		this.colorSecundario = colorSecundario;
	}
	
	public Boolean getEnUso() {
		return this.enUso;
	}

	public void setEnUso(Boolean enUso) {
		this.enUso = enUso;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Guardarropas getGuardarropas(){
		return guardarropas;
	}
	
	public void setGuardarropas(Guardarropas guardarropas) {
		this.guardarropas = guardarropas;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Atuendo> getAtuendos() {
		return atuendos;
	}

	public void setAtuendos(List<Atuendo> atuendos) {
		this.atuendos = atuendos;
	}
	
	public void agregarseAlAtuendo(Atuendo atuendo) {
		this.getAtuendos().add(atuendo);
	}
}