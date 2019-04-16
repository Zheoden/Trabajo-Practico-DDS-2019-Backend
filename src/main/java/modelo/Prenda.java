package modelo;

import java.util.ArrayList;
import java.util.Arrays;

public class Prenda {
	
	public enum Tela{
		TWEED, GABARDINA, LINO, FRANELA, CREPE, ENCAJE, OXFORD, CUERO, JEAN, SEDA, LYCRA, POLAR,
		TERCIOPELO, ALGODON
	}

	public enum Tipo{
		REMERACORTA, REMERALARGA, BUZO, SWEATER, CAMPERA, CAMISA, SACO,
		SHORTS, JOGGINGS, JEANS, BERMUDAS, PANTALON,
		ZAPATILLAS, ZAPATOS, ZAPATOSDETACON, OJOTAS,
		BUFANDA, ANTEOJOS, GORRA, COLLAR, LENTES, AROS,
		VESTIDO, SMOKING, TRAJE
	}
		
	public enum Categoria{
		PARTESUPERIOR, PARTEINFERIOR, CALZADO, ACCESORIO, NULL
	}
	
	public enum Color{
		BLANCO, NEGRO, AZUL, ROJO, VERDE, AMARILLO, VIOLETA, ROSA, SALMON, MARRON, GRIS, NARANJA, 
		CELESTE, BORDO, BEIGE, CAQUI, CARMESI, TURQUESA, NULL
	}
	
	Tipo tipo;
	Tela tela;
	Color colorPrimario;
	Color colorSecundario;
	
	public Prenda(Tipo tipo, Tela tela, Color colorPrimario, Color colorSecundario) throws Exception {
		this.setTipo(tipo);
		if(validarTela(tela)) {
			this.setTela(tela);
		}else {
			throw new Exception("Esta combinacion de prenda y tela no es valida");
		}
		this.setTela(tela);
		this.setColorPrimario(colorPrimario);
		this.setColorSecundario(colorSecundario);
	}
	
	public Prenda(Tipo tipo, Tela tela, Color colorPrimario) {
		this.setTipo(tipo);
		this.setTela(tela);
		this.setColorPrimario(colorPrimario);
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


}
