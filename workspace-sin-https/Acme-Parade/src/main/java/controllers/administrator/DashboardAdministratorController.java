
package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.DashboardService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Chapter;
import domain.Member;
import domain.Parade;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	//Services
	@Autowired
	DashboardService	dashboardService;


	//Constructor
	DashboardAdministratorController() {
		super();
	}

	//Dashboard
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		final ModelAndView res;
		res = new ModelAndView("dashboard/dashboard");

		final double minMembers = this.dashboardService.minMembers();
		final double maxMembers = this.dashboardService.maxMembers();
		final double avgMembers = this.dashboardService.avgMembers();
		final double stddevMembers = this.dashboardService.stddevMembers();

		final Collection<Brotherhood> largestBrotherhoods = this.dashboardService.largestBrotherhoods();

		final Collection<Brotherhood> smallestBrotherhoods = this.dashboardService.smallestsBrotherhoods();

		final List<Object[]> requestRatioByProcession = this.dashboardService.requestRatioByProcession();
		final List<Double> ratioList = new ArrayList<>();
		final List<String> processionList = new ArrayList<>();
		final List<String> statusList = new ArrayList<>();
		for (final Object[] o : requestRatioByProcession) {
			ratioList.add((double) o[0]);
			processionList.add((String) o[1]);
			statusList.add((String) o[2]);
		}

		final Collection<Parade> processionsOrganizedIn30Days = this.dashboardService.processionsOrganizedIn30Days();

		final List<Double> requestRatio = this.dashboardService.requestRatio();

		final Collection<Member> members10Percent = this.dashboardService.membersWith10PercentRequestsApproved();

		final List<Object[]> positionHistogram = this.dashboardService.positionHistogram();
		final List<String> positionList = new ArrayList<>();
		final List<Long> positionCountList = new ArrayList<>();
		for (final Object[] o : positionHistogram) {
			positionList.add((String) o[0]);
			positionCountList.add((Long) o[1]);
		}

		final double ratioBrotherhoodsPerArea = this.dashboardService.ratioBrotherhoodsPerArea();
		final Collection<Double> countBrotherhoodsPerArea = this.dashboardService.countBrotherhoodsPerArea();
		final double minBrotherhoodsPerArea = this.dashboardService.minBrotherhoodsPerArea();
		final double maxBrotherhoodsPerArea = this.dashboardService.maxBrotherhoodsPerArea();
		final double avgBrotherhoodsPerArea = this.dashboardService.avgBrotherhoodsPerArea();
		final double stddevBrotherhoodsPerArea = this.dashboardService.stddevBrotherhoodsPerArea();

		final double avgFinderResults = this.dashboardService.avgFinderResults();
		final double minFinderResults = this.dashboardService.minFinderResults();
		final double maxFinderResults = this.dashboardService.maxFinderResults();
		final double stddevFinderResults = this.dashboardService.stddevFinderResults();

		final double ratioEmptyFinders = this.dashboardService.ratioEmptyFinders();

		res.addObject("minM", minMembers);
		res.addObject("maxM", maxMembers);
		res.addObject("avgM", avgMembers);
		res.addObject("stddevM", stddevMembers);
		res.addObject("largestB", largestBrotherhoods);
		res.addObject("smallestB", smallestBrotherhoods);
		//res.addObject("rRatioP", requestRatioByProcession);
		res.addObject("ratioList", ratioList);
		res.addObject("processionList", processionList);
		res.addObject("statusList", statusList);
		res.addObject("p30", processionsOrganizedIn30Days);
		res.addObject("rRatio", requestRatio);
		res.addObject("m10", members10Percent);
		//res.addObject("histogram", positionHistogram);
		res.addObject("posHistList", positionList);
		res.addObject("posCountList", positionCountList);
		res.addObject("ratioBA", ratioBrotherhoodsPerArea);
		res.addObject("countBA", countBrotherhoodsPerArea);
		res.addObject("minBA", minBrotherhoodsPerArea);
		res.addObject("maxBA", maxBrotherhoodsPerArea);
		res.addObject("avgBA", avgBrotherhoodsPerArea);
		res.addObject("stddevBA", stddevBrotherhoodsPerArea);
		res.addObject("avgFinder", avgFinderResults);
		res.addObject("minFinder", minFinderResults);
		res.addObject("maxFinder", maxFinderResults);
		res.addObject("stddevFinder", stddevFinderResults);
		res.addObject("ratioEmptyFinders", ratioEmptyFinders);

		//ACME PARADE
		final double maxNumRecordsPerHistory = this.dashboardService.maxNumRecordsPerHistory();
		final double minNumRecordsPerHistory = this.dashboardService.minNumRecordsPerHistory();
		final double avgNumRecordsPerHistory = this.dashboardService.avgNumRecordsPerHistory();
		final double stddevNumRecordsPerHistory = this.dashboardService.stddevNumRecordsPerHistory();
		final List<Brotherhood> largestBrotherhoodList = new ArrayList<>(this.dashboardService.largestHistoryBrotherhood());
		final String largestBrotherhoodHistory = largestBrotherhoodList.get(0).getTitle();
		final Collection<Brotherhood> brotherhoodAvgHistory = this.dashboardService.largerThanAvgHistoryBrotherhood();

		res.addObject("maxRec", maxNumRecordsPerHistory);
		res.addObject("minRec", minNumRecordsPerHistory);
		res.addObject("avgRec", avgNumRecordsPerHistory);
		res.addObject("stdRec", stddevNumRecordsPerHistory);
		res.addObject("largestBH", largestBrotherhoodHistory);
		res.addObject("largestAvgBH", brotherhoodAvgHistory);

		//B

		final double ratioAreasNoChapter = this.dashboardService.ratioAreasNoChapter();
		final double avgParadesChapter = this.dashboardService.avgParadesPerChapter();
		final double minParadesChapter = this.dashboardService.minParadesPerChapter();
		final double maxParadesChapter = this.dashboardService.maxParadesPerChapter();
		final double stddevParadesChapter = this.dashboardService.stddevParadesPerChapter();
		final Collection<Chapter> chapterMoreParadesThanAvg = this.dashboardService.chapterMoreParadesThanAvg();
		final double ratioParadesDraftVsFinal = this.dashboardService.ratioParadesDraftModeVsFinalMode();
		final Collection<Double> ratioParadesFinalModeGroupedByStatus = this.dashboardService.ratioParadesFinalModeGroupedByStatus();

		res.addObject("ratioANC", ratioAreasNoChapter);
		res.addObject("avgPC", avgParadesChapter);
		res.addObject("minPC", minParadesChapter);
		res.addObject("maxPC", maxParadesChapter);
		res.addObject("stddevPC", stddevParadesChapter);
		res.addObject("chapterMoreParades", chapterMoreParadesThanAvg);
		res.addObject("ratioPDvF", ratioParadesDraftVsFinal);
		res.addObject("ratioPFM", ratioParadesFinalModeGroupedByStatus);

		return res;

	}
}
