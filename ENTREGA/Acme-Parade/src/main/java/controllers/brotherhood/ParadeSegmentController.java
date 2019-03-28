
package controllers.brotherhood;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ParadeService;
import services.SegmentService;
import controllers.AbstractController;
import domain.Parade;
import domain.Segment;

@Controller
@RequestMapping("/parade/segment")
public class ParadeSegmentController extends AbstractController {

	@Autowired
	private SegmentService	segmentService;

	@Autowired
	private ParadeService	paradeService;

	private Parade			paradeACT;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int paradeId) {
		ModelAndView result;

		final Parade parade = this.paradeService.findOne(paradeId);
		final List<Segment> segments = this.segmentService.getSegmentsByParade(parade);
		if (segments == null)
			return new ModelAndView("redirect:/welcome/index.do");

		this.paradeACT = parade;

		result = new ModelAndView("parade/segment/list");
		result.addObject("segments", segments);
		result.addObject("requestURI", "/parade/segment/list.do");
		if (!segments.isEmpty())
			result.addObject("lastId", segments.get(segments.size() - 1).getId());

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Segment segment;

		segment = this.segmentService.create(this.paradeACT.getId());

		result = this.createEditModelAndView(segment, this.paradeACT);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int segmentId) {
		ModelAndView result;
		Segment segment;

		segment = this.segmentService.findOne(segmentId);
		Assert.notNull(segment);

		if (!this.paradeACT.getSegments().contains(segment))
			return new ModelAndView("redirect:/welcome/index.do");
		if (this.paradeACT.getSegments().size() == segment.getSegmentOrder())
			result = this.createEditModelAndView(segment, this.paradeACT);
		else
			result = this.list(this.paradeACT.getId());

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Segment segment, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(segment, this.paradeACT);
		else
			try {
				if (this.segmentService.isCorrectDate(segment, this.paradeACT)) {
					this.paradeService.saveSegmentInParade(segment, this.paradeACT);
					result = this.list(this.paradeACT.getId());
				} else
					result = this.createEditModelAndView(segment, this.paradeACT, "segment.commit.error");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(segment, this.paradeACT, "segment.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Segment segment, final BindingResult binding) {
		ModelAndView result;

		try {
			this.paradeService.deleteSegmentInParade(segment, this.paradeACT);
			result = this.list(this.paradeACT.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(segment, this.paradeACT);
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Segment segment, final Parade parade) {
		ModelAndView result;
		result = this.createEditModelAndView(segment, parade, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Segment segment, final Parade parade, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("parade/segment/edit");
		result.addObject("segment", segment);

		result.addObject("parade", parade);
		result.addObject("message", messageCode);

		return result;
	}

	//-------------------------DISPLAY-----------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int segmentId) {
		ModelAndView result;
		Segment segment;

		segment = this.segmentService.findOne(segmentId);
		Assert.notNull(segment);

		result = this.createDisplayModelAndView(segment);

		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Segment segment) {
		ModelAndView result;
		result = this.createDisplayModelAndView(segment, null);

		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Segment segment, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("parade/segment/display");
		result.addObject("segment", segment);
		result.addObject("messageCode", messageCode);

		return result;

	}

}
