
package controllers;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import repositories.MemberRepository;
import security.Authority;
import security.UserAccount;
import services.MemberService;
import domain.Member;
import forms.MemberForm;

@Controller
@RequestMapping("/member")
public class MemberController extends AbstractController {

	@Autowired
	MemberService		memberService;

	@Autowired
	MemberRepository	memberRepository;


	// Constructors -----------------------------------------------------------

	public MemberController() {
		super();
	}
	// Edition ------------------------------
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {
		final ModelAndView res;
		//		Member member;
		//
		//		member = this.memberService.create();
		//		res = this.createEditModelAndView(member);

		Authority authority;
		Collection<Authority> authorities;
		UserAccount userAccount;
		userAccount = new UserAccount();
		authorities = userAccount.getAuthorities();
		authority = new Authority();
		authority.setAuthority(Authority.MEMBER);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		final MemberForm memberForm = new MemberForm();
		memberForm.setUserAccount(userAccount);
		res = this.createEditModelAndView(memberForm);

		return res;

	}
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute final MemberForm memberForm, final BindingResult binding) {
		ModelAndView result;

		try {
			final Member member = this.memberService.reconstruct(memberForm, binding);
			final String vacia = "";
			if (!member.getEmail().isEmpty() || member.getEmail() != vacia)
				Assert.isTrue(member.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || member.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			this.memberService.save(member);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(memberForm);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditModelAndView(memberForm, "member.email.error");
			else
				result = this.createEditModelAndView(memberForm, "member.comit.error");
		}
		if (!memberForm.isConditionsAccepted())
			result = this.createEditModelAndView(memberForm, "member.conditionsError");

		return result;
	}

	// Ancillary methods ------------------------------
	protected ModelAndView createEditModelAndView(final MemberForm memberForm) {
		ModelAndView result;

		result = this.createEditModelAndView(memberForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final MemberForm memberForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("member/register");
		result.addObject("memberForm", memberForm);
		result.addObject("message", message);

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int brotherhoodId) {
		final ModelAndView res;

		final Collection<Member> members = this.memberRepository.membersByBrotherhood(brotherhoodId);

		res = new ModelAndView("member/list");
		res.addObject("requestURI", "member/list.do");
		res.addObject("members", members);
		return res;
	}

}
