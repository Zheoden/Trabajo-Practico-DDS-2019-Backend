package modelo.enums.comportamiento;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import modelo.enums.Categoria;
import modelo.enums.Material;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoPrenda {
	BUZO {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.SEDA, Material.JEAN, Material.GABARDINA, Material.TERCIOPELO).contains(material);
		}

		public int nivelDeCapa() {
			return 1;
		}
		
		public int nivelDeAbrigo() {
			return 3;
		}
		
		public Categoria categoria() {
			return Categoria.PARTE_SUPERIOR;
		}
	}, 
	CAMISA {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.TERCIOPELO, Material.POLAR, Material.LYCRA).contains(material);
		}
		
		public int nivelDeAbrigo() {
			return 2;
		}
		
		public int nivelDeCapa() {
			return 0;
		}
		
		public Categoria categoria() {
			return Categoria.PARTE_SUPERIOR;
		}
	},
	CAMPERA {
		public boolean esMaterialValido(Material material) {
			return true;
		}
		
		public int nivelDeAbrigo() {
			return 5;
		}
		
		public int nivelDeCapa() {
			return 2;
		}
		
		public Categoria categoria() {
			return Categoria.PARTE_SUPERIOR;
		}
	},
	
	@JsonProperty("REMERACORTA")
	REMERACORTA {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.TERCIOPELO, Material.JEAN).contains(material);
		}
		
		public int nivelDeAbrigo() {
			return 1;
		}
		
		public int nivelDeCapa() {
			return 0;
		}
		
		public Categoria categoria() {
			return Categoria.PARTE_SUPERIOR;
		}
	}, 
	REMERALARGA {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.TERCIOPELO, Material.JEAN).contains(material);
		}
		
		public int nivelDeAbrigo() {
			return 2;
		}
		
		public int nivelDeCapa() {
			return 0;
		}
		
		public Categoria categoria() {
			return Categoria.PARTE_SUPERIOR;
		}
	},
	SWEATER {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.SEDA, Material.JEAN, Material.GABARDINA, Material.TERCIOPELO).contains(material);
		}

		public int nivelDeAbrigo() {
			return 3;
		}
		
		public int nivelDeCapa() {
			return 1;
		}
		
		public Categoria categoria() {
			return Categoria.PARTE_SUPERIOR;
		}
	},
	
	
	ZAPATILLAS {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.GABARDINA, Material.LYCRA, Material.OXFORD, Material.POLAR, Material.SEDA, Material.TERCIOPELO).contains(material);
		}

		public int nivelDeCapa() {
			return 0;
		}
		
		public int nivelDeAbrigo() {
			return 0;
		}
		
		public Categoria categoria() {
			return Categoria.CALZADO;
		}
	},
	ZAPATOS {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.ALGODON, Material.GABARDINA, Material.JEAN, Material.LINO, Material.LYCRA, Material.OXFORD, Material.POLAR, Material.SEDA, Material.TERCIOPELO).contains(material);
		}
		
		public int nivelDeCapa() {
			return 0;
		}

		public int nivelDeAbrigo() {
			return 0;
		}
		
		public Categoria categoria() {
			return Categoria.CALZADO;
		}
	},
	ZAPATOSDETACON {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.ALGODON, Material.GABARDINA, Material.JEAN, Material.LINO, Material.LYCRA, Material.OXFORD, Material.POLAR, Material.SEDA, Material.TERCIOPELO).contains(material);
		}
		
		public int nivelDeCapa() {
			return 0;
		}
		
		public int nivelDeAbrigo() {
			return 0;
		}
		
		public Categoria categoria() {
			return Categoria.CALZADO;
		}
	},
	OJOTAS {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.ALGODON, Material.GABARDINA, Material.JEAN, Material.LINO, Material.LYCRA, Material.OXFORD, Material.POLAR, Material.SEDA, Material.TERCIOPELO).contains(material);
		}

		public int nivelDeCapa() {
			return 0;
		}
		
		public int nivelDeAbrigo() {
			return 0;
		}
		
		public Categoria categoria() {
			return Categoria.CALZADO;
		}
	},
	
	
	
	
	BERMUDAS {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.LYCRA, Material.OXFORD, Material.POLAR, Material.SEDA, Material.TERCIOPELO).contains(material);
		}
		
		public int nivelDeAbrigo() {
			return 2;
		}
		
		public int nivelDeCapa() {
			return 0;
		}
		public Categoria categoria() {
			return Categoria.PARTE_INFERIOR;
		}
	},
	CALZAS {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.OXFORD, Material.SEDA, Material.TERCIOPELO).contains(material);
		}
		
		public int nivelDeAbrigo() {
			return 0;
		}
		
		public int nivelDeCapa() {
			return 0;
		}
		public Categoria categoria() {
			return Categoria.PARTE_INFERIOR;
		}
	},
	PANTALON {
		public boolean esMaterialValido(Material material) {
			return true;
		}
		
		public int nivelDeAbrigo() {
			return 0;
		}
		
		public int nivelDeCapa() {
			return 0;
		}
		
		public Categoria categoria() {
			return Categoria.PARTE_INFERIOR;
		}
	},
	POLLERA {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.LYCRA, Material.POLAR).contains(material);
		}
		
		public int nivelDeAbrigo() {
			return 2;
		}
		
		public int nivelDeCapa() {
			return 0;
		}
		
		public Categoria categoria() {
			return Categoria.PARTE_INFERIOR;
		}
	},
	SHORTS {
		public boolean esMaterialValido(Material material) {
			return !Arrays.asList(Material.CUERO, Material.JEAN, Material.POLAR, Material.SEDA, Material.TERCIOPELO).contains(material);
		}
		
		public int nivelDeAbrigo() {
			return 2;
		}
		
		public int nivelDeCapa() {
			return 0;
		}
		
		public Categoria categoria() {
			return Categoria.PARTE_INFERIOR;
		}
	},
	
	ANTEOJOS{
		public Categoria categoria() {
			return Categoria.ACCESORIO;
		}

		public int nivelDeCapa() {
			return 0;
		}

		public boolean esMaterialValido(Material material) {
			return true;
		}
		
		public int nivelDeAbrigo() {
			return 0;
		}
	},
	BUFANDA{
		public Categoria categoria() {
			return Categoria.ACCESORIO;
		}

		public int nivelDeCapa() {
			return 0;
		}

		public boolean esMaterialValido(Material material) {
			return true;
		}
		
		public int nivelDeAbrigo() {
			return 0;
		}
	},
	GORRA {
		public Categoria categoria() {
			return Categoria.ACCESORIO;
		}

		public int nivelDeCapa() {
			return 0;
		}

		public boolean esMaterialValido(Material material) {
			return true;
		}
		
		public int nivelDeAbrigo() {
			return 0;
		}
	},
	COLLAR {
		public Categoria categoria() {
			return Categoria.ACCESORIO;
		}

		public int nivelDeCapa() {
			return 0;
		}

		public boolean esMaterialValido(Material material) {
			return true;
		}
		
		public int nivelDeAbrigo() {
			return 0;
		}
	},
	LENTES{
		public Categoria categoria() {
			return Categoria.ACCESORIO;
		}

		public int nivelDeCapa() {
			return 0;
		}

		public boolean esMaterialValido(Material material) {
			return true;
		}
		
		public int nivelDeAbrigo() {
			return 0;
		}
	},
	AROS {
		public Categoria categoria() {
			return Categoria.ACCESORIO;
		}

		public int nivelDeCapa() {
			return 0;
		}

		public boolean esMaterialValido(Material material) {
			return true;
		}
		
		public int nivelDeAbrigo() {
			return 0;
		}
	};
	
	public abstract Categoria categoria();
	public abstract boolean esMaterialValido(Material material);
	public abstract int nivelDeCapa();	
	public abstract int nivelDeAbrigo();
}
