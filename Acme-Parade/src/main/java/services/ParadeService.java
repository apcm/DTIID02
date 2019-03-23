
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import domain.Brotherhood;
import domain.Enrolement;
import domain.Finder;
import domain.Member;
import domain.Parade;
import domain.Request;

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
	
	public Parade copy(Parade p){
		Parade copy = new Parade();
		
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
		final Brotherhood bro = this.brotherhoodService.findByPrincipal();
		final UserAccount user = bro.getUserAccount();
		a.setAuthority(Authority.BROTHERHOOD);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1);

		if (pro.getId() == 0)
			res = pro;
		else {
			res = this.paradeRepository.findOne(pro.getId());
			res.setFloats(pro.getFloats());
			res.setTitle(pro.getTitle());
			res.setFinalMode(pro.getFinalMode());
			res.setDescription(pro.getDescription());
			res.setDepartureDate(pro.getDepartureDate());
			this.validator.validate(res, binding);
		}
		return res;
	}
	
}
