
package controllers.chapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import repositories.ParadeRepository;
import services.AreaService;
import services.ChapterService;
import services.FloatService;
import services.ParadeService;
import controllers.AbstractController;
import domain.Area;
import domain.Chapter;
import domain.Parade;

@Controller
@RequestMapping("/parade")
public class ParadeChapterController extends AbstractController {

	@Autowired
	ChapterService		chapterService;

	@Autowired
	ParadeService		paradeService;

	@Autowired
	FloatService		floatService;

	@Autowired
	AreaService			areaService;

	@Autowired
	ParadeRepository	paradeRepository;


	@RequestMapping(value = "/chapter/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;

		final List<Boolean> finalModes = new ArrayList<>();
		finalModes.add(true);
		finalModes.add(false);
		final Collection<Parade> parades = new ArrayList<Parade>();
		final Chapter logChapter = this.chapterService.findByPrincipal();
		if (logChapter.getArea() != null) {
			final Collection<Parade> parades2 = this.paradeService.findParadesByChapterId();
			for (final Parade p : parades2)
				if (p.getFinalMode() == true)
					parades.add(p);
		}
		res = new ModelAndView("parade/chapter/list");
		res.addObject("parades", parades);
		res.addObject("requestURI", "parade/chapter/list.do");
		res.addObject("finalModes", finalModes);
		return res;
	}


	@Autowired
	Validator	validator;


	@RequestMapping(value = "/chapter/editStatus", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute Parade parade, final BindingResult binding) {
		ModelAndView result;
		try {
			final Parade paradeOld = this.paradeRepository.findOne(parade.getId());
			final String subm = "SUBMITTED";
			Assert.isTrue(paradeOld.getStatus() == "SUBMITTED" || paradeOld.getStatus() == subm || paradeOld.getStatus().contains("SUBMITTED"));
			final String rej = "REJECTED";
			if (parade.getId() != 0)
				if (parade.getStatus() == rej || parade.getStatus().contains("REJECTED"))
					Assert.isTrue(parade.getRejectReason() != "", "rejectReason");

			parade = this.paradeService.reconstructChapter(parade, binding);

			this.paradeService.saveStatus(parade);
			result = new ModelAndView("redirect:list.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(parade);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "rejectReason")
				result = this.createEditModelAndView(parade, "parade.error.rejectReason");
			else
				result = this.createEditModelAndView(parade, "parade.commit.error");
		}

		return result;

	}

	@RequestMapping(value = "/chapter/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Parade parade;

		parade = this.paradeService.create();

		res = this.createEditModelAndView(parade);

		return res;
	}

	@RequestMapping(value = "/chapter/editStatus", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int paradeId) {
		ModelAndView result;
		Parade parade;

		parade = this.paradeService.findOne(paradeId);
		//Area de la parade
		final Area area = this.areaService.getParadeArea(paradeId);
		//Se comprueba que la parade pertenece a la área que gestiona el chapter logeado
		final Chapter cha = this.chapterService.findByPrincipal();
		Assert.isTrue(area == cha.getArea());

		Assert.isTrue(parade.getFinalMode() == true);
		result = this.createEditModelAndView(parade);

		return result;
	}

	@RequestMapping(value = "/chapter/editStatus", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Parade parade, final BindingResult binding) {
		ModelAndView result;

		try {
			this.paradeService.delete(parade);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(parade, "parade.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Parade parade) {
		ModelAndView result;

		result = this.createEditModelAndView(parade, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Parade parade, final String message) {
		ModelAndView result;

		result = new ModelAndView("parade/chapter/editStatus");
		result.addObject("parade", parade);
		result.addObject("message", message);

		return result;
	}
}
