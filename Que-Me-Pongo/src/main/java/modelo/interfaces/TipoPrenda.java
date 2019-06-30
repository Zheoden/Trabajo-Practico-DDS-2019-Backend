package modelo.interfaces;

import java.util.List;
import modelo.enums.Categoria;
import modelo.enums.Material;

public interface TipoPrenda {
	//CADA TIPO DE PRENDA SABE A QUE CATEGORIA PERTENCE
	Categoria categoria();

	//CADA TIPO DE PRENDA CONOCE A SUS MATERIALES CON LOS QUE ES COMPATIBLE
	List<Material> materialesValidos();

	//CADA TIPO DE PRENDA TIENE SU "CAPA" QUE DEFINE CUANDO SE PUEDE SUPERPONER CON OTRA PRENDA DEL MISMO TIPO
	//SI TIENE 0 SE SUPERPONE CON CUALQUIERA
	Integer nivelDeCapa();

	//CADA TIPO DE PRENDA TIENE SU NIVEL DE ABRIGO
	//SI TIENE 0 NO IMPORTA EL ABRIGO, POR EJ ALGUNOS CALZADO Y ACCESORIOS
	Integer nivelDeAbrigo();

	default Boolean sePuedePonerJuntoA(TipoPrenda tipoPrenda) {
		return this.nivelDeCapa() != tipoPrenda.nivelDeCapa() ||
				this.categoria() != tipoPrenda.categoria() ||
				this.nivelDeCapa() == 0;
	}
}
