package modelo;

import java.util.ArrayList;
import java.util.Arrays;

import modelo.enums.Categoria;
import modelo.enums.Color;
import modelo.enums.Tela;
import modelo.enums.Tipo;

public class Prenda {
	
	String direccionImagen;
	Tipo tipo;
	Tela tela;
	Color colorPrimario;
	Color colorSecundario;
	
	public Prenda(Tipo tipo, Tela tela, Color colorPrimario, Color colorSecundario) {
		this(tipo, tela, colorPrimario);
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
	
	public Prenda(Tipo tipo, Tela tela, Color colorPrimario) {
		this(tipo, colorPrimario);
		this.setTela(tela);
	}

	public Prenda(Tipo tipo, Color colorPrimario) {
		this.setTipo(tipo);
		this.setColorPrimario(colorPrimario);
	}
	
	public boolean validarTela(Tela tela) {
		//this.prenda
		return false;
	}
	
	public Categoria Categoria() {
		ArrayList<Tipo> partesSuperiores = new ArrayList<Tipo>(Arrays.asList(Tipo.REMERACORTA, Tipo.REMERALARGA,
				Tipo.BUZO, Tipo.SWEATER, Tipo.CAMPERA, Tipo.CAMISA, Tipo.SACO));
		ArrayList<Tipo> partesInferiores = new ArrayList<Tipo>(Arrays.asList(Tipo.SHORTS, Tipo.JOGGINGS,
				Tipo.JEANS, Tipo.BERMUDAS, Tipo.PANTALON));
		ArrayList<Tipo> partesCalzados = new ArrayList<Tipo>(Arrays.asList(Tipo.ZAPATILLAS, Tipo.ZAPATOS,
				Tipo.ZAPATOSDETACON, Tipo.OJOTAS));
		ArrayList<Tipo> partesAccesorios = new ArrayList<Tipo>(Arrays.asList(Tipo.ANTEOJOS, Tipo.BUFANDA,
				Tipo.GORRA, Tipo.COLLAR, Tipo.LENTES, Tipo.AROS));
			
		if(partesSuperiores.contains(this.tipo)) {//elemento en lista es de PARTE SUPERIOR){
			return Categoria.PARTESUPERIOR;
		}
		if(partesInferiores.contains(this.tipo)){//elemento en lista es de PARTE SUPERIOR){
			return Categoria.PARTEINFERIOR;
		}
		if(partesCalzados.contains(this.tipo)) {//elemento en lista es de PARTE SUPERIOR){
			return Categoria.CALZADO;
		}
		if(partesAccesorios.contains(this.tipo)) {//elemento en lista es de PARTE SUPERIOR){
			return Categoria.ACCESORIO;
		}
		
		return Categoria.NULL;
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

	public Tela getTela() {
		return tela;
	}

	public void setTela(Tela tela) {
		this.tela = tela;
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