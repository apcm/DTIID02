
package controllers.enrolements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.EnrolementService;
import services.MemberService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Enrolement;
import domain.Member;

@Controller
@RequestMapping("/enrolements/member")
public class EnrolementsMemberController extends AbstractController {

	@Autowired
	MemberService		memberService;

	@Autowired
	BrotherhoodService	brotherhoodService;

	@Autowired
	EnrolementService	enrolementService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Collection<Enrolement> enrolements;

		final Member member = this.memberService.findOnePrincipal();
		enrolements = this.enrolementService.findEnrolementsByMemberId(member);
		res = new ModelAndView("enrolements/list");
		res.addObject("enrolements", enrolements);
		res.addObject("memberView", true);
		res.addObject("requestURI", "enrolements/member/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView res;
		final Enrolement e;

		e = this.enrolementService.create();
		res = this.createEditModelAndView(e);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Enrolement e, final BindingResult binding) {
		ModelAndView res;
		e = this.enrolementService.reconstruct(e, binding);
		if (binding.hasErrors())
			res = this.createEditModelAndView(e);
		else

			try {

				this.enrolementService.save(e);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(e, "error.enrolement");
			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final Enrolement e) {
		ModelAndView res;
		res = this.createEditModelAndView(e, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Enrolement e, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("enrolements/edit");
		final Member m = this.memberService.findByPrincipal();
		final List<Brotherhood> lb = new ArrayList<>(this.brotherhoodService.findAllNotApproved(m));

		res.addObject("memberView", true);
		res.addObject("enrolement", e);
		res.addObject("message", messageCode);
		res.addObject("listBrotherhoods", lb);

		return res;
	}

}
