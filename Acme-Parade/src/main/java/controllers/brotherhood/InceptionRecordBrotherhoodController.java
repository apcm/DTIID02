
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

import repositories.InceptionRecordRepository;
import services.InceptionRecordService;
import controllers.AbstractController;
import domain.InceptionRecord;

@Controller
@RequestMapping("/inceptionrecord/brotherhood")
public class InceptionRecordBrotherhoodController extends AbstractController {

	@Autowired
	private InceptionRecordService		inceptionRecordService;

	@Autowired
	private InceptionRecordRepository	inceptionRecordRepository;


	////////////////
	////CREATE//////
	////////////////

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		InceptionRecord inceptionRecord;

		inceptionRecord = this.inceptionRecordService.create();

		res = this.createEditModelAndView(inceptionRecord);

		return res;
	}

	protected ModelAndView createEditModelAndView(final InceptionRecord inceptionRecord) {
		ModelAndView result;

		result = this.createEditModelAndView(inceptionRecord, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final InceptionRecord inceptionRecord, final String message) {
		ModelAndView result;
		result = new ModelAndView("inceptionrecord/brotherhood/edit");
		result.addObject("inceptionRecord", inceptionRecord);
		result.addObject("message", message);

		return result;
	}

	////////////////////
	///////EDIT/////////
	////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int inceptionRecordId) {
		ModelAndView result;
		InceptionRecord inceptionRecord;

		inceptionRecord = this.inceptionRecordRepository.findOne(inceptionRecordId);
		Assert.notNull(inceptionRecord);

		result = this.createEditModelAndView(inceptionRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final InceptionRecord inceptionRecord, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(inceptionRecord);
		else
			try {
				this.inceptionRecordService.save(inceptionRecord);
				result = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(inceptionRecord, "record.commit.error");
			}

		return result;
	}

	////////////////////
	///////DELETE///////
	////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final InceptionRecord inceptionRecord, final BindingResult binding) {
		ModelAndView result;

		try {
			this.inceptionRecordService.delete(inceptionRecord);
			result = new ModelAndView("redirect:../../");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(inceptionRecord, "record.commit.error");
		}

		return result;
	}

}
