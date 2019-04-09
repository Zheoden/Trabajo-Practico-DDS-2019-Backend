package modelo;
import modelo.ClasesUtilsPrendas;

public class Prenda {
	
	Tipo.Tipos tipo; //remera, short, pantalón, etc.
	ClasesUtilsPrendas.Tela tela; //algodón, seda, cuero, etc.
	ClasesUtilsPrendas.Color colorPrimario; //color primario de la prenda. No puede ser NULL.
	ClasesUtilsPrendas.Color colorSecundario; //color secundario de la prenda. Puede ser NULL. 
	
}
