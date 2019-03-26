package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Customisation;

import repositories.CustomisationRepository;


@Transactional
@Service
public class CustomisationService {
	@Autowired
	private CustomisationRepository customisationRepository;
	
	public Customisation create(){
		return new Customisation();
	}
	
	public Collection<Customisation> findAll(){
		return customisationRepository.findAll();
	}
	
	public Customisation findOne(int customisationId){
		return customisationRepository.findOne(customisationId);
	}
	
	public Customisation save(Customisation customisation){
		return customisationRepository.save(customisation);
	}
	
	public void delete(Customisation customisation){
		customisationRepository.delete(customisation);
	}
	
	public Customisation getCustomisation(){
		List<Customisation> x = new ArrayList<Customisation>();
		x.addAll(this.findAll());
		Customisation res;
		
		res = x.get(0);
		
		return res;
		
	}
	

}
