package de.themole.stocker.dao.location;

import javax.persistence.Basic;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class XYZIntLocation implements ILocation {

	private static final long serialVersionUID = 5737180052256885115L;

	@Basic
	Long x;
	
	@Basic
	Long y;
	
	@Basic
	Long z;
}
