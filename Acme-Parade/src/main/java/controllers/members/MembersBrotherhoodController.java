
package controllers.members;

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
@RequestMapping("/members/brotherhood")
public class MembersBrotherhoodController extends AbstractController {

	@Autowired
	MemberService		memberService;

	@Autowired
	PositionService		positionService;

	@Autowired
	BrotherhoodService	brotherhoodService;

	@Autowired
	EnrolementService	enrolementService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Collection<Member> members;

		final Brotherhood b = this.brotherhoodService.findOnePrincipal();
		members = this.memberService.membersByBrotherhood(b);
		res = new ModelAndView("members/list");
		res.addObject("members", members);
		res.addObject("requestURI", "members/brotherhood/list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int memberId) {
		final ModelAndView res;
		final Member member;
		final Brotherhood b;
		final Enrolement enrolement;

		final Member m = new Member();
		m.setId(memberId);
		member = this.memberService.findOne(m);
		b = this.brotherhoodService.findOnePrincipal();

		enrolement = this.enrolementService.findEnrolementByIds(b, member);
		this.brotherhoodService.checkBrotherhood2(enrolement);

		res = new ModelAndView("members/show");
		res.addObject("member", member);
		res.addObject("enrolement", enrolement);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteMember(final Member m, final BindingResult binding) {
		ModelAndView res;

		try {
			this.brotherhoodService.deleteMember(m);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:list.do");
		}
		return res;

	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "edit")
	public ModelAndView editMember(final Enrolement e, final BindingResult binding) {
		ModelAndView res;

		try {
			final Enrolement e1 = this.enrolementService.findOne(e);
			this.brotherhoodService.checkBrotherhood(e1);
			res = this.createEditModelAndView(e1);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:list.do");
		}
		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveMember(Enrolement e, final BindingResult binding) {
		ModelAndView res;
		e = this.enrolementService.reconstruct2(e, binding);
		try {
			this.brotherhoodService.checkBrotherhood(e);
			this.enrolementService.saveDirectly(e);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:list.do");
		}
		return res;

	}

	private ModelAndView createEditModelAndView(final Enrolement e1) {
		ModelAndView res;

		res = this.createEditModelAndView(e1, null);
		return res;
	}

	private ModelAndView createEditModelAndView(final Enrolement e1, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("members/edit");
		final List<Position> lp = new ArrayList<>(this.positionService.findAll());

		res.addObject("enrolement", e1);
		res.addObject("message", messageCode);
		res.addObject("listPositions", lp);

		return res;
	}
}
