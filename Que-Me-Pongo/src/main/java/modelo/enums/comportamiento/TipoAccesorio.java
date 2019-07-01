package modelo.enums.comportamiento;

import modelo.enums.Categoria;
import modelo.enums.Material;
import modelo.interfaces.TipoPrenda;

public enum TipoAccesorio implements TipoPrenda {
	ANTEOJOS, BUFANDA, GORRA, COLLAR, LENTES, AROS;

	public Categoria categoria() {
		return Categoria.ACCESORIO;
	}

	@Override
	public int nivelDeCapa() {
		return 0;
	}

	@Override
	public boolean esMaterialValido(Material material) {
		return true;
	}
	
	@Override
	public int nivelDeAbrigo() {
		return 0;
	}
}
