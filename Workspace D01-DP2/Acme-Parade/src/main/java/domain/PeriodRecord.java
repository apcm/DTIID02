
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class PeriodRecord extends Record {

	private Integer				startYear;
	private Integer				endYear;
	private Collection<String>	photos;


	@Min(0)
	public Integer getStartYear() {
		return this.startYear;
	}

	public void setStartYear(final Integer startYear) {
		this.startYear = startYear;
	}

	@Min(0)
	public Integer getEndYear() {
		return this.endYear;
	}

	public void setEndYear(final Integer endYear) {
		this.endYear = endYear;
	}

	public Collection<String> getPhotos() {
		return this.photos;
	}

	public void setPhotos(final Collection<String> photos) {
		this.photos = photos;
	}

}
