
package controllers.brotherhood;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import repositories.LegalRecordRepository;
import services.LegalRecordService;
import controllers.AbstractController;
import domain.LegalRecord;

@Controller
@RequestMapping("/legalrecord/brotherhood")
public class LegalRecordBrotherhoodController extends AbstractController {

	@Autowired
	private LegalRecordService		legalRecordService;

	@Autowired
	private LegalRecordRepository	legalRecordRepository;


	////////////////
	////CREATE//////
	////////////////

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		LegalRecord legalRecord;

		legalRecord = this.legalRecordService.create();

		res = this.createEditModelAndView(legalRecord);

		return res;
	}

	protected ModelAndView createEditModelAndView(final LegalRecord legalRecord) {
		ModelAndView result;

		result = this.createEditModelAndView(legalRecord, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final LegalRecord legalRecord, final String message) {
		ModelAndView result;
		result = new ModelAndView("legalrecord/brotherhood/edit");
		result.addObject("legalRecord", legalRecord);
		result.addObject("message", message);

		return result;
	}

	////////////////////
	///////EDIT/////////
	////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int legalRecordId) {
		ModelAndView result;
		LegalRecord legalRecord;

		legalRecord = this.legalRecordRepository.findOne(legalRecordId);
		Assert.notNull(legalRecord);

		result = this.createEditModelAndView(legalRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final LegalRecord legalRecord, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(legalRecord);
		else
			try {
				this.legalRecordService.save(legalRecord);
				result = new ModelAndView("redirect:../../history/brotherhood/showHistory.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(legalRecord, "record.commit.error");
			}

		return result;
	}

	////////////////////
	///////DELETE///////
	////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final LegalRecord legalRecord, final BindingResult binding) {
		ModelAndView result;

		try {
			this.legalRecordService.delete(legalRecord);
			result = new ModelAndView("redirect:../../history/brotherhood/showHistory.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(legalRecord, "record.commit.error");
		}

		return result;
	}

}
