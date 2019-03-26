
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
import domain.Chapter;
import domain.Member;
import domain.Parade;

@Service
@Transactional
public class DashboardService {

	@Autowired
	public DashboardRepository	dashboardRepository;

	@Autowired
	public ParadeService		processionService;


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

	public List<Object[]> requestRatioByProcession() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.requestRatioByParade();
	}

	//12.3.5
	public Collection<Parade> processionsOrganizedIn30Days() {
		Assert.isTrue(this.checkAdmin());
		final List<Parade> processions = new ArrayList<Parade>(this.processionService.findAll());
		final List<Parade> processionsQ = new ArrayList<Parade>(processions);
		for (final Parade p : processions) {
			final long diffInMillies = Math.abs(p.getDepartureDate().getTime() - Calendar.getInstance().getTime().getTime());
			final long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if (diff >= 30)
				processionsQ.remove(p);
		}
		return processionsQ;
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

	//-----------------ACME PARADE-----------------------
	public double maxNumRecordsPerHistory() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.maxNumRecordsPerHistory();
	}

	public double minNumRecordsPerHistory() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.minNumRecordsPerHistory();
	}

	public double avgNumRecordsPerHistory() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.avgNumRecordsPerHistory();
	}

	public double stddevNumRecordsPerHistory() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.stddevNumRecordsPerHistory();
	}

	public Collection<Brotherhood> largestHistoryBrotherhood() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.largestHistoryBrotherhood();
	}

	public Collection<Brotherhood> largerThanAvgHistoryBrotherhood() {
		Assert.isTrue(this.checkAdmin());
		final double avg = this.dashboardRepository.avgNumRecordsPerHistory();
		return this.dashboardRepository.largerThanAvgHistoryBrotherhood(avg);
	}

	//B-Level Dashboard
	public double ratioAreasNoChapter() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.ratioAreasNoChapter();
	}

	public double avgParadesPerChapter() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.avgParadesPerChapter();
	}

	public double minParadesPerChapter() {
		Assert.isTrue(this.checkAdmin());
		double min = 0.0;
		final List<Double> countParades = new ArrayList<>(this.dashboardRepository.countParadesPerChapter());
		for (int i = 0; i < countParades.size() - 1; i++)
			min = Math.min(countParades.get(i), countParades.get(i + 1));
		return min;
	}

	public double maxParadesPerChapter() {
		Assert.isTrue(this.checkAdmin());
		double max = 0.0;
		final List<Double> countParades = new ArrayList<>(this.dashboardRepository.countParadesPerChapter());
		for (int i = 0; i < countParades.size() - 1; i++)
			max = Math.max(countParades.get(i), countParades.get(i + 1));
		return max;
	}

	public double stddevParadesPerChapter() {
		Assert.isTrue(this.checkAdmin());
		final List<Double> countParades = new ArrayList<>(this.dashboardRepository.countParadesPerChapter());
		double sum = 0.0, standardDeviation = 0.0;

		final int length = countParades.size();

		for (final double num : countParades)
			sum += num;

		final double mean = sum / length;

		for (final double num : countParades)
			standardDeviation += Math.pow(num - mean, 2);

		return Math.sqrt(standardDeviation / length);

	}

	public Collection<Chapter> chapterMoreParadesThanAvg() {
		Assert.isTrue(this.checkAdmin());
		final double avg = this.avgParadesPerChapter();
		return this.dashboardRepository.chapterMoreParadesThanAvg(avg);
	}

	public double ratioParadesDraftModeVsFinalMode() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.ratioParadesDraftModevsFinalMode();
	}

	public Collection<Double> ratioParadesFinalModeGroupedByStatus() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.ratioParadesFinalModeGroupedByStatus();
	}

}
