
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Area extends DomainEntity {

	private String					name;
	private Collection<String>		pictures;
	private Collection<Brotherhood>	brotherhoods;


	@OneToMany(mappedBy = "area")
	public Collection<Brotherhood> getBrotherhoods() {
		return this.brotherhoods;
	}

	public void setBrotherhoods(final Collection<Brotherhood> brotherhoods) {
		this.brotherhoods = brotherhoods;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@ElementCollection(targetClass = String.class)
	@NotEmpty
	public Collection<String> getPictures() {
		return this.pictures;
	}

	public void setPictures(final Collection<String> pictures) {
		this.pictures = pictures;
	}

}
