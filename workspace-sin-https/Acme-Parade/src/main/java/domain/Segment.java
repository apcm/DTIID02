
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
	private Date	arriveTime;
	private Boolean	finalMode;
	private String	status;
	private Integer	segmentOrder;


	public Double getOrigLatitude() {
		return this.origLatitude;
	}

	public void setOrigLatitude(final Double origLatitude) {
		this.origLatitude = origLatitude;
	}

	public Double getOrigLongitude() {
		return this.origLongitude;
	}

	public void setOrigLongitude(final Double origLongitude) {
		this.origLongitude = origLongitude;
	}

	public Double getDestLatitude() {
		return this.destLatitude;
	}

	public void setDestLatitude(final Double destLatitude) {
		this.destLatitude = destLatitude;
	}

	public Double getDestLongitude() {
		return this.destLongitude;
	}

	public void setDestLongitude(final Double destLongitude) {
		this.destLongitude = destLongitude;
	}

	public Boolean getFinalMode() {
		return this.finalMode;
	}

	public void setFinalMode(final Boolean finalMode) {
		this.finalMode = finalMode;
	}

	@Pattern(regexp = "^SUBMITTED|ACCEPTED|REJECTED$")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Min(1)
	public Integer getSegmentOrder() {
		return this.segmentOrder;
	}

	public void setSegmentOrder(final Integer segmentOrder) {
		this.segmentOrder = segmentOrder;
	}

	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	@NotNull
	public Date getArriveTime() {
		return this.arriveTime;
	}

	public void setArriveTime(final Date arriveTime) {
		this.arriveTime = arriveTime;
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
