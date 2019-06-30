package modelo.clases;

import java.util.ArrayList;
import java.util.Arrays;

import modelo.enums.Categoria;
import modelo.enums.Color;
import modelo.enums.Material;
import modelo.enums.Tipo;

public class Prenda {
	
	String direccionImagen;
	Tipo tipo;
	Material material;
	Color colorPrimario;
	Color colorSecundario;
	
	public Prenda(Tipo tipo, Material material, Color colorPrimario, Color colorSecundario) {
		this(tipo, material, colorPrimario);
		if (colorPrimario != colorSecundario) {
			this.setColorSecundario(colorSecundario);
		} else {
			System.out.print("Se intento asignar un color secundario igual al primario. No se realizo dicha asignacion");
		}
		/* TODO: descomentar una vez que se realice la funci�n de validez prenda con la tela (ejemplo, prohibir remera de cuero)
		if(validarTela(tela)) {
			this.setTela(tela);
		}else {
			throw new Exception("Esta combinacion de prenda y tela no es valida");
		} */
	}
	
	public Prenda(Tipo tipo, Material material, Color colorPrimario) {
		this(tipo, colorPrimario);
		this.setTela(material);
	}

	public Prenda(Tipo tipo, Color colorPrimario) {
		this.setTipo(tipo);
		this.setColorPrimario(colorPrimario);
	}
	
	public boolean validarTela(Material material) {
		//this.prenda
		return false;
	}
	
	public Categoria Categoria() {
		return tipo.categoria();
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
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

	public boolean isCategoria(Categoria categoria) {
		return categoria == this.Categoria();
	}
	
	public String getDireccionImagen() {
		return direccionImagen;
	}

	public void setDireccionImagen(String direccionImagen) {
		this.direccionImagen = direccionImagen;
	}
}