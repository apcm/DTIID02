
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class LegalRecord extends Record {

	private String				legalName;
	private Collection<String>	applicableLaws;
	private Integer				vatNumber;


	@NotBlank
	public String getLegalName() {
		return this.legalName;
	}

	public void setLegalName(final String legalName) {
		this.legalName = legalName;
	}

	@ElementCollection
	public Collection<String> getApplicableLaws() {
		return this.applicableLaws;
	}

	public void setApplicableLaws(final Collection<String> applicableLaws) {
		this.applicableLaws = applicableLaws;
	}

	@Min(0)
	public Integer getVatNumber() {
		return this.vatNumber;
	}

	public void setVatNumber(final Integer vatNumber) {
		this.vatNumber = vatNumber;
	}

}
