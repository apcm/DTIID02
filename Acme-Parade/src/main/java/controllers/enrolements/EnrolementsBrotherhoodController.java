
package controllers.enrolements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.EnrolementService;
import services.MemberService;
import services.PositionService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Enrolement;
import domain.Member;
import domain.Position;

@Controller
@RequestMapping("/enrolements/brotherhood")
public class EnrolementsBrotherhoodController extends AbstractController {

	@Autowired
	MemberService		memberService;

	@Autowired
	BrotherhoodService	brotherhoodService;

	@Autowired
	PositionService		positionService;

	@Autowired
	EnrolementService	enrolementService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Collection<Enrolement> enrolements;

		final Brotherhood b = this.brotherhoodService.findOnePrincipal();
		enrolements = this.enrolementService.enrolementsPending(b.getId());
		res = new ModelAndView("enrolements/list");
		res.addObject("enrolements", enrolements);
		res.addObject("brotherhoodView", true);
		res.addObject("requestURI", "enrolements/brotherhood/list.do");

		return res;
	}

	@RequestMapping(value = "/enrol", method = RequestMethod.GET)
	public ModelAndView enrol(@RequestParam final int enrolementId) {
		ModelAndView res;

		try {
			final Enrolement e = new Enrolement();
			e.setId(enrolementId);
			final Enrolement enrolement = this.enrolementService.findOne(e);
			this.brotherhoodService.checkBrotherhood(enrolement);
			res = this.createEditModelAndView(enrolement);
		} catch (final Throwable oops) {
			res = new ModelAndView("enrolements/list");
		}

		return res;
	}

	@RequestMapping(value = "/enrol", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Enrolement e, final BindingResult binding) {
		ModelAndView res;
		e = this.enrolementService.reconstruct2(e, binding);

		if (binding.hasErrors())
			res = this.createEditModelAndView(e);
		else

			try {
				this.brotherhoodService.checkBrotherhood(e);
				this.enrolementService.enrolMember(e);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(e, "error.enrolement");
			}

		return res;
	}

	private ModelAndView createEditModelAndView(final Enrolement e) {
		ModelAndView res;

		res = this.createEditModelAndView(e, null);
		return res;
	}

	private ModelAndView createEditModelAndView(final Enrolement e, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("enrolements/edit");
		final List<Position> lp = new ArrayList<>(this.positionService.findAll());
		res.addObject("brotherhoodView", true);
		res.addObject("enrolement", e);
		res.addObject("message", messageCode);
		res.addObject("listPositions", lp);

		return res;
	}

	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	public ModelAndView reject(@RequestParam final int enrolementId) {
		ModelAndView res;
		try {
			final Enrolement e = new Enrolement();
			e.setId(enrolementId);
			final Enrolement enrolement = this.enrolementService.findOne(e);
			this.brotherhoodService.checkBrotherhood(enrolement);
			this.enrolementService.rejectMember(enrolement);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = new ModelAndView("enrolements/list");
		}

		return res;
	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int enrolementId) {
		final ModelAndView res;
		final Member member;
		final Enrolement enrolement;

		final Enrolement e = new Enrolement();
		e.setId(enrolementId);
		enrolement = this.enrolementService.findOne(e);
		this.brotherhoodService.checkBrotherhood(enrolement);
		member = this.memberService.memberByEnrolemetId(enrolementId);

		res = new ModelAndView("enrolements/show");
		res.addObject("member", member);
		res.addObject("enrolement", enrolement);

		return res;
	}
}
