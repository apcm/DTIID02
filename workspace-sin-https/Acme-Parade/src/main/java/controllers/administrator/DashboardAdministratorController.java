
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

		final double minMembers = this.dashboardService.minMembers();
		final double maxMembers = this.dashboardService.maxMembers();
		final double avgMembers = this.dashboardService.avgMembers();
		final double stddevMembers = this.dashboardService.stddevMembers();

		final Collection<Brotherhood> largestBrotherhoods = this.dashboardService.largestBrotherhoods();

		final Collection<Brotherhood> smallestBrotherhoods = this.dashboardService.smallestsBrotherhoods();

		final List<Object[]> requestRatioByParade = this.dashboardService.requestRatioByParade();
		final List<Double> ratioList = new ArrayList<>();
		final List<String> paradeList = new ArrayList<>();
		final List<String> statusList = new ArrayList<>();
		for (final Object[] o : requestRatioByParade) {
			ratioList.add((double) o[0]);
			paradeList.add((String) o[1]);
			statusList.add((String) o[2]);
		}

		final Collection<Parade> paradesOrganizedIn30Days = this.dashboardService.paradesOrganizedIn30Days();

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

		res = new ModelAndView("dashboard/dashboard");
		res.addObject("minM", minMembers);
		res.addObject("maxM", maxMembers);
		res.addObject("avgM", avgMembers);
		res.addObject("stddevM", stddevMembers);
		res.addObject("largestB", largestBrotherhoods);
		res.addObject("smallestB", smallestBrotherhoods);
		//res.addObject("rRatioP", requestRatioByParade);
		res.addObject("ratioList", ratioList);
		res.addObject("paradeList", paradeList);
		res.addObject("statusList", statusList);
		res.addObject("p30", paradesOrganizedIn30Days);
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

		return res;
	}
}
