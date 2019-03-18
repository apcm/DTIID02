
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
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
	public ModelAndView save(@Valid final ChapterForm chapterForm, final BindingResult binding) {
		ModelAndView result;

		try { //name, surname, address, email, title, departure	
			Assert.notNull(chapterForm.getEmail());
			Assert.isTrue(chapterForm.getEmail() != "");

		} catch (final Throwable error) {
			result = this.createEditModelAndView(chapterForm, "chapter.mandatory");
			return result;
		}

		if (!chapterForm.isConditionsAccepted())
			result = this.createEditModelAndView(chapterForm, "chapter.conditionsError");
		//result.addObject("conditionsError", true);
		else {
			final Chapter chapter = this.chapterService.reconstruct(chapterForm, binding);
			if (binding.hasErrors())
				result = this.createEditModelAndView(chapterForm);
			else
				try {
					final String vacia = "";
					if (!chapter.getEmail().isEmpty() || chapter.getEmail() != vacia)
						Assert.isTrue(chapter.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || chapter.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

					this.chapterService.save(chapter);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (final Throwable error) {
					if (error.getMessage() == "Wrong email")
						result = this.createEditModelAndView(chapterForm, "chapter.email.error");
					else
						result = this.createEditModelAndView(chapterForm, "chapter.comit.error");
					System.out.println(error.getMessage());
				}
		}

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
