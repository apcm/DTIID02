
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ParadeService;
import domain.Parade;

@Controller
@RequestMapping("/parade")
public class ParadeController extends AbstractController {

	@Autowired
	ParadeService	paradeService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int brotherhoodId) {
		final ModelAndView res;

		final Collection<Parade> parades = this.paradeService.findByBrotherhoodId(brotherhoodId);
		final Collection<Parade> paradesFinal = new ArrayList<>();
		for (final Parade p : parades)
			if (p.getFinalMode() == true && p.getStatus().equals("ACCEPTED"))
				paradesFinal.add(p);
		

		res = new ModelAndView("parade/list");
		res.addObject("requestURI", "parade/list.do");
		res.addObject("parades", paradesFinal);
		return res;
	}

}
