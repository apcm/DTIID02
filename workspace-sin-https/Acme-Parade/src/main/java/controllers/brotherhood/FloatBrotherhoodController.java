
package controllers.brotherhood;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.FloatService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Float;

@Controller
@RequestMapping("/float/brotherhood")
public class FloatBrotherhoodController extends AbstractController {

	//Services

	@Autowired
	private BrotherhoodService	brotherhoodService;

	@Autowired
	private FloatService		floatService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;

		final Brotherhood bro = this.brotherhoodService.findByPrincipal();
		final Collection<Float> floats = this.floatService.findByBrotherhoodId(bro.getId());

		res = new ModelAndView("float/brotherhood/list");
		res.addObject("requestURI", "float/brotherhood/list.do");
		res.addObject("floats", floats);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveEdit")
	public ModelAndView save(@ModelAttribute("flo") final domain.Float flo, final BindingResult binding) {
		ModelAndView result;
		final domain.Float floatReconstructed;
		try {
			floatReconstructed = this.floatService.reconstruct(flo, binding);
			if (binding.hasErrors())
				result = this.createEditModelAndView(flo);
			else {
				this.floatService.save(floatReconstructed);
				result = new ModelAndView("redirect:list.do");
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(flo, "float.commit.error.blank");
		}

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		domain.Float flo;

		flo = this.floatService.create();

		res = this.createEditModelAndView(flo);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int floatId) {
		ModelAndView result;
		domain.Float flo;
		final Brotherhood bro;

		bro = this.brotherhoodService.findByPrincipal();
		flo = this.floatService.findOne(floatId);
		Assert.notNull(flo);
		Assert.isTrue(flo.getBrotherhood().getId() == bro.getId());

		result = this.createEditModelAndView(flo);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final domain.Float flo, final BindingResult binding) {
		ModelAndView result;

		try {
			this.floatService.delete(flo);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(flo, "float.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final domain.Float flo) {
		ModelAndView result;

		result = this.createEditModelAndView(flo, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final domain.Float flo, final String message) {
		ModelAndView result;
		final Brotherhood bro = this.brotherhoodService.findByPrincipal();
		final Collection<Brotherhood> brotherhoods = this.brotherhoodService.findAll();
		result = new ModelAndView("float/brotherhood/edit");
		result.addObject("brotherhoods", brotherhoods);
		result.addObject("flo", flo);
		result.addObject("message", message);

		return result;
	}
	//Show
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int floatId) {
		ModelAndView res;
		domain.Float f;

		res = new ModelAndView("float/brotherhood/show");
		f = this.floatService.findOne(floatId);
		res.addObject("f", f);
		res.addObject("requestURI", "float/brotherhood/show.do");

		return res;
	}

}
