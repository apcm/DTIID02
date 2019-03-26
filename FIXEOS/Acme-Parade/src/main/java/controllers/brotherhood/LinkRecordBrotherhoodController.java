
package controllers.brotherhood;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import repositories.LinkRecordRepository;
import services.BrotherhoodService;
import services.LinkRecordService;
import controllers.AbstractController;
import domain.LinkRecord;

@Controller
@RequestMapping("/linkrecord/brotherhood")
public class LinkRecordBrotherhoodController extends AbstractController {

	@Autowired
	private LinkRecordService		linkRecordService;

	@Autowired
	private LinkRecordRepository	linkRecordRepository;

	@Autowired
	private BrotherhoodService		brotherhoodService;


	////////////////
	////CREATE//////
	////////////////

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		LinkRecord linkRecord;

		linkRecord = this.linkRecordService.create();

		res = this.createEditModelAndView(linkRecord);

		return res;
	}

	protected ModelAndView createEditModelAndView(final LinkRecord linkRecord) {
		ModelAndView result;

		result = this.createEditModelAndView(linkRecord, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final LinkRecord linkRecord, final String message) {
		ModelAndView result;
		final Collection<String> links = this.brotherhoodService.findBrotherhoodsNotLinked();

		result = new ModelAndView("linkrecord/brotherhood/edit");
		result.addObject("linkRecord", linkRecord);
		result.addObject("links", links);
		result.addObject("message", message);

		return result;
	}

	////////////////////
	///////EDIT/////////
	////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int linkRecordId) {
		ModelAndView result;
		LinkRecord linkRecord;

		linkRecord = this.linkRecordRepository.findOne(linkRecordId);
		Assert.notNull(linkRecord);

		result = this.createEditModelAndView(linkRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final LinkRecord linkRecord, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(linkRecord);
		else
			try {
				this.linkRecordService.save(linkRecord);
				result = new ModelAndView("redirect:../../history/brotherhood/showHistory.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(linkRecord, "record.commit.error");
			}

		return result;
	}

	////////////////////
	///////DELETE///////
	////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final LinkRecord linkRecord, final BindingResult binding) {
		ModelAndView result;

		try {
			this.linkRecordService.delete(linkRecord);
			result = new ModelAndView("redirect:../../history/brotherhood/showHistory.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(linkRecord, "record.commit.error");
		}

		return result;
	}

}
