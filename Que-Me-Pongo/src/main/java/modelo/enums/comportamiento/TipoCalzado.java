package modelo.enums.comportamiento;

import java.util.Arrays;
import modelo.enums.Categoria;
import modelo.enums.Material;
import modelo.interfaces.TipoPrenda;

public enum TipoCalzado implements TipoPrenda {
	ZAPATILLAS {
		@Override
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.GABARDINA, Material.LYCRA, Material.OXFORD, Material.POLAR, Material.SEDA, Material.TERCIOPELO).contains(material);
		}
	},
	ZAPATOS {
	},
	ZAPATOSDETACON {
	},
	OJOTAS {
		@Override
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.ALGODON, Material.GABARDINA, Material.JEAN, Material.LINO, Material.LYCRA, Material.OXFORD, Material.POLAR, Material.SEDA, Material.TERCIOPELO).contains(material);
		}
	};

	public Categoria categoria() {
		return Categoria.CALZADO;
	}

	@Override
	public int nivelDeCapa() {
		return 0;
	}

	@Override
	public boolean esMaterialValido(Material material) {
		return !Arrays.asList(Material.GABARDINA, Material.JEAN, Material.LINO, Material.LYCRA, Material.OXFORD, Material.POLAR, Material.SEDA, Material.TERCIOPELO).contains(material);
	}
	
	@Override
	public int nivelDeAbrigo() {
		return 0;
	}
}
