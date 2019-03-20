
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
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
	public ModelAndView save(@Valid final MemberForm memberForm, final BindingResult binding) {
		ModelAndView result;

		try { //name, surname, address, email, title, departure	
			Assert.notNull(memberForm.getEmail());
			Assert.isTrue(memberForm.getEmail() != "");

		} catch (final Throwable error) {
			result = this.createEditModelAndView(memberForm, "member.mandatory");
			return result;
		}
		
		if (!memberForm.isConditionsAccepted())
			result = this.createEditModelAndView(memberForm, "member.conditionsError");
		//result.addObject("conditionsError", true);
		else {
			final Member member = this.memberService.reconstruct(memberForm, binding);
			if (binding.hasErrors())
				result = this.createEditModelAndView(memberForm);
			else
				try {
					final String vacia = "";
					if (!member.getEmail().isEmpty() || member.getEmail() != vacia)
						Assert.isTrue(member.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || member.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

					this.memberService.save(member);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (final Throwable error) {
					if (error.getMessage() == "Wrong email")
						result = this.createEditModelAndView(memberForm, "member.email.error");
					else
						result = this.createEditModelAndView(memberForm, "member.comit.error");
					System.out.println(error.getMessage());
				}
		}
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
