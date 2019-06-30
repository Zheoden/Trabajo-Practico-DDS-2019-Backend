package modelo.enums.comportamiento;

import java.util.List;

import modelo.enums.Categoria;
import modelo.enums.Material;
import modelo.interfaces.TipoPrenda;

public enum TipoCalzado implements TipoPrenda {
	ZAPATILLAS, ZAPATOS, ZAPATOSDETACON, OJOTAS,

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
