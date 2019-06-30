package modelo.enums.comportamiento;

import java.util.Arrays;
import modelo.enums.Categoria;
import modelo.enums.Material;
import modelo.interfaces.TipoPrenda;

public enum TipoSuperior implements TipoPrenda {
	BUZO {
		@Override
		public boolean esMaterialValido(Material material) {
			return Arrays.asList(Material.CUERO, Material.SEDA, Material.JEAN).contains(material);
		}

		@Override
		public int nivelDeCapa() {
			return 4;
		}
	}, 
	CAMISA {
		@Override
		public boolean esMaterialValido(Material material) {
			return true;
		}

		@Override
		public int nivelDeCapa() {
			return 2;
		}
	},
	CAMPERA {
		@Override
		public boolean esMaterialValido(Material material) {
			return true;
		}

		@Override
		public int nivelDeCapa() {
			return 5;
		}
	}, 
	REMERACORTA {
		@Override
		public boolean esMaterialValido(Material material) {
			return true;
		}

		@Override
		public int nivelDeCapa() {
			return 1;
		}
	}, 
	REMERALARGA {
		@Override
		public boolean esMaterialValido(Material material) {
			return true;
		}		
	},
	SWEATER {
		@Override
		public boolean esMaterialValido(Material material) {
			return true;
		}

		@Override
		public int nivelDeCapa() {
			return 3;
		}
	};

	public Categoria categoria() {
		// TODO Auto-generated method stub
		return Categoria.PARTESUPERIOR;
	}

	@Override
	public boolean esMaterialValido(Material material) {
		return true;
	}

	@Override
	public int nivelDeCapa() {
		return 0;
	}
}
