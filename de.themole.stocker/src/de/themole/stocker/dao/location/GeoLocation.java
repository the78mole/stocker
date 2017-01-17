package de.themole.stocker.dao.location;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class GeoLocation implements ILocation {

	@Basic
	Double longitude;

	@Basic
	Double latitude;

}
