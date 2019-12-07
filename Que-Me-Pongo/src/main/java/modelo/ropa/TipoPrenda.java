package modelo.ropa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TipoPrenda")
public class TipoPrenda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nombre;
	
	public TipoPrenda() {}
	
	public TipoPrenda(String tipoPrenda) {
		this.nombre = tipoPrenda;
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

	public void setNombrePrenda(String nombrePrenda) {
		this.nombre = nombrePrenda;
	}
}