package de.themole.stocker.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class StorageUnit implements Serializable {

	private static final long serialVersionUID = 3629439832745344221L;

	@OneToMany
	ItemRef item;
	
	@OneToMany
	StorageUnitRef storage;
}
