
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Brotherhood extends Actor {

	private String						title;
	private Date						stablishmentDate;
	private Collection<String>			urls;
	private Collection<Enrolement>		enrolements;
	private Area						area;
	private Collection<LegalRecord>		legalRecords;
	private Collection<InceptionRecord>	inceptionRecords;
	private Collection<LinkRecord>		linkRecords;
	private Collection<PeriodRecord>	periodRecords;


	@ManyToOne(optional = true)
	public Area getArea() {
		return this.area;
	}

	public void setArea(final Area area) {
		this.area = area;
	}

	@OneToMany(mappedBy = "brotherhood")
	public Collection<Enrolement> getEnrolements() {
		return this.enrolements;
	}

	public void setEnrolements(final Collection<Enrolement> enrolements) {
		this.enrolements = enrolements;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getStablishmentDate() {
		return this.stablishmentDate;
	}

	public void setStablishmentDate(final Date stablishmentDate) {
		this.stablishmentDate = stablishmentDate;
	}

	@ElementCollection
	public Collection<String> getUrls() {
		return this.urls;
	}

	public void setUrls(final Collection<String> urls) {
		this.urls = urls;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<LegalRecord> getLegalRecords() {
		return this.legalRecords;
	}

	public void setLegalRecords(final Collection<LegalRecord> legalRecords) {
		this.legalRecords = legalRecords;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<InceptionRecord> getInceptionRecords() {
		return this.inceptionRecords;
	}

	public void setInceptionRecords(final Collection<InceptionRecord> inceptionRecords) {
		this.inceptionRecords = inceptionRecords;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<LinkRecord> getLinkRecords() {
		return this.linkRecords;
	}

	public void setLinkRecords(final Collection<LinkRecord> linkRecords) {
		this.linkRecords = linkRecords;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<PeriodRecord> getPeriodRecords() {
		return this.periodRecords;
	}

	public void setPeriodRecords(final Collection<PeriodRecord> periodRecords) {
		this.periodRecords = periodRecords;
	}

}
