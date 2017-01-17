package de.themole.stocker.dao;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class ItemRef implements Serializable {

	private static final long serialVersionUID = 4167828680754311331L;

	@OneToMany
	Transaction transaction;
	
	@Embedded
	Item item;
}
