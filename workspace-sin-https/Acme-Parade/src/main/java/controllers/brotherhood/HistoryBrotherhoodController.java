
package controllers.brotherhood;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.BrotherhoodService;
import domain.Brotherhood;
import domain.InceptionRecord;
import domain.LegalRecord;
import domain.LinkRecord;
import domain.PeriodRecord;

@Controller
@RequestMapping("/history/brotherhood")
public class HistoryBrotherhoodController {

	@Autowired
	private BrotherhoodService	brotherhoodService;


	@RequestMapping(value = "/showHistory", method = RequestMethod.GET)
	public ModelAndView showHistory() {
		ModelAndView res;
		Brotherhood brotherhood;

		brotherhood = this.brotherhoodService.findByPrincipal();
		//brotherhood = this.brotherhoodService.findOne(brotherhoodId);
		res = this.createShowRecordsModelAndView(brotherhood);
		return res;

	}

	protected ModelAndView createShowRecordsModelAndView(final Brotherhood brotherhood) {
		ModelAndView result;

		result = this.createShowRecordsModelAndView(brotherhood, null);

		return result;

	}

	protected ModelAndView createShowRecordsModelAndView(final Brotherhood brotherhood, final String message) {
		final ModelAndView result;

		UserAccount userAccount;
		userAccount = brotherhood.getUserAccount();

		final Collection<LegalRecord> legalRecords = brotherhood.getLegalRecords();
		final Collection<PeriodRecord> periodRecords = brotherhood.getPeriodRecords();
		final Collection<InceptionRecord> inceptionRecords = brotherhood.getInceptionRecords();
		final Collection<LinkRecord> linkRecords = brotherhood.getLinkRecords();

		result = new ModelAndView("history/brotherhood/showHistory");
		result.addObject("brotherhood", brotherhood);
		result.addObject("legalRecords", legalRecords);
		result.addObject("periodRecords", periodRecords);
		result.addObject("inceptionRecords", inceptionRecords);
		result.addObject("linkRecords", linkRecords);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

}
