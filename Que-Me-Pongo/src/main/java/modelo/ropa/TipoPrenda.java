package modelo.ropa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoPrenda")
public class TipoPrenda {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nombrePrenda;
	
	public TipoPrenda() {}
	
	public TipoPrenda(String tipoPrenda) {
		this.nombrePrenda = tipoPrenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombrePrenda() {
		return nombrePrenda;
	}

	public void setNombrePrenda(String nombrePrenda) {
		this.nombrePrenda = nombrePrenda;
	}
}