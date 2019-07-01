package modelo.clases;

import modelo.enums.Categoria;
import modelo.enums.Color;
import modelo.enums.Material;
import modelo.interfaces.TipoPrenda;

public class Prenda {
	
	String direccionImagen;
	TipoPrenda tipo;
	Material material;
	Color colorPrimario;
	Color colorSecundario;
	
	public Prenda(TipoPrenda tipo, Material material, Color colorPrimario, Color colorSecundario) {
		this(tipo, material, colorPrimario);
		if (colorPrimario != colorSecundario) {
			this.setColorSecundario(colorSecundario);
		} else {
			System.out.print("Se intento asignar un color secundario igual al primario. No se realizo dicha asignacion");
		}
	}
	
	public Prenda(TipoPrenda tipo, Material material, Color colorPrimario) {
		this(tipo, colorPrimario);
		if (this.esMaterialValido(tipo, material)) {
			this.setTela(material);
		} else {
			System.out.print("Se intento crear una prenda con una combinacion Tipo - Material invalida. Se dejo el material como nulo.");
		}
	}

	public Prenda(TipoPrenda tipo, Color colorPrimario) {
		this.setTipo(tipo);
		this.setColorPrimario(colorPrimario);
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