
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Segment extends DomainEntity {

	private Double	origLatitude;
	private Double	origLongitude;
	private Double	destLatitude;
	private Double	destLongitude;
	private Date	startTime;
	private Date	arriveTime;
	private Integer	segmentOrder;

	@NotNull
	public Double getOrigLatitude() {
		return this.origLatitude;
	}

	public void setOrigLatitude(final Double origLatitude) {
		this.origLatitude = origLatitude;
	}

	@NotNull
	public Double getOrigLongitude() {
		return this.origLongitude;
	}

	public void setOrigLongitude(final Double origLongitude) {
		this.origLongitude = origLongitude;
	}

	@NotNull
	public Double getDestLatitude() {
		return this.destLatitude;
	}

	public void setDestLatitude(final Double destLatitude) {
		this.destLatitude = destLatitude;
	}

	@NotNull
	public Double getDestLongitude() {
		return this.destLongitude;
	}

	public void setDestLongitude(final Double destLongitude) {
		this.destLongitude = destLongitude;
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
	
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	@NotNull
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}
	
}