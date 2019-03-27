package controllers.brotherhood;

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

import services.BrotherhoodService;
import services.FloatService;
import services.ParadeService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Parade;

@Controller
@RequestMapping("/parade")
public class ParadeBrotherhoodController extends AbstractController {

	@Autowired
	BrotherhoodService brotherhoodService;

	@Autowired
	ParadeService paradeService;

	@Autowired
	FloatService floatService;

	@RequestMapping(value = "/brotherhood/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;

		final Brotherhood bro = this.brotherhoodService.findByPrincipal();

		final Collection<Parade> parades = this.paradeService
				.findByBrotherhoodId(bro.getId());
		final List<Boolean> finalModes = new ArrayList<>();
		finalModes.add(true);
		finalModes.add(false);

		res = new ModelAndView("parade/brotherhood/list");
		res.addObject("parades", parades);
		res.addObject("requestURI", "parade/brotherhood/list.do");
		res.addObject("finalModes", finalModes);
		return res;
	}

	@Autowired
	Validator validator;

	// @RequestMapping(value = "/brotherhood/edit", method = RequestMethod.POST,
	// params = "save")
	// public ModelAndView save(@ModelAttribute final Parade parade, final
	// BindingResult binding) {
	// ModelAndView result;
	// Parade procMod;
	// try {
	// Assert.notNull(parade.getDescription());
	// Assert.isTrue(parade.getDescription() != "");
	// Assert.notNull(parade.getDepartureDate());
	// Assert.notNull(parade.getFloats());
	// Assert.isTrue(parade.getFloats().isEmpty() == false);
	// Assert.notNull(parade.getTitle());
	// Assert.isTrue(parade.getTitle() != "");
	//
	// } catch (final Throwable error) {
	// result = this.createEditModelAndView(parade, "parade.mandatory");
	// return result;
	// }
	// try {
	// procMod = this.paradeService.reconstruct(parade, binding);
	// this.validator.validate(procMod, binding);
	// if (binding.hasErrors())
	// result = this.createEditModelAndView(procMod);
	// else {
	// this.paradeService.save(procMod);
	// result = new ModelAndView("redirect:list.do");
	// }
	// } catch (final Throwable oops) {
	// result = this.createEditModelAndView(parade, "parade.commit.error");
	// }
	//
	// return result;
	// }

	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute Parade parade,
			final BindingResult binding) {
		ModelAndView result;
		try {
			parade = this.paradeService.reconstruct(parade, binding);
			this.paradeService.save(parade);
			result = new ModelAndView("redirect:list.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(parade);
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(parade, "parade.commit.error");
		}

		return result;

	}

	@RequestMapping(value = "/brotherhood/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Parade parade;

		parade = this.paradeService.create();

		res = this.createEditModelAndView(parade);

		return res;
	}

	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int paradeId) {
		ModelAndView result;
		Parade parade;
		final Brotherhood brotherhood = this.brotherhoodService
				.findByPrincipal();

		parade = this.paradeService.findOne(paradeId);
		Assert.isTrue(parade.getBrotherhood().getId() == brotherhood.getId());
		Assert.isTrue(parade.getFinalMode() == false);
		result = this.createEditModelAndView(parade);

		return result;
	}

	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.POST, params = "delete")
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

	protected ModelAndView createEditModelAndView(final Parade parade,
			final String message) {
		ModelAndView result;
		final Brotherhood bro = this.brotherhoodService.findByPrincipal();
		final Collection<domain.Float> floats = this.floatService
				.findByBrotherhoodId(bro.getId());

		result = new ModelAndView("parade/edit");
		result.addObject("floats", floats);
		result.addObject("parade", parade);
		result.addObject("message", message);

		return result;
	}

	@RequestMapping(value = "brotherhood/copy", method = RequestMethod.GET)
	public ModelAndView copy(@RequestParam int paradeId) {
		ModelAndView result;
		Parade parade = paradeService.findOne(paradeId);
		try {
			Assert.notNull(parade.getDescription());
			Assert.isTrue(parade.getDescription() != "");
			Assert.notNull(parade.getDepartureDate());
			Assert.notNull(parade.getFloats());
			Assert.isTrue(parade.getFloats().isEmpty() == false);
			Assert.notNull(parade.getTitle());
			Assert.isTrue(parade.getTitle() != "");

		} catch (final Throwable error) {
			result = this.createEditModelAndView(parade, "parade.mandatory");
			return result;
		}
		try {
			this.paradeService.copy(parade);
			result = new ModelAndView("redirect:list.do");

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(parade, "parade.commit.error");
		}

		return result;

	}

}
