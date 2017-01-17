package de.themole.stocker.dao;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Update implements Serializable {

	private static final long serialVersionUID = -194980582907450026L;

	@Temporal(TemporalType.TIMESTAMP)
	Calendar timestamp;
	
	@Basic
	String logtext;
}
