package modelo.interfaces;

import modelo.enums.Categoria;
import modelo.enums.Material;

public interface TipoPrenda {
	//CADA TIPO DE PRENDA SABE A QUE CATEGORIA PERTENCE
	Categoria categoria();

	//CADA TIPO DE PRENDA CONOCE A SUS MATERIALES CON LOS QUE ES COMPATIBLE
	boolean esMaterialValido(Material material);

	//CADA TIPO DE PRENDA TIENE SU "CAPA" QUE DEFINE CUANDO SE PUEDE SUPERPONER CON OTRA PRENDA DEL MISMO TIPO
	//SI TIENE 0 SE SUPERPONE CON CUALQUIERA
	//MAXIMO NIVEL DE CAPA: 5
	int nivelDeCapa();
}
