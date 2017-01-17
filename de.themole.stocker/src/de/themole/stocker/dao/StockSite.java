package de.themole.stocker.dao;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.themole.stocker.dao.location.ILocation;
import lombok.Data;

@Entity
@Data
@Table(name="stock_site")
public class StockSite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "NAME", nullable = false)
	String name;
	
	@Embedded
	ILocation location;

	@OneToMany
	StorageUnitRef storage;
}
