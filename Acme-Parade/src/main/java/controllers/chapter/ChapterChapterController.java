
package controllers.chapter;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import repositories.AreaRepository;
import security.UserAccount;
import services.ChapterService;
import controllers.AbstractController;
import domain.Area;
import domain.Chapter;

@Controller
@RequestMapping("/chapter/chapter")
public class ChapterChapterController extends AbstractController {

	@Autowired
	ChapterService	chapterService;

	@Autowired
	AreaRepository	areaRepository;


	// Constructors -----------------------------------------------------------

	public ChapterChapterController() {
		super();
	}

	////////////////////////////
	//////////EDIT//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEdit() {
		ModelAndView res;
		Chapter chapter;

		chapter = this.chapterService.findByPrincipal();
		//chapter = this.chapterService.findOne(chapterId);
		res = this.createEditEditModelAndView(chapter);
		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("chapter") final Chapter chapter, final BindingResult binding) {
		ModelAndView result;
		Chapter chapterMod = chapter;

		try { //name, surname, address, email, title, departure	
			Assert.notNull(chapter.getName());
			Assert.isTrue(chapter.getName() != "");
			Assert.notNull(chapter.getEmail());
			Assert.isTrue(chapter.getEmail() != "");
			Assert.notNull(chapter.getAddress());
			Assert.isTrue(chapter.getAddress() != "");
			Assert.notNull(chapter.getTitle());
			Assert.isTrue(chapter.getTitle() != "");
		} catch (final Throwable error) {
			result = this.createEditEditModelAndView(chapter, "chapter.mandatory");
			return result;
		}

		try {
			chapterMod = this.chapterService.reconstruct(chapter, binding);
			if (binding.hasErrors())
				result = this.createEditEditModelAndView(chapter);
			else {
				final String vacia = "";

				if (!chapterMod.getEmail().isEmpty() || chapterMod.getEmail() != vacia)
					Assert.isTrue(chapterMod.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || chapterMod.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

				//	Si se introduce un área, se comprueba que no tenía una
				if (chapter.getArea() != null) {
					final Chapter oldBro = this.chapterService.findOne(chapter.getId());
					Assert.isTrue(oldBro.getArea() != null);
				}
				this.chapterService.save(chapterMod);
				result = new ModelAndView("redirect:/welcome/index.do");
			}
		} catch (final Throwable error) {
			if (error.getMessage() == "Wrong email")
				result = this.createEditEditModelAndView(chapter, "chapter.email.error");
			else
				result = this.createEditEditModelAndView(chapter, "chapter.comit.error");
			System.out.println(error.getMessage());
		}

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Chapter chapter) {
		ModelAndView result;

		result = this.createEditEditModelAndView(chapter, null);

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Chapter chapter, final String message) {
		ModelAndView result;

		UserAccount userAccount;

		userAccount = chapter.getUserAccount();

		Area area = null;
		area = chapter.getArea();
		final Collection<Area> areas = this.areaRepository.findAll();

		result = new ModelAndView("chapter/edit");
		result.addObject("chapter", chapter);
		result.addObject("areas", areas);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		result.addObject("area", area);
		return result;
	}

	////////////////////////////
	//////////SHOW//////////////
	////////////////////////////

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView res;
		Chapter chapter;

		chapter = this.chapterService.findByPrincipal();
		//chapter = this.chapterService.findOne(chapterId);
		res = this.createShowModelAndView(chapter);
		return res;

	}

	protected ModelAndView createShowModelAndView(final Chapter chapter) {
		ModelAndView result;

		result = this.createShowModelAndView(chapter, null);

		return result;

	}

	protected ModelAndView createShowModelAndView(final Chapter chapter, final String message) {
		ModelAndView result;

		UserAccount userAccount;

		userAccount = chapter.getUserAccount();

		result = new ModelAndView("chapter/show");
		result.addObject("chapter", chapter);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////DELETE//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "leave")
	public ModelAndView saveLeave(final Chapter chapter, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditEditModelAndView(chapter);
		else
			try {

				this.chapterService.leave();
				result = new ModelAndView("redirect:../../j_spring_security_logout");
			} catch (final Throwable error) {
				if (error.getMessage() == "Wrong email")
					result = this.createEditEditModelAndView(chapter, "chapter.email.error");
				else
					result = this.createEditEditModelAndView(chapter, "chapter.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

}
