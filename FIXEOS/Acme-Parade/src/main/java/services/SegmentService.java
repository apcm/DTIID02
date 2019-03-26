package services;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;



import repositories.SegmentRepository;

import domain.Parade;
import domain.Segment;


@Service
@Transactional
public class SegmentService {
	
	@Autowired
	private SegmentRepository segmentRepository;
	
	@Autowired
	private ParadeService paradeService;
	
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


}
