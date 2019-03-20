
package forms;

import java.util.Collection;
import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import security.UserAccount;
import domain.Area;
import domain.Enrolement;

public class BrotherhoodForm {

	private String					name;
	private String					email;
	private String					phoneNumber;
	private String					address;
	private String					middleName;
	private String					surname;
	private String					photo;
	private boolean					conditionsAccepted;
	private UserAccount				userAccount;

	private String					title;
	private Date					stablishmentDate;
	private Collection<String>		urls;
	private Collection<Enrolement>	enrolements;
	private Area					area;


	@NotBlank
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@NotBlank
	public String getAddress() {
		return this.address;
	}
	public void setAddress(final String address) {
		this.address = address;
	}

	public String getMiddleName() {
		return this.middleName;
	}
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	@NotBlank
	public String getSurname() {
		return this.surname;
	}
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}
	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@NotNull
	public boolean isConditionsAccepted() {
		return this.conditionsAccepted;
	}

	public void setConditionsAccepted(final boolean conditionsAccepted) {
		this.conditionsAccepted = conditionsAccepted;
	}

	@Valid
	@NotNull
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

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

}
