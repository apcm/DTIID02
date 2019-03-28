
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
import domain.Chapter;
import domain.Parade;
import domain.Segment;

@Service
@Transactional
public class SegmentService {

	@Autowired
	private SegmentRepository	segmentRepository;

	@Autowired
	private ParadeService		paradeService;

	@Autowired
	private BrotherhoodService	brotherhoodService;

	@Autowired
	private ChapterService		chapterService;


	public Segment create(final int paradeId) {
		final Segment res = new Segment();
		final Parade p = this.paradeService.findOne(paradeId);
		final Segment ant = this.getLastSegment(p);
		if (ant != null) {
			res.setOrigLatitude(ant.getDestLatitude());
			res.setOrigLongitude(ant.getDestLongitude());
			res.setSegmentOrder(ant.getSegmentOrder() + 1);
		} else
			res.setSegmentOrder(1);

		return res;
	}

	public Collection<Segment> findAll() {
		return this.segmentRepository.findAll();
	}

	public Segment findOne(final int segmentId) {
		return this.segmentRepository.findOne(segmentId);
	}

	public Segment save(final Segment segment) {
		Assert.notNull(segment);
		return this.segmentRepository.save(segment);
	}

	public void delete(final Segment segment) {
		this.segmentRepository.delete(segment);
	}

	public List<Segment> getSegmentsByParade(final Parade parade) {
		Brotherhood b;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		b = this.brotherhoodService.findByUserAccount(userAccount);
		if (parade.getBrotherhood() != b)
			return null;
		return parade.getSegments();
	}

	private Segment getLastSegment(final Parade p) {
		int i = 0;
		Segment res = null;
		for (final Segment s : p.getSegments())
			if (s.getSegmentOrder() > i) {
				i = s.getSegmentOrder();
				res = s;
			}
		return res;
	}

	public boolean isCorrectDate(final Segment segment, final Parade parade) {
		if (segment.getStartTime() == null || segment.getArriveTime() == null)
			throw new IllegalArgumentException();

		final Parade p = this.paradeService.findOne(parade.getId());
		boolean res = false;
		Segment ant;
		Integer x = 1;
		if (p.getSegments().contains(segment))
			x = 2;
		if (segment.getSegmentOrder() == 1)
			ant = null;
		else
			ant = p.getSegments().get(p.getSegments().size() - x);

		if (ant == null)
			res = segment.getArriveTime().after(segment.getStartTime());
		else
			res = ant.getArriveTime().before(segment.getStartTime()) && segment.getArriveTime().after(segment.getStartTime());

		return res;
	}

	public List<Segment> getSegmentsByParadeChapter(final Parade parade) {
		final Chapter c;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		c = this.chapterService.findByUserAccount(userAccount);

		if (!(c.getArea().equals(parade.getBrotherhood().getArea())))
			return null;
		return parade.getSegments();
	}
}
