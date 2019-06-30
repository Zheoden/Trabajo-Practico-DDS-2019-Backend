package modelo.enums.comportamiento;

import java.util.List;

import modelo.enums.Categoria;
import modelo.enums.Material;
import modelo.interfaces.TipoPrenda;

public enum TipoAccesorio implements TipoPrenda {
	BUFANDA, ANTEOJOS, GORRA, COLLAR, LENTES, AROS;

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
