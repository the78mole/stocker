package de.themole.stocker.dao;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.eclipse.persistence.annotations.VariableOneToOne;

import de.themole.stocker.dao.location.ILocation;
import lombok.Data;

@Data
@Embeddable
public class StorageUnitRef implements Serializable{

	private static final long serialVersionUID = -7241136810112582940L;

	@VariableOneToOne
	ILocation location;

	/**
	 * If the specified location is a bulk storage location, the item count not accurate.
	 */
	@Basic
	Boolean isBulk;
	
	@Embedded
	StorageUnit storage;
	
}
