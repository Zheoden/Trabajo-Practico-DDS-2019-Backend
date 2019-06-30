package modelo.enums;

public enum Tipo{
	REMERACORTA{
		public int nivelAbrigo() {
			return 1;
		}
	}, 
	
	REMERALARGA{
		public int nivelAbrigo() {
			return 1;
		}
		
		public boolean tipoValido(Material Material) {
			return true;
		}
		
		public Categoria categoria() {
			return Categoria.PARTESUPERIOR;
		}
		
	}, 
	BUZO, SWEATER, CAMPERA, CAMISA, SACO,
	SHORTS, JOGGINGS, JEANS, BERMUDAS, PANTALON,
	ZAPATILLAS, ZAPATOS, ZAPATOSDETACON, OJOTAS,
	BUFANDA, ANTEOJOS, GORRA, COLLAR, LENTES, AROS,
	VESTIDO, SMOKING, TRAJE;

	public Categoria categoria() {
		// TODO Auto-generated method stub
		return null;
	}
}
