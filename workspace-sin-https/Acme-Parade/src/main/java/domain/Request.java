
package domain;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Table(indexes = {
	@Index(columnList = "status, rowPosition, columnPosition")
})
public class Request extends DomainEntity {

	private String	status;
	private int		rowPosition;
	private int		columnPosition;
	private String	rejectReason;


	@Pattern(regexp = "^PENDING|APPROVED|REJECTED$")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Min(0)
	public int getRowPosition() {
		return this.rowPosition;
	}

	public void setRowPosition(final int rowPosition) {
		this.rowPosition = rowPosition;
	}

	@Min(0)
	public int getColumnPosition() {
		return this.columnPosition;
	}

	public void setColumnPosition(final int columnPosition) {
		this.columnPosition = columnPosition;
	}

	public String getRejectReason() {
		return this.rejectReason;
	}

	public void setRejectReason(final String rejectReason) {
		this.rejectReason = rejectReason;
	}


	//------------Relationships------------

	private Procession	procession;


	@ManyToOne
	public Procession getProcession() {
		return this.procession;
	}

	public void setProcession(final Procession procession) {
		this.procession = procession;
	}

}
