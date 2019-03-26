
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomisationService;
import domain.Customisation;

@Controller
@RequestMapping("/customisation")
public class CustomisationController {

	@Autowired
	private CustomisationService	SCService;


	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Customisation customisation;

		customisation = this.SCService.getCustomisation();

		Assert.notNull(customisation);

		result = this.createEditModelAndView(customisation);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Customisation customisation, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(customisation);
		else
			try {
				this.SCService.save(customisation);
				result = new ModelAndView("redirect:/welcome/index.do"); 
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(customisation, "customisation.commit.error");
			}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Customisation customisation) {
		ModelAndView result;

		result = this.createEditModelAndView(customisation, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Customisation customisation, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("customisation/edit");
		result.addObject("customisation", customisation);
		result.addObject("message", messageCode);
		final String banner = this.SCService.getCustomisation().getBannerURL();
		result.addObject("bannerURL", banner);
		return result;
	}

}
