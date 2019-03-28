
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
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import services.AreaService;
import services.ChapterService;
import domain.Area;
import domain.Chapter;
import forms.ChapterForm;

@Controller
@RequestMapping("/chapter")
public class ChapterController extends AbstractController {

	@Autowired
	private ChapterService	chapterService;

	@Autowired
	private AreaService		areaService;


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;

		Authority authority;
		Collection<Authority> authorities;
		UserAccount userAccount;
		userAccount = new UserAccount();
		authorities = userAccount.getAuthorities();
		authority = new Authority();
		authority.setAuthority(Authority.CHAPTER);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		final ChapterForm chapterForm = new ChapterForm();
		chapterForm.setUserAccount(userAccount);
		res = this.createEditModelAndView(chapterForm);

		//chapter = this.chapterService.create();
		//res = this.createEditModelAndView(chapter);
		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute final ChapterForm chapterForm, final BindingResult binding) {
		ModelAndView result;

		try {
			Assert.isTrue(chapterForm.isConditionsAccepted(), "conditionsAccepted");

			final Chapter chapter = this.chapterService.reconstruct(chapterForm, binding);
			final String vacia = "";
			if (!chapter.getEmail().isEmpty() || chapter.getEmail() != vacia)
				Assert.isTrue(chapter.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || chapter.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			this.chapterService.save(chapter);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(chapterForm);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditModelAndView(chapterForm, "chapter.email.error");
			else if (oops.getMessage() == "conditionsAccepted")
				result = this.createEditModelAndView(chapterForm, "member.conditionsError");
			else
				result = this.createEditModelAndView(chapterForm, "chapter.comit.error");
		}
		if (!chapterForm.isConditionsAccepted())
			result = this.createEditModelAndView(chapterForm, "chapter.conditionsError");

		return result;

	}

	// Ancillary methods ------------------------------
	protected ModelAndView createEditModelAndView(final ChapterForm chapterForm) {
		ModelAndView result;

		result = this.createEditModelAndView(chapterForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final ChapterForm chapterForm, final String message) {
		ModelAndView result;

		final Collection<Area> areas = this.areaService.findNotAssigned();

		result = new ModelAndView("chapter/register");
		result.addObject("areas", areas);
		result.addObject("chapterForm", chapterForm);
		result.addObject("message", message);

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;

		final Collection<Chapter> chapters = this.chapterService.findAll();

		res = new ModelAndView("chapter/list");
		res.addObject("requestURI", "chapter/list.do");
		res.addObject("chapters", chapters);
		return res;
	}

}
