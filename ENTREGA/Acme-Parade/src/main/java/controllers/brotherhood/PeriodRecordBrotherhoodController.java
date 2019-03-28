
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

import repositories.PeriodRecordRepository;
import services.PeriodRecordService;
import controllers.AbstractController;
import domain.PeriodRecord;

@Controller
@RequestMapping("/periodrecord/brotherhood")
public class PeriodRecordBrotherhoodController extends AbstractController {

	@Autowired
	private PeriodRecordService		periodRecordService;

	@Autowired
	private PeriodRecordRepository	periodRecordRepository;


	////////////////
	////CREATE//////
	////////////////

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		PeriodRecord periodRecord;

		periodRecord = this.periodRecordService.create();

		res = this.createEditModelAndView(periodRecord);

		return res;
	}

	protected ModelAndView createEditModelAndView(final PeriodRecord periodRecord) {
		ModelAndView result;

		result = this.createEditModelAndView(periodRecord, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final PeriodRecord periodRecord, final String message) {
		ModelAndView result;
		result = new ModelAndView("periodrecord/brotherhood/edit");
		result.addObject("periodRecord", periodRecord);
		result.addObject("message", message);

		return result;
	}

	////////////////////
	///////EDIT/////////
	////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int periodRecordId) {
		ModelAndView result;
		PeriodRecord periodRecord;

		periodRecord = this.periodRecordRepository.findOne(periodRecordId);
		Assert.notNull(periodRecord);

		result = this.createEditModelAndView(periodRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final PeriodRecord periodRecord, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(periodRecord);
		else
			try {
				this.periodRecordService.save(periodRecord);
				result = new ModelAndView("redirect:../../history/brotherhood/showHistory.do");

			} catch (final Throwable oops) {
				if (oops.getMessage() == "yearValidation")
					result = this.createEditModelAndView(periodRecord, "record.error.year");
				else
					result = this.createEditModelAndView(periodRecord, "record.commit.error");
			}

		return result;
	}

	////////////////////
	///////DELETE///////
	////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final PeriodRecord periodRecord, final BindingResult binding) {
		ModelAndView result;

		try {
			this.periodRecordService.delete(periodRecord);
			result = new ModelAndView("redirect:../../history/brotherhood/showHistory.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(periodRecord, "record.commit.error");
		}

		return result;
	}

}
