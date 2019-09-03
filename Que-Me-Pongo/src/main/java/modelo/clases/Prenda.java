package modelo.clases;

import modelo.dtos.Categoria;
import modelo.dtos.Color;
import modelo.dtos.Material;
import modelo.dtos.TipoPrenda;

public class Prenda {

	String direccionImagen;
	TipoPrenda tipo;
	Material material;
	Color colorPrimario;
	Color colorSecundario;
	boolean formal;

	public boolean isFormal() {
		return formal;
	}

	public void setInformal(boolean formal) {
		this.formal = formal;
	}

	public Prenda(TipoPrenda tipo, Material material, Color colorPrimario, Color colorSecundario,boolean formal) {
		this(tipo, material, colorPrimario,formal);
		if (colorPrimario != colorSecundario) {
			this.setColorSecundario(colorSecundario);
		} else {
			System.out
					.println("Se intento asignar un color secundario igual al primario. No se realizo dicha asignacion");
		}
	}

	public Prenda(TipoPrenda tipo, Material material, Color colorPrimario,boolean formal) {
		this(tipo, colorPrimario,formal);
		if (this.esMaterialValido(tipo, material)) {
			this.setTela(material);
			this.setInformal(formal);
		} else {
			System.out.println(
					"Se intento crear una prenda con una combinacion Tipo - Material invalida. Se dejo el material como nulo.");
		}
	}

	public Prenda(TipoPrenda tipo, Color colorPrimario,boolean informal) {
		this.setTipo(tipo);
		this.setColorPrimario(colorPrimario);
		this.setInformal(informal);
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

	public Material getTela() {
		return material;
	}

	public void setTela(Material material) {
		this.material = material;
	}

	public String getDireccionImagen() {
		return direccionImagen;
	}

	public void setDireccionImagen(String direccionImagen) {
		this.direccionImagen = direccionImagen;
	}
}