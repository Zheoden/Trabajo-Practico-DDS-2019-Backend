package modelo.dtos;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "color")
public enum Color {
	BLANCO, NEGRO, AZUL, ROJO, VERDE, AMARILLO, VIOLETA, ROSA, SALMON, MARRON, GRIS, NARANJA, CELESTE, BORDO, BEIGE,
	CAQUI, CARMESI, TURQUESA, NULL;
}
