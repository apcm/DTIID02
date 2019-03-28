
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ParadeRepository;
import repositories.RequestRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Actor;
import domain.Area;
import domain.Brotherhood;
import domain.Chapter;
import domain.Enrolement;
import domain.Finder;
import domain.Member;
import domain.Parade;
import domain.Request;
import domain.Segment;

@Service
@Transactional
public class ParadeService {

	@Autowired
	public ActorService			actorService;

	@Autowired
	public ParadeRepository		paradeRepository;

	@Autowired
	public RequestRepository	requestRepository;

	@Autowired
	public BrotherhoodService	brotherhoodService;

	@Autowired
	public MemberService		memberService;

	@Autowired
	public ChapterService		chapterService;

	@Autowired
	public AreaService			areaService;

	@Autowired
	private SegmentService		segmentService;


	public ParadeService() {
		super();
	}

	public Parade create() {
		final Parade res = new Parade();

		res.setDepartureDate(new Date());
		res.setDescription("");
		res.setFinalMode(false);
		res.setTitle("");
		res.setTicker(TickerGenerator.generateTicker());

		return res;
	}
	public Parade save(final Parade proc) {

		Brotherhood res;

		res = this.brotherhoodService.findByPrincipal();
		proc.setBrotherhood(res);

		if (proc.getId() == 0)
			proc.setStatus("SUBMITTED");

		if (proc.getId() != 0) {
			final Parade procOld = this.paradeRepository.findOne(proc.getId());
			//Assert.isTrue(!procOld.getFinalMode());

			final Collection<Request> reqs = this.requestRepository.findByParadeId(proc.getId());
			for (final Request r : reqs) {
				r.setParade(proc);
				final Request reqSave = this.requestRepository.save(r);
				final Member mem = this.memberService.findByRequestId(r.getId());
				final Collection<Request> reqsMember = mem.getRequests();
				reqsMember.remove(r);
				reqsMember.add(reqSave);
				this.memberService.save(mem);
			}
		}
		return this.paradeRepository.save(proc);
	}
	public void delete(final Parade proc) {
		Assert.isTrue(!proc.getFinalMode());

		final Collection<Request> reqs = this.requestRepository.findByParadeId(proc.getId());

		for (final Request r : reqs)
			this.requestRepository.delete(r.getId());

		this.paradeRepository.delete(proc);
	}

	public Collection<Parade> findAll() {
		return this.paradeRepository.findAll();
	}

	public Parade findOne(final int paradeId) {
		return this.paradeRepository.findOne(paradeId);
	}

	public Collection<Parade> findByBrotherhoodId(final int brotherhoodId) {
		return this.paradeRepository.findByBrotherhoodId(brotherhoodId);
	}

	public Collection<Parade> findAllFinalMode() {
		return this.paradeRepository.findAllFinalMode();
	}
	public List<Parade> findByMemberId(final Member member) {
		final List<Parade> lp = new ArrayList<>();
		for (final Enrolement e : member.getEnrolements())
			lp.addAll(this.paradeRepository.findByEnrolementIdApproved(e.getId()));

		return lp;

	}

	public Collection<Parade> findAllFinalModeRequests() {
		final Date today = Calendar.getInstance().getTime();
		return this.paradeRepository.findAllFinalModeRequests(today);
	}

	public Parade findByRequestId(final Integer requestId) {
		return this.paradeRepository.findByRequestId(requestId);
	}

	public Collection<Parade> findByBrotherhood(final Brotherhood b) {
		return this.paradeRepository.findByBrotherhoodId(b.getId());

	}

	public Parade findOne(final Parade p1) {
		return this.paradeRepository.findOne(p1.getId());
	}

	private boolean checkMember() {
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.MEMBER);
		return user.getAuthorities().contains(a);
	}

	public Collection<Parade> findParadesByChapterId() {
		final Chapter cha = this.chapterService.findByPrincipal();
		final Area a = cha.getArea();
		return this.findParadesByArea2(a.getId());

	}
	public Collection<Parade> findParadesByArea2(final int id) {
		return this.paradeRepository.findParadesByAreaId(id);
	}

	public Collection<Parade> findParadesByKeyworkd(final String keyword) {
		Assert.isTrue(this.checkMember());
		return this.paradeRepository.findParadesByKeyword("%" + keyword + "%");
	}

	public Collection<Parade> findParadesByArea(final int id) {
		Assert.isTrue(this.checkMember());
		return this.paradeRepository.findParadesByAreaId(id);
	}

	public Collection<Parade> findParadesByMinimumDate(final Date minDate) {
		Assert.isTrue(this.checkMember());
		return this.paradeRepository.findParadesByMinimumDate(minDate);
	}

	public Collection<Parade> findParadesByMaximumDate(final Date maxDate) {
		Assert.isTrue(this.checkMember());
		return this.paradeRepository.findParadesByMaximumDate(maxDate);
	}

	public Collection<Parade> findParadesByDateRange(final Date minDate, final Date maxDate) {
		Assert.isTrue(this.checkMember());
		return this.paradeRepository.findParadesByDateRange(minDate, maxDate);
	}

	public Set<Parade> finderResults(final Finder finder) {
		Assert.isTrue(this.checkMember());
		final Set<Parade> results = new HashSet<>();

		if (finder.getKeyword() != null && finder.getKeyword() != "")
			results.addAll(this.findParadesByKeyworkd(finder.getKeyword()));
		else
			results.addAll(this.findAll());

		if (finder.getArea() == null)
			results.addAll(this.findAll());
		else
			results.addAll(this.findParadesByArea(finder.getArea().getId()));
		if (finder.getStartDate() != null && finder.getEndDate() == null)
			results.addAll(this.findParadesByMinimumDate(finder.getStartDate()));
		else if (finder.getEndDate() != null && finder.getStartDate() == null)
			results.addAll(this.findParadesByMaximumDate(finder.getEndDate()));
		else if (finder.getEndDate() != null && finder.getStartDate() != null)
			results.addAll(this.findParadesByDateRange(finder.getStartDate(), finder.getEndDate()));
		else
			results.addAll(this.findAll());
		return results;
	}


	@Autowired
	private Validator	validator;


	public Parade reconstruct(final Parade pro, final BindingResult binding) {
		Parade res;

		//Check authority
		final Authority a = new Authority();
		final Authority b = new Authority();
		final Actor act = this.actorService.findByPrincipal();
		final UserAccount user = act.getUserAccount();
		a.setAuthority(Authority.BROTHERHOOD);
		b.setAuthority(Authority.CHAPTER);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1 || user.getAuthorities().contains(b));

		if (pro.getId() == 0)
			res = pro;
		else
			res = this.paradeRepository.findOne(pro.getId());
		res.setFloats(pro.getFloats());
		res.setTitle(pro.getTitle());
		res.setFinalMode(pro.getFinalMode());
		res.setDescription(pro.getDescription());
		res.setDepartureDate(pro.getDepartureDate());
		//res.setBrotherhood(pro.getBrotherhood());
		if (pro.getStatus() != null)
			res.setStatus(pro.getStatus());

		this.validator.validate(res, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public Parade reconstructChapter(final Parade pro, final BindingResult binding) {
		Parade res;

		//Check authority
		final Authority a = new Authority();
		final Authority b = new Authority();
		final Actor act = this.actorService.findByPrincipal();
		final UserAccount user = act.getUserAccount();
		a.setAuthority(Authority.BROTHERHOOD);
		b.setAuthority(Authority.CHAPTER);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1 || user.getAuthorities().contains(b));

		if (pro.getId() == 0)
			res = pro;
		else
			res = this.paradeRepository.findOne(pro.getId());
		//		res.setFloats(pro.getFloats());
		//		res.setTitle(pro.getTitle());
		//		res.setFinalMode(pro.getFinalMode());
		//		res.setDescription(pro.getDescription());
		//		res.setDepartureDate(pro.getDepartureDate());	
		//		res.setBrotherhood(pro.getBrotherhood());
		res.setStatus(pro.getStatus());
		res.setRejectReason(pro.getRejectReason());

		this.validator.validate(res, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public Parade saveStatus(final Parade parade) {

		return this.paradeRepository.save(parade);
	}

	public Parade copy(final Parade p) {
		final Parade copy = new Parade();

		Assert.notNull(p);
		copy.setFinalMode(false);
		copy.setStatus("");
		copy.setRejectReason("");
		copy.setDepartureDate(p.getDepartureDate());
		copy.setDescription(p.getDescription());
		copy.setFloats(p.getFloats());
		copy.setTicker(TickerGenerator.generateTicker());
		copy.setTitle(p.getTitle());
		copy.setBrotherhood(p.getBrotherhood());

		return this.save(copy);
	}

	public Segment saveSegmentInParade(final Segment segment, final Parade parade) {
		Segment res = null;
		final Parade p = this.findOne(parade.getId());
		final List<Segment> segments = new ArrayList<Segment>();
		final Segment ant = this.getLastSegment(p, segment);
		if (ant != null) {
			segment.setOrigLatitude(ant.getDestLatitude());
			segment.setOrigLongitude(ant.getDestLongitude());
		}

		res = this.segmentService.save(segment);
		segments.addAll(p.getSegments());
		if (segments.contains(res))
			segments.remove(res);
		segments.add(res);
		p.setSegments(segments);

		return res;
	}

	public void deleteSegmentInParade(final Segment segment, final Parade parade) {
		final Parade p = this.findOne(parade.getId());
		final List<Segment> segments = new ArrayList<Segment>();
		segments.addAll(p.getSegments());
		segments.remove(segment);
		p.setSegments(segments);
		this.segmentService.delete(segment);

	}

	private Segment getLastSegment(final Parade p, final Segment segment) {
		int i = 0;
		Segment res = null;
		for (final Segment s : p.getSegments())
			if (s.getSegmentOrder() > i && (!s.equals(segment))) {
				i = s.getSegmentOrder();
				res = s;
			}
		return res;
	}

	public Collection<Parade> getParadesFinalMode() {
		Collection<Parade> res = new ArrayList<Parade>();
		final Collection<Parade> parades = new ArrayList<Parade>();

		res = this.paradesFinal(parades);

		return res;
	}

	private Collection<Parade> paradesFinal(final Collection<Parade> parades) {
		final Collection<Parade> res = new ArrayList<Parade>();
		for (final Parade p : parades)
			if (p.getFinalMode())
				res.add(p);
		return res;
	}

}
