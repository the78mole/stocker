package de.themole.stocker.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@NamedQueries({
	@NamedQuery(
			name="Item.findAll", 
			query = "SELECT i FROM Item i"),
	@NamedQuery(
			name = "Item.findAllIds", 
			query = "SELECT i FROM Item i WHERE i.id = :searchId OR i.altIds = :searchId")
})

@Entity
@Data
public class Item implements Serializable {

	private static final long serialVersionUID = 5848683070981009284L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ElementCollection
	List<String> altIds = new ArrayList<String>();
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	Calendar created;

	@ElementCollection
	List<Update> updates = new ArrayList<Update>();

	@Basic
	String name;
	
	@Lob
	@Basic
	String description;

	
	// TODO Add images
	// TODO Add datasheets and manuals (don't forget types)
	
}
