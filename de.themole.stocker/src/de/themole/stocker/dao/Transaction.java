package de.themole.stocker.dao;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Transaction implements Serializable {

	private static final long serialVersionUID = 655391254958332577L;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	Calendar created;
	
	/**
	 * If this field
	 *  is true, the real amount has been counted. 
	 * If false, the amount is recorded as a change.
	 * E.g. if a physical inventory has been run, the counted value is entered and marked as absolute.
	 * All following changes will be based on it.  
	 */
	@Basic
	Boolean absolute;
	
	// TODO Add project accounting 
	
	@Basic
	String usage;
	
	@Basic
	Long amount;
	
}
