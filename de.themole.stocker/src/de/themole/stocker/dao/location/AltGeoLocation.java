package de.themole.stocker.dao.location;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class AltGeoLocation implements ILocation {
	
	private static final long serialVersionUID = -2337369918687924707L;

	@Basic
	Double longitude;
	
	@Basic
	Double latitude;
	
	@Basic
	Double altitude;
	
}
