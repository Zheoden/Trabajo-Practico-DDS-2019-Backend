package modelo.enums.comportamiento;

import java.util.Arrays;
import modelo.enums.Categoria;
import modelo.enums.Material;
import modelo.interfaces.TipoPrenda;

public enum TipoSuperior implements TipoPrenda {
	BUZO {
		@Override
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.SEDA, Material.JEAN, Material.GABARDINA, Material.TERCIOPELO).contains(material);
		}

		@Override
		public int nivelDeCapa() {
			return 4;
		}
	}, 
	CAMISA {
		@Override
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.TERCIOPELO, Material.POLAR, Material.LYCRA).contains(material);
		}

		@Override
		public int nivelDeCapa() {
			return 2;
		}
	},
	CAMPERA {
		@Override
		public int nivelDeCapa() {
			return 5;
		}
	}, 
	REMERACORTA {
		@Override
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.TERCIOPELO, Material.JEAN).contains(material);
		}

		@Override
		public int nivelDeCapa() {
			return 1;
		}
	}, 
	REMERALARGA {
		@Override
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.TERCIOPELO, Material.JEAN).contains(material);
		}		
	},
	SWEATER {
		@Override
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.SEDA, Material.JEAN, Material.GABARDINA, Material.TERCIOPELO).contains(material);
		}

		@Override
		public int nivelDeCapa() {
			return 3;
		}
	};

	public Categoria categoria() {
		// TODO Auto-generated method stub
		return Categoria.PARTE_SUPERIOR;
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
