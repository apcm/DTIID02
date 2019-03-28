
package controllers.chapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/chapter/segment")
public class ParadeChapterSegmentController extends AbstractController {

	@Autowired
	private ParadeService	paradeService;

	@Autowired
	private SegmentService	segmentService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int paradeId) {
		ModelAndView result;
		final Parade parade = this.paradeService.findOne(paradeId);
		final List<Segment> segments = this.segmentService.getSegmentsByParadeChapter(parade);

		result = new ModelAndView("chapter/segment/list");
		result.addObject("segments", segments);
		result.addObject("requestURI", "/chapter/segment/list.do");

		return result;
	}

}
