
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsorship extends DomainEntity {

	private String				banner;
	private Boolean				isActive;
	private String				targetURL;
	private Collection<Parade>	parades;
	private CreditCard			creditCard;


	@NotBlank
	@URL
	public String getTargetURL() {
		return this.targetURL;
	}

	public void setTargetURL(final String targetURL) {
		this.targetURL = targetURL;
	}

	@NotBlank
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	public void setIsActive(final Boolean isActive) {
		this.isActive = isActive;
	}

	@NotNull
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(final boolean isActive) {
		this.isActive = isActive;
	}

	@ManyToMany
	public Collection<Parade> getParades() {
		return this.parades;
	}

	public void setParades(final Collection<Parade> parades) {
		this.parades = parades;
	}
	@NotNull
	@ManyToOne(optional = false)
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}
}
