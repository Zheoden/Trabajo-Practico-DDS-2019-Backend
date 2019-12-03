package modelo.ropa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categoria")
public class Categoria {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	
	public Categoria() {}
	
	public Categoria(String unaCategoria) {
		this.nombre = unaCategoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombre = nombreCategoria;
	}
}