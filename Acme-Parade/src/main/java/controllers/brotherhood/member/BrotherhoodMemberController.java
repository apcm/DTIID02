
package controllers.brotherhood.member;

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
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Enrolement;
import domain.Member;

@Controller
@RequestMapping("/brotherhood/member")
public class BrotherhoodMemberController extends AbstractController {

	@Autowired
	BrotherhoodService	brotherhoodService;

	@Autowired
	MemberService		memberService;

	@Autowired
	EnrolementService	enrolementService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int brotherhoodId) {
		final ModelAndView res;
		final Brotherhood brotherhood;
		final Enrolement enrolement;
		final Member m;

		final Brotherhood b = new Brotherhood();
		b.setId(brotherhoodId);
		brotherhood = this.brotherhoodService.findOne(b);
		m = this.memberService.findOnePrincipal();
		enrolement = this.enrolementService.findEnrolementByIds(b, m);

		res = new ModelAndView("brotherhood/show");
		res.addObject("brotherhood", brotherhood);
		res.addObject("enrolement", enrolement);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.POST, params = "delete")
	public ModelAndView leaveBrotherhood(final Brotherhood b, final BindingResult binding) {
		ModelAndView res;

		try {
			final Member m = this.memberService.findOnePrincipal();
			final Enrolement e = this.enrolementService.findEnrolementByIds(b, m);
			this.enrolementService.leaveBrotherhood(e);
			res = new ModelAndView("redirect:/welcome/index.do");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/welcome/index.do");
		}
		return res;

	}
}
