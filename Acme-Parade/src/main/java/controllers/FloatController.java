
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FloatService;
import domain.Float;

@Controller
@RequestMapping("/float")
public class FloatController extends AbstractController {

	//Services
	@Autowired
	private FloatService	floatService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int brotherhoodId) {
		final ModelAndView res;

		final Collection<Float> floats = this.floatService.findByBrotherhoodId(brotherhoodId);

		res = new ModelAndView("float/list");
		res.addObject("requestURI", "float/list.do");
		res.addObject("floats", floats);
		return res;
	}

}
