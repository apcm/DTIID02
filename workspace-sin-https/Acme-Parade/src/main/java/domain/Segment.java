
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Segment extends DomainEntity {

	private Double	origLatitude;
	private Double	origLongitude;
	private Double	destLatitude;
	private Double	destLongitude;
	private Date	time;
	private Boolean	finalmode;
	private String	status;
	private Integer	order;


	public Double getorigLatitude() {
		return this.origLatitude;
	}

	public void setorigLatitude(final Double origLatitude) {
		this.origLatitude = origLatitude;
	}

	public Double getorigLongitude() {
		return this.origLongitude;
	}

	public void setorigLongitude(final Double origLongitude) {
		this.origLongitude = origLongitude;
	}

	public Double getdestLatitude() {
		return this.destLatitude;
	}

	public void setdestLatitude(final Double destLatitude) {
		this.destLatitude = destLatitude;
	}

	public Double getdestLongitude() {
		return this.destLongitude;
	}

	public void setdestLongitude(final Double destLongitude) {
		this.destLongitude = destLongitude;
	}

	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getTime() {
		return this.time;
	}

	public void setTime(final Date time) {
		this.time = time;
	}

	public Boolean getFinalmode() {
		return this.finalmode;
	}

	public void setFinalmode(final Boolean finalmode) {
		this.finalmode = finalmode;
	}

	@Pattern(regexp = "^SUBMITTED|ACCEPTED|REJECTED$")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Min(1)
	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(final Integer order) {
		this.order = order;
	}


	//Relationships

	private Parade	parade;


	@ManyToOne(optional = false)
	public Parade getParade() {
		return this.parade;
	}

	public void setParade(final Parade parade) {
		this.parade = parade;
	}

}
