package modelo.enums.comportamiento;

import java.util.List;

import modelo.enums.Categoria;
import modelo.enums.Material;
import modelo.interfaces.TipoPrenda;

public enum TipoInferior implements TipoPrenda {
	SHORTS, JOGGINGS, JEANS, BERMUDAS, PANTALON,
	ZAPATILLAS, ZAPATOS, ZAPATOSDETACON, OJOTAS,
	BUFANDA, ANTEOJOS, GORRA, COLLAR, LENTES, AROS,
	VESTIDO, SMOKING, TRAJE;

	public Categoria categoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Material> materialesValidos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nivelDeCapa() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int nivelDeAbrigo() {
		// TODO Auto-generated method stub
		return 0;
	}
}
