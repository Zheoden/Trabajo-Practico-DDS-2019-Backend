package modelo.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Color{
	BLANCO, NEGRO, 
	@JsonProperty("AZUL")
	AZUL,
	@JsonProperty("ROJO")
	ROJO, VERDE, AMARILLO, VIOLETA, ROSA, SALMON, MARRON, GRIS, NARANJA, 
	CELESTE, BORDO, BEIGE, CAQUI, CARMESI, TURQUESA, NULL;
}