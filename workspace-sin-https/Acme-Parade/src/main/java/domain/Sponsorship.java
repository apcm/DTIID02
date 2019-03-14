package domain;



import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsorship extends DomainEntity{
	
	private boolean isBanner;
	private String  targetURL;
	private CreditCard creditCard;
	private Procession parade; //Esto deberia ser del tipo Parade.
	
	@NotNull
	public Boolean getIsBanner(){
		return this.isBanner;
	}
	
	public void setIsBanner(Boolean isBanner){
		this.isBanner = isBanner;
	}
	@NotBlank
	public String getTargetURL(){
		return this.targetURL;
	}
	
	public void setTargetURL(String targetURL){
		this.targetURL = targetURL;
	}
	
	@NotNull
	@ManyToOne
	public CreditCard getCreditCard(){
		return this.creditCard;
	}

	public void setCreditCard(CreditCard creditCard){
		this.creditCard = creditCard;
	}
	
	@NotNull
	public Procession getParade(){
		return this.parade;
	}
	
	public void setIsParade(Procession parade){
		this.parade = parade;
	}
	
	
	

}
