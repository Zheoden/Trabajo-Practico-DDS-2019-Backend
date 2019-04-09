package modelo;

import java.util.ArrayList;
import java.util.Arrays;

public class Tipo {
	
		public enum Tipos{
		REMERACORTA, REMERALARGA, BUZO, SWEATER, CAMPERA, CAMISA, SACO,
		SHORTS, JOGGINGS, JEANS, BERMUDAS, PANTALON,
		ZAPATILLAS, ZAPATOS, ZAPATOSDETACON, OJOTAS,
		BUFANDA, ANTEOJOS, GORRA, COLLAR, LENTES, AROS,
		VESTIDO, SMOKING, TRAJE
		}
		
		public enum Categoria{
			PARTESUPERIOR, PARTEINFERIOR, CALZADO, ACCESORIO, NULL
		} 
		
		public Categoria getCategoria(Tipos unTipo) {
			ArrayList<Tipos> partesSuperiores = new ArrayList<Tipos>(Arrays.asList(Tipos.REMERACORTA, Tipos.REMERALARGA,
					Tipos.BUZO, Tipos.SWEATER, Tipos.CAMPERA, Tipos.CAMISA, Tipos.SACO));
			ArrayList<Tipos> partesInferiores = new ArrayList<Tipos>(Arrays.asList(Tipos.SHORTS, Tipos.JOGGINGS,
					Tipos.JEANS, Tipos.BERMUDAS, Tipos.PANTALON));
			ArrayList<Tipos> partesCalzados = new ArrayList<Tipos>(Arrays.asList(Tipos.ZAPATILLAS, Tipos.ZAPATOS,
					Tipos.ZAPATOSDETACON, Tipos.OJOTAS));
			ArrayList<Tipos> partesAccesorios = new ArrayList<Tipos>(Arrays.asList(Tipos.ANTEOJOS, Tipos.BUFANDA,
					Tipos.GORRA, Tipos.COLLAR, Tipos.LENTES, Tipos.AROS));
			
			if(partesSuperiores.contains(unTipo)) {//elemento en lista es de PARTE SUPERIOR){
				return Categoria.PARTESUPERIOR;
			}
			if(partesInferiores.contains(unTipo)){//elemento en lista es de PARTE SUPERIOR){
				return Categoria.PARTEINFERIOR;
			}
			if(partesCalzados.contains(unTipo)) {//elemento en lista es de PARTE SUPERIOR){
				return Categoria.CALZADO;
			}
			if(partesAccesorios.contains(unTipo)) {//elemento en lista es de PARTE SUPERIOR){
				return Categoria.ACCESORIO;
			}
			
			return Categoria.NULL;
		}
	}

