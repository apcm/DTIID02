package services;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;



import repositories.SegmentRepository;
import security.LoginService;
import security.UserAccount;

import domain.Brotherhood;
import domain.Parade;
import domain.Segment;


@Service
@Transactional
public class SegmentService {
	
	@Autowired
	private SegmentRepository segmentRepository;
	
	@Autowired
	private ParadeService paradeService;
	
	@Autowired
	private BrotherhoodService brotherhoodService;
	
	public Segment create(int paradeId){
		Segment res = new Segment();
		Parade p = this.paradeService.findOne(paradeId);
		Segment ant = this.getLastSegment(p);
		if(ant != null){
			res.setOrigLatitude(ant.getDestLatitude());
			res.setOrigLongitude(ant.getDestLongitude());
			res.setSegmentOrder(ant.getSegmentOrder()+1);
		}else{
			res.setSegmentOrder(1);
		}
		
		return res;
	}
	
	
	public Collection<Segment> findAll(){
		return segmentRepository.findAll();
	}
	
	public Segment findOne(int segmentId){
		return segmentRepository.findOne(segmentId);
	}
	
	public Segment save(Segment segment){
		Assert.notNull(segment);
		return this.segmentRepository.save(segment);
	}
	
	public void delete(Segment segment){
		segmentRepository.delete(segment);
	}

	public List<Segment> getSegmentsByParade(Parade parade) {
		Brotherhood b;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		b = this.brotherhoodService.findByUserAccount(userAccount);
		if(parade.getBrotherhood()!=b){
			return null;
		}
		return parade.getSegments();
	}
	
	private Segment getLastSegment(Parade p) {
		int i = 0;
		Segment res = null;
		for(Segment s : p.getSegments()){
			if(s.getSegmentOrder()>i){
				i = s.getSegmentOrder();
				res = s;
			}
		}
		return res;
	}


	public boolean isCorrectDate(Segment segment, Parade parade) {
		if(segment.getStartTime()==null || segment.getArriveTime()==null){
			throw new IllegalArgumentException();
		}
		
		Parade p = this.paradeService.findOne(parade.getId());
		boolean res = false;
		Segment ant;
		Integer x = 1;
		if(p.getSegments().contains(segment)){ x = 2;}
			if(segment.getSegmentOrder()==1){
				ant = null;
			}
			
			else {
				ant = p.getSegments().get(p.getSegments().size() -x);
			}
			
			if(ant == null){
				res = segment.getArriveTime().after(segment.getStartTime());
			}else{
				res = ant.getArriveTime().before(segment.getStartTime()) && segment.getArriveTime().after(segment.getStartTime());
			}
			
			
		
		return res;
	}


}
