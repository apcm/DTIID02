
package controllers.member;

import java.util.Calendar;
import java.util.Collection;
import java.util.Set;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AreaService;
import services.FinderService;
import services.MemberService;
import services.ParadeService;
import controllers.AbstractController;
import domain.Area;
import domain.Finder;
import domain.Member;
import domain.Parade;

@Controller
@RequestMapping("/finder/member")
public class FinderMemberController extends AbstractController {

	//Services
	@Autowired
	private FinderService	finderService;

	@Autowired
	private MemberService	memberService;

	@Autowired
	private AreaService		areaService;

	@Autowired
	private ParadeService	paradeService;


	//Constructor
	public FinderMemberController() {
		super();
	}

	//Show
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		final ModelAndView res;
		Member member;
		Finder finder;

		member = this.memberService.findOnePrincipal();
		finder = this.finderService.getFinderMember(member.getId());
		if (this.finderService.checkCache(finder))
			finder = this.finderService.clear(finder);

		res = new ModelAndView("finder/show");
		res.addObject("finder", finder);
		return res;
	}

	//Edit
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		Finder finder;

		finder = this.finderService.getFinderMember(this.memberService.findByPrincipal().getId());
		Assert.notNull(finder);
		res = this.createEditModelAndView(finder);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Finder finder, final BindingResult binding) {
		ModelAndView res;

		try {
			finder = this.finderService.reconstruct(finder, binding);

			if (finder.getId() != 0) {
				final Member principal = this.memberService.findByPrincipal();
				Assert.isTrue(principal.getFinder().getId() == finder.getId());
				if (finder.getStartDate() != null && finder.getEndDate() != null)
					Assert.isTrue(finder.getStartDate().before(finder.getEndDate()));
				final Set<Parade> results = this.paradeService.finderResults(finder);
				for (final Parade p : results)
					if (p.getFinalMode() == false)
						results.remove(p);
				finder.setParades(results);
				finder.setMoment(Calendar.getInstance().getTime());
			}

			this.finderService.save(finder);
			res = new ModelAndView("redirect:show.do");
		} catch (final ValidationException oops) {
			res = this.createEditModelAndView(finder);
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(finder, "finder.error");
		}
		return res;

		//		final Finder finderMod = this.finderService.reconstruct(finder, binding);
		//		if (binding.hasErrors())
		//			res = this.createEditModelAndView(finderMod);
		//		else
		//			try {
		//				this.finderService.save(finderMod);
		//				res = new ModelAndView("redirect:show.do");
		//			} catch (final Throwable oops) {
		//				res = this.createEditModelAndView(finderMod, "finder.error");
		//			}
		//		return res;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "clear")
	public ModelAndView clear(final Finder finder, final BindingResult binding) {
		ModelAndView res;

		try {
			this.finderService.clear(finder);
			res = new ModelAndView("redirect:show.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(finder, "finder");
		}
		return res;

	}

	//Ancillary methods
	protected ModelAndView createEditModelAndView(final Finder finder) {
		final ModelAndView res;

		res = this.createEditModelAndView(finder, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final Finder finder, final String message) {
		final ModelAndView res;
		Collection<Area> areas;
		areas = this.areaService.findAll();

		res = new ModelAndView("finder/edit");
		res.addObject("finder", finder);
		res.addObject("areas", areas);
		res.addObject("message", message);

		return res;
	}
}
