
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

import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Customisation;
import domain.Finder;
import domain.Member;
import domain.Parade;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository		repository;

	@Autowired
	private ParadeService			paradeService;

	@Autowired
	private CustomisationService	customisationService;

	@Autowired
	private MemberService			memberService;


	public FinderService() {
		super();
	}

	private boolean checkMember() {
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.MEMBER);
		return user.getAuthorities().contains(a);
	}

	public Finder create() {
		Finder res;
		res = new Finder();
		res.setParades(new HashSet<Parade>());
		return res;
	}

	public Collection<Finder> findAll() {
		Collection<Finder> res;
		Assert.notNull(this.repository);
		res = this.repository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Finder findOne(final int id) {
		Finder res;
		res = this.repository.findOne(id);
		return res;
	}

	public Finder save(final Finder finder) {
		Assert.notNull(finder);
		final Finder res = finder;

		if (finder.getId() != 0) {
			final Member principal = this.memberService.findByPrincipal();
			Assert.isTrue(principal.getFinder().getId() == finder.getId());
			if (finder.getStartDate() != null && finder.getEndDate() != null)
				Assert.isTrue(finder.getStartDate().before(finder.getEndDate()));
			final Set<Parade> results = this.paradeService.finderResults(finder);
			for (final Parade p : results)
				if (p.getFinalMode() == false)
					results.remove(p);
			res.setParades(results);
			res.setMoment(Calendar.getInstance().getTime());
		}
		return this.repository.save(res);
	}
	public Finder getFinderMember(final int id) {
		Assert.isTrue(this.checkMember());
		Assert.isTrue(id != 0);
		return this.repository.getFinderMember(id);
	}
	public Finder clear(final Finder finder) {
		Assert.isTrue(this.checkMember());
		Assert.notNull(finder);
		final Finder res = finder;
		res.setArea(null);
		res.setEndDate(null);
		res.setKeyword("");
		res.setStartDate(null);
		res.setMoment(Calendar.getInstance().getTime());
		res.setParades(new HashSet<Parade>());
		return this.repository.save(res);
	}
	//Check cached hours
	public boolean checkCache(final Finder finder) {
		boolean res = false;
		final Date today = Calendar.getInstance().getTime();
		final Date moment = finder.getMoment();
		final List<Customisation> cusList = new ArrayList<>(this.customisationService.findAll());
		final Customisation cus = cusList.get(0);
		final long milisecondsDiff = Math.abs(today.getTime() - moment.getTime());
		final long hoursDiff = milisecondsDiff / 3600000;

		if (hoursDiff > cus.getFinderDuration())
			res = true;
		return res;
	}


	//Pruned object
	@Autowired
	private Validator	validator;


	public Finder reconstruct(final Finder finder, final BindingResult binding) {
		Finder res;

		Assert.isTrue(this.checkMember());
		if (finder.getId() == 0)
			res = finder;
		else {
			res = this.findOne(finder.getId());
			res.setArea(finder.getArea());
			res.setKeyword(finder.getKeyword());
			res.setStartDate(finder.getStartDate());
			res.setEndDate(finder.getEndDate());

			this.validator.validate(res, binding);
		}
		return res;
	}
}
