package modelo.ropa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "material")
public class Material {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	
	public Material() {}
	
	public Material(String unMaterial) {
		this.nombre = unMaterial;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreMaterial() {
		return nombre;
	}

	public void setNombreMaterial(String nombreMaterial) {
		this.nombre = nombreMaterial;
	}
}
