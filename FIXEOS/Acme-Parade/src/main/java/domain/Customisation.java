
package domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Customisation extends DomainEntity {

	//ATRIBUTOS DEL SISTEMA.
	public List<String>			negativeWords;
	public List<String>			positiveWords;
	public Integer				finderDuration;
	public Integer				resultsNumber;
	public String				systemName;
	public String				bannerURL;
	public Collection<String>	welcomeMessage;
	public List<String>			spamWords;
	public String				phoneNumberCountryCode;
	public List<String>			messagePriorities;
	public List<String>			creditCardMakes;
	public Double				fare;
	public Integer				vatPercentage;


	public enum negativeWords {   //En principio no hace falta
		NOT, BAD, HORRIBLE, AVERAGE, DISASTER
	};

	public enum positiveWords {
		GOOD, FANTASTIC, EXCELLENT, GREAT, AMAZING, TERRIFIC, BEAUTIFUL
	}


	@Column
	@ElementCollection(targetClass = String.class)
	public List<String> getNegativeWords() {
		return this.negativeWords;
	}

	public void setNegativeWords(final List<String> negativeWords) {
		this.negativeWords = negativeWords;
	}
	@Column
	@ElementCollection(targetClass = String.class)
	public List<String> getPositiveWords() {
		return this.positiveWords;
	}

	public void setPositiveWords(final List<String> positiveWords) {
		this.positiveWords = positiveWords;
	}

	@Min(value = 1L)
	@Max(value = 24)
	@NotBlank
	public Integer getFinderDuration() {
		return this.finderDuration;
	}

	public void setFinderDuration(final Integer finderDuration) {
		this.finderDuration = finderDuration;
	}
	@NotBlank
	@Min(value = 1L)
	@Max(value = 100)
	public Integer getResultsNumber() {
		return this.resultsNumber;
	}

	public void setResultsNumber(final Integer resultsNumber) {
		this.resultsNumber = resultsNumber;
	}
	@NotBlank
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(final String systemName) {
		this.systemName = systemName;
	}

	@NotBlank
	public String getBannerURL() {
		return this.bannerURL;
	}

	public void setBannerURL(final String bannerURL) {
		this.bannerURL = bannerURL;
	}

	@ElementCollection(targetClass = String.class)
	public Collection<String> getWelcomeMessage() {
		return this.welcomeMessage;
	}

	public void setWelcomeMessage(final Collection<String> welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	@Column
	@ElementCollection(targetClass = String.class)
	public List<String> getSpamWords() {
		return this.spamWords;
	}

	public void setSpamWords(final List<String> spamWords) {
		this.spamWords = spamWords;
	}

	@NotBlank
	public String getPhoneNumberCountryCode() {
		return this.phoneNumberCountryCode;
	}

	public void setPhoneNumberCountryCode(final String phoneNumberCountryCode) {
		this.phoneNumberCountryCode = phoneNumberCountryCode;
	}

	@Column
	@ElementCollection(targetClass = String.class)
	public List<String> getMessagePriorities() {
		return this.messagePriorities;
	}

	public void setMessagePriorities(final List<String> messagePriorities) {
		this.messagePriorities = messagePriorities;
	}

	@ElementCollection(targetClass = String.class)
	public List<String> getCreditCardMakes() {
		return this.creditCardMakes;
	}

	public void setCreditCardMakes(final List<String> creditCardMakes) {
		this.creditCardMakes = creditCardMakes;
	}

	@NotBlank
	@Min(value = 0)
	public Double getFare() {
		return this.fare;
	}

	//Esto puede dar fallos porque es un Long y no un double.
	public void setFare(final Double fare) {
		this.fare = fare;
	}

	@Min(value = 0L)
	@Max(value = 100)
	@NotBlank
	public Integer getVatPercentage() {
		return this.vatPercentage;
	}

	public void setVatPercentage(final Integer vatPercentage) {
		this.vatPercentage = vatPercentage;
	}

}
