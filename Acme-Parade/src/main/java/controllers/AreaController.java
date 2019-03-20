
package controllers;

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

import services.AreaService;
import services.BrotherhoodService;
import domain.Area;

@Controller
@RequestMapping("/area")
public class AreaController extends AbstractController {

	//Services
	@Autowired
	private AreaService			areaService;

	@Autowired
	private BrotherhoodService	brotherhoodService;


	//List
	@RequestMapping(value = "/administrator/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Area> areas;
		//		Collection<String> titles;

		areas = this.areaService.findAll();
		//		titles = this.areaService.getBrotherhoodTitle();

		res = new ModelAndView("area/administrator/list");
		res.addObject("area", areas);
		//		res.addObject("bros", titles);
		res.addObject("requestURI", "area/administrator/list.do");

		return res;
	}

	//Create and Edit
	@RequestMapping(value = "/administrator/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Area a;

		a = this.areaService.create();
		res = this.createModelAndView(a);

		return res;
	}

	@RequestMapping(value = "/administrator/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int areaId) {
		ModelAndView res;
		Area a;

		a = this.areaService.findOne(areaId);
		Assert.notNull(a);
		res = this.editModelAndView(a);

		return res;
	}

	//Save
	@RequestMapping(value = "/administrator/edit", method = RequestMethod.POST, params = "saveEdit")
	public ModelAndView saveEdit(@Valid final Area a, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.editModelAndView(a);
		else
			try {
				this.areaService.save(a);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.editModelAndView(a, "area.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/administrator/create", method = RequestMethod.POST, params = "saveCreate")
	public ModelAndView saveCreate(@Valid final Area a, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createModelAndView(a);
		else
			try {
				this.areaService.save(a);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createModelAndView(a, "area.commit.error");
			}
		return res;
	}

	//Delete
	@RequestMapping(value = "/administrator/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Area a, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.editModelAndView(a);
		else
			try {
				this.areaService.delete(a);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.editModelAndView(a, "area.commit.error");
			}
		return res;
	}

	//Pictures
	@RequestMapping(value = "/pictures", method = RequestMethod.GET)
	public ModelAndView pictures(@RequestParam final int areaId) {
		ModelAndView res;
		Area a;
		Collection<String> pictures;

		res = new ModelAndView("area/pictures");
		a = this.areaService.findOne(areaId);
		Assert.notNull(a);
		pictures = a.getPictures();
		res.addObject("pictures", pictures);
		res.addObject("requestURI", "area/pictures.do");

		return res;
	}

	protected ModelAndView createModelAndView(final Area a) {
		ModelAndView res;
		res = this.createModelAndView(a, null);
		return res;
	}

	protected ModelAndView createModelAndView(final Area a, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("area/administrator/create");
		res.addObject("area", a);
		res.addObject("brotherhoods", this.brotherhoodService.findAll());
		res.addObject("message", messageCode);
		return res;
	}

	protected ModelAndView editModelAndView(final Area a) {
		ModelAndView res;
		res = this.editModelAndView(a, null);
		return res;
	}

	protected ModelAndView editModelAndView(final Area a, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("area/administrator/edit");
		res.addObject("area", a);
		res.addObject("brotherhoods", this.brotherhoodService.findAll());
		res.addObject("message", messageCode);
		return res;
	}
}
