package de.themole.stocker.dao.location;

import javax.persistence.Basic;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class XYZFloatLocation implements ILocation {

	private static final long serialVersionUID = 5737180052256885115L;

	@Basic
	Double x;
	
	@Basic
	Double y;
	
	@Basic
	Double z;
}
