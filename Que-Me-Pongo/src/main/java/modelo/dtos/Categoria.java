package modelo.dtos;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "categoria")
public enum Categoria {
	PARTE_SUPERIOR, PARTE_INFERIOR, CALZADO, ACCESORIO, NULL;

	 Categoria() {
	}
}
