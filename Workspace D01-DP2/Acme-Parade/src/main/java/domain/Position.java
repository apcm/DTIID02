
package domain;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Position extends DomainEntity {

	private String	positionEsp;
	private String	positionEng;


	@NotBlank
	public String getPositionEsp() {
		return this.positionEsp;
	}

	public void setPositionEsp(final String positionEsp) {
		this.positionEsp = positionEsp;
	}

	@NotBlank
	public String getPositionEng() {
		return this.positionEng;
	}

	public void setPositionEng(final String positionEng) {
		this.positionEng = positionEng;
	}

}
