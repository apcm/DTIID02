
package controllers.brotherhood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import services.ProcessionService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Parade;

@Controller
@RequestMapping("/procession")
public class ProcessionBrotherhoodController extends AbstractController {

	@Autowired
	BrotherhoodService	brotherhoodService;

	@Autowired
	ProcessionService	processionService;

	@Autowired
	FloatService		floatService;


	@RequestMapping(value = "/brotherhood/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;

		final Brotherhood bro = this.brotherhoodService.findByPrincipal();

		final Collection<Parade> processions = this.processionService.findByBrotherhoodId(bro.getId());
		final List<Boolean> finalModes = new ArrayList<>();
		finalModes.add(true);
		finalModes.add(false);

		res = new ModelAndView("procession/brotherhood/list");
		res.addObject("processions", processions);
		res.addObject("requestURI", "procession/brotherhood/list.do");
		res.addObject("finalModes", finalModes);
		return res;
	}


	@Autowired
	Validator	validator;


	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute final Parade procession, final BindingResult binding) {
		ModelAndView result;
		Parade procMod;
		try {
			Assert.notNull(procession.getDescription());
			Assert.isTrue(procession.getDescription() != "");
			Assert.notNull(procession.getDepartureDate());
			Assert.notNull(procession.getFloats());
			Assert.isTrue(procession.getFloats().isEmpty() == false);
			Assert.notNull(procession.getTitle());
			Assert.isTrue(procession.getTitle() != "");

		} catch (final Throwable error) {
			result = this.createEditModelAndView(procession, "procession.mandatory");
			return result;
		}
		try {
			procMod = this.processionService.reconstruct(procession, binding);
			this.validator.validate(procMod, binding);
			if (binding.hasErrors())
				result = this.createEditModelAndView(procMod);
			else {
				this.processionService.save(procMod);
				result = new ModelAndView("redirect:list.do");
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(procession, "procession.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/brotherhood/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Parade procession;

		procession = this.processionService.create();

		res = this.createEditModelAndView(procession);

		return res;
	}

	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int processionId) {
		ModelAndView result;
		Parade procession;
		final Brotherhood brotherhood = this.brotherhoodService.findByPrincipal();

		procession = this.processionService.findOne(processionId);
		Assert.isTrue(procession.getBrotherhood().getId() == brotherhood.getId());
		Assert.isTrue(procession.getFinalMode() == false);
		result = this.createEditModelAndView(procession);

		return result;
	}

	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Parade procession, final BindingResult binding) {
		ModelAndView result;

		try {
			this.processionService.delete(procession);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(procession, "procession.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Parade procession) {
		ModelAndView result;

		result = this.createEditModelAndView(procession, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Parade procession, final String message) {
		ModelAndView result;
		final Brotherhood bro = this.brotherhoodService.findByPrincipal();
		final Collection<domain.Float> floats = this.floatService.findByBrotherhoodId(bro.getId());

		result = new ModelAndView("procession/edit");
		result.addObject("floats", floats);
		result.addObject("procession", procession);
		result.addObject("message", message);

		return result;
	}

}
