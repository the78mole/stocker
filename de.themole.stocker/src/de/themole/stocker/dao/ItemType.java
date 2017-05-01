package de.themole.stocker.dao;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class ItemType implements Serializable {
	
	private static final long serialVersionUID = 4399193851280644763L;

	/*
	 * This class should add required and default properties of different item types 
	 * and or subtypes of a type to create an inheritance tree.
	 * An example could be: Screw, Metric Screw
	 * The parent then can require a length dimension to be defined,
	 * the Metric screw should also require the head geometry and the diameter of the shaft
	 * and optionally the diameter and size of the head. 
	 */
}
