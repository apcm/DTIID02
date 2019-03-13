
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

import repositories.ProcessionRepository;
import repositories.RequestRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Brotherhood;
import domain.Enrolement;
import domain.Finder;
import domain.Member;
import domain.Procession;
import domain.Request;

@Service
@Transactional
public class ProcessionService {

	@Autowired
	public ActorService			actorService;

	@Autowired
	public ProcessionRepository	processionRepository;

	@Autowired
	public RequestRepository	requestRepository;

	@Autowired
	public BrotherhoodService	brotherhoodService;

	@Autowired
	public MemberService		memberService;


	public ProcessionService() {
		super();
	}

	public Procession create() {
		final Procession res = new Procession();

		res.setDepartureDate(new Date());
		res.setDescription("");
		res.setFinalMode(false);
		res.setTitle("");
		res.setTicker(TickerGenerator.generateTicker());

		return res;
	}

	public Procession save(final Procession proc) {

		Brotherhood res;

		res = this.brotherhoodService.findByPrincipal();
		proc.setBrotherhood(res);

		if (proc.getId() != 0) {
			final Procession procOld = this.processionRepository.findOne(proc.getId());
			//Assert.isTrue(!procOld.getFinalMode());

			final Collection<Request> reqs = this.requestRepository.findByProcessionId(proc.getId());
			for (final Request r : reqs) {
				r.setProcession(proc);
				final Request reqSave = this.requestRepository.save(r);
				final Member mem = this.memberService.findByRequestId(r.getId());
				final Collection<Request> reqsMember = mem.getRequests();
				reqsMember.remove(r);
				reqsMember.add(reqSave);
				this.memberService.save(mem);
			}
		}
		return this.processionRepository.save(proc);
	}
	public void delete(final Procession proc) {
		Assert.isTrue(!proc.getFinalMode());

		final Collection<Request> reqs = this.requestRepository.findByProcessionId(proc.getId());

		for (final Request r : reqs)
			this.requestRepository.delete(r.getId());

		this.processionRepository.delete(proc);
	}

	public Collection<Procession> findAll() {
		return this.processionRepository.findAll();
	}

	public Procession findOne(final int processionId) {
		return this.processionRepository.findOne(processionId);
	}

	public Collection<Procession> findByBrotherhoodId(final int brotherhoodId) {
		return this.processionRepository.findByBrotherhoodId(brotherhoodId);
	}

	public Collection<Procession> findAllFinalMode() {
		return this.processionRepository.findAllFinalMode();
	}
	public List<Procession> findByMemberId(final Member member) {
		final List<Procession> lp = new ArrayList<>();
		for (final Enrolement e : member.getEnrolements())
			lp.addAll(this.processionRepository.findByEnrolementIdApproved(e.getId()));

		return lp;

	}

	public Collection<Procession> findAllFinalModeRequests() {
		final Date today = Calendar.getInstance().getTime();
		return this.processionRepository.findAllFinalModeRequests(today);
	}

	public Procession findByRequestId(final Integer requestId) {
		return this.processionRepository.findByRequestId(requestId);
	}

	public Collection<Procession> findByBrotherhood(final Brotherhood b) {
		return this.processionRepository.findByBrotherhoodId(b.getId());

	}

	public Procession findOne(final Procession p1) {
		return this.processionRepository.findOne(p1.getId());
	}

	private boolean checkMember() {
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.MEMBER);
		return user.getAuthorities().contains(a);
	}

	public Collection<Procession> findProcessionsByKeyworkd(final String keyword) {
		Assert.isTrue(this.checkMember());
		return this.processionRepository.findProcessionsByKeyword("%" + keyword + "%");
	}

	public Collection<Procession> findProcessionsByArea(final int id) {
		Assert.isTrue(this.checkMember());
		return this.processionRepository.findProcessionsByAreaId(id);
	}

	public Collection<Procession> findProcessionsByMinimumDate(final Date minDate) {
		Assert.isTrue(this.checkMember());
		return this.processionRepository.findProcessionsByMinimumDate(minDate);
	}

	public Collection<Procession> findProcessionsByMaximumDate(final Date maxDate) {
		Assert.isTrue(this.checkMember());
		return this.processionRepository.findProcessionsByMaximumDate(maxDate);
	}

	public Collection<Procession> findProcessionsByDateRange(final Date minDate, final Date maxDate) {
		Assert.isTrue(this.checkMember());
		return this.processionRepository.findProcessionsByDateRange(minDate, maxDate);
	}

	public Set<Procession> finderResults(final Finder finder) {
		Assert.isTrue(this.checkMember());
		final Set<Procession> results = new HashSet<>();

		if (finder.getKeyword() != null && finder.getKeyword() != "")
			results.addAll(this.findProcessionsByKeyworkd(finder.getKeyword()));
		else
			results.addAll(this.findAll());

		if (finder.getArea() == null)
			results.addAll(this.findAll());
		else
			results.addAll(this.findProcessionsByArea(finder.getArea().getId()));
		if (finder.getStartDate() != null && finder.getEndDate() == null)
			results.addAll(this.findProcessionsByMinimumDate(finder.getStartDate()));
		else if (finder.getEndDate() != null && finder.getStartDate() == null)
			results.addAll(this.findProcessionsByMaximumDate(finder.getEndDate()));
		else if (finder.getEndDate() != null && finder.getStartDate() != null)
			results.addAll(this.findProcessionsByDateRange(finder.getStartDate(), finder.getEndDate()));
		else
			results.addAll(this.findAll());
		return results;
	}


	@Autowired
	private Validator	validator;


	public Procession reconstruct(final Procession pro, final BindingResult binding) {
		Procession res;

		//Check authority
		final Authority a = new Authority();
		final Brotherhood bro = this.brotherhoodService.findByPrincipal();
		final UserAccount user = bro.getUserAccount();
		a.setAuthority(Authority.BROTHERHOOD);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1);

		if (pro.getId() == 0)
			res = pro;
		else {
			res = this.processionRepository.findOne(pro.getId());
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
