package modelo.clases;

public class Ciudad {

	private Double temperatura;
	private String nombre;
	
	public Ciudad (String nombre,Double temperatura)
	{
	this.nombre = nombre;
	this.temperatura = temperatura;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
