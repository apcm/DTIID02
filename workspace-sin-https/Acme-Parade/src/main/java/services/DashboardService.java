
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DashboardRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Brotherhood;
import domain.Member;
import domain.Parade;

@Service
@Transactional
public class DashboardService {

	@Autowired
	public DashboardRepository	dashboardRepository;

	@Autowired
	public ParadeService		paradeService;


	public DashboardService() {
		super();
	}

	private boolean checkAdmin() {
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.ADMIN);
		return user.getAuthorities().contains(a);
	}

	//12.3.1
	public double minMembers() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.minMembers();
	}
	public double maxMembers() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.maxMembers();
	}
	public double avgMembers() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.avgMembers();
	}
	public double stddevMembers() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.stddevMembers();
	}

	//12.3.2
	public Collection<Brotherhood> largestBrotherhoods() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.largestBrotherhoods();
	}

	//12.3.3
	public Collection<Brotherhood> smallestsBrotherhoods() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.smallestBrotherhoods();
	}

	//12.3.4 

	public List<Object[]> requestRatioByParade() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.requestRatioByParade();
	}

	//12.3.5
	public Collection<Parade> paradesOrganizedIn30Days() {
		Assert.isTrue(this.checkAdmin());
		final List<Parade> parades = new ArrayList<Parade>(this.paradeService.findAll());
		final List<Parade> paradesQ = new ArrayList<Parade>(parades);
		for (final Parade p : parades) {
			final long diffInMillies = Math.abs(p.getDepartureDate().getTime() - Calendar.getInstance().getTime().getTime());
			final long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if (diff >= 30)
				paradesQ.remove(p);
		}
		return paradesQ;
	}

	//12.3.6
	public List<Double> requestRatio() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.requestRatio();
	}
	//12.3.7
	public Collection<Member> membersWith10PercentRequestsApproved() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.membersWith10PercentRequestsApproved();
	}
	//12.3.8
	public List<Object[]> positionHistogram() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.positionHistogram();
	}
	//22.2.1
	public double ratioBrotherhoodsPerArea() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.ratioBrotherhoodsPerArea();
	}

	public Collection<Double> countBrotherhoodsPerArea() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.countBrotherhoodsPerArea();
	}

	public double minBrotherhoodsPerArea() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.minBrotherhoodsPerArea();
	}

	public double maxBrotherhoodsPerArea() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.maxBrotherhoodsPerArea();
	}

	public double avgBrotherhoodsPerArea() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.avgBrotherhoodsPerArea();
	}

	public double stddevBrotherhoodsPerArea() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.stddevBrotherhoodsPerArea();
	}

	//22.2.2
	public double avgFinderResults() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.avgFinderResults();
	}

	public double minFinderResults() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.minFinderResults();
	}

	public double maxFinderResults() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.maxFinderResults();
	}

	public double stddevFinderResults() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.stddevFinderResults();
	}

	//22.2.3
	public double ratioEmptyFinders() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.ratioEmptyFinders();
	}
}
