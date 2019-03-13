
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PositionRepository;
import domain.Enrolement;
import domain.Position;

@Service
@Transactional
public class PositionService {

	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired
	private EnrolementService enrolementService;
	
	public Position create(){
		return new Position();
	}
	
	public Collection<Position> findAll(){
		return positionRepository.findAll();
	}
	
	public Position findOne(int positionId){
		return positionRepository.findOne(positionId);
	}
	
	public Position save(Position position){
		return positionRepository.save(position);
	}
	
	public void delete(Position position){
		Collection<Enrolement> enrolements;
		enrolements = enrolementService.findAll();
		Boolean isUsed = checkIsUsed(enrolements, position);
		
		if(isUsed==false){
			positionRepository.delete(position);
		}
	
	}

	private Boolean checkIsUsed(Collection<Enrolement> enrolements, Position position) {
		Boolean res = false;
		
		for(Enrolement e: enrolements){
			if(e.getPosition().equals(position)){
				res = true;
			}
		}
		return res;
	}

	
	
	
}
