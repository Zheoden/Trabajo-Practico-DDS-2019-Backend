package modelo.dtos;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "material")
public enum Material {
	ALGODON, CUERO, GABARDINA, JEAN, LINO, LYCRA, OXFORD, POLAR, SEDA, TERCIOPELO;
}

