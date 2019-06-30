package modelo.enums.comportamiento;

import java.util.Arrays;

import modelo.enums.Categoria;
import modelo.enums.Material;
import modelo.interfaces.TipoPrenda;

public enum TipoInferior implements TipoPrenda {
	BERMUDAS {
		@Override
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.LYCRA, Material.OXFORD, Material.POLAR, Material.SEDA, Material.TERCIOPELO).contains(material);
		}
	},
	CALZAS {
		@Override
		public int nivelDeCapa() {
			return 0;
		}
		@Override
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.OXFORD, Material.SEDA, Material.TERCIOPELO).contains(material);
		}
	},
	PANTALON {
	},
	POLLERA {
		@Override
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.LYCRA, Material.POLAR).contains(material);
		}
	},
	SHORTS {
		@Override
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.JEAN, Material.POLAR, Material.SEDA, Material.TERCIOPELO).contains(material);
		}
	};

	public Categoria categoria() {
		return Categoria.PARTE_INFERIOR;
	}

	@Override
	public int nivelDeCapa() {
		return 1;
	}

	@Override
	public boolean esMaterialValido(Material material) {
		return true;
	}
}
