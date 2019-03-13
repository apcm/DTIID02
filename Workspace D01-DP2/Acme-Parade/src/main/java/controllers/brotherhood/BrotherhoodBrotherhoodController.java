
package controllers.brotherhood;

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
import services.BrotherhoodService;
import controllers.AbstractController;
import domain.Area;
import domain.Brotherhood;

@Controller
@RequestMapping("/brotherhood/brotherhood")
public class BrotherhoodBrotherhoodController extends AbstractController {

	@Autowired
	BrotherhoodService	brotherhoodService;

	@Autowired
	AreaRepository		areaRepository;


	// Constructors -----------------------------------------------------------

	public BrotherhoodBrotherhoodController() {
		super();
	}

	////////////////////////////
	//////////EDIT//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEdit() {
		ModelAndView res;
		Brotherhood brotherhood;

		brotherhood = this.brotherhoodService.findByPrincipal();
		//brotherhood = this.brotherhoodService.findOne(brotherhoodId);
		res = this.createEditEditModelAndView(brotherhood);
		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("brotherhood") final Brotherhood brotherhood, final BindingResult binding) {
		ModelAndView result;
		Brotherhood brotherhoodMod = brotherhood;

		try { //name, surname, address, email, title, departure	
			Assert.notNull(brotherhood.getName());
			Assert.isTrue(brotherhood.getName() != "");
			Assert.notNull(brotherhood.getEmail());
			Assert.isTrue(brotherhood.getEmail() != "");
			Assert.notNull(brotherhood.getAddress());
			Assert.isTrue(brotherhood.getAddress() != "");
			Assert.notNull(brotherhood.getTitle());
			Assert.isTrue(brotherhood.getTitle() != "");
			Assert.notNull(brotherhood.getStablishmentDate());
		} catch (final Throwable error) {
			result = this.createEditEditModelAndView(brotherhood, "brotherhood.mandatory");
			return result;
		}

		try {
			brotherhoodMod = this.brotherhoodService.reconstruct(brotherhood, binding);
			if (binding.hasErrors())
				result = this.createEditEditModelAndView(brotherhood);
			else {
				final String vacia = "";

				if (!brotherhoodMod.getEmail().isEmpty() || brotherhoodMod.getEmail() != vacia)
					Assert.isTrue(brotherhoodMod.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || brotherhoodMod.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

				//	Si se introduce un �rea, se comprueba que no ten�a una
				if (brotherhood.getArea() != null) {
					final Brotherhood oldBro = this.brotherhoodService.findOne(brotherhood.getId());
					Assert.isTrue(oldBro.getArea() != null);
				}
				this.brotherhoodService.save(brotherhoodMod);
				result = new ModelAndView("redirect:/welcome/index.do");
			}
		} catch (final Throwable error) {
			if (error.getMessage() == "Wrong email")
				result = this.createEditEditModelAndView(brotherhood, "brotherhood.email.error");
			else
				result = this.createEditEditModelAndView(brotherhood, "brotherhood.comit.error");
			System.out.println(error.getMessage());
		}

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Brotherhood brotherhood) {
		ModelAndView result;

		result = this.createEditEditModelAndView(brotherhood, null);

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Brotherhood brotherhood, final String message) {
		ModelAndView result;
		//		Collection<Box> boxes;
		//		final Collection<SocialProfile> socialProfiles;
		//		final Collection<Endorsement> endorsements;
		//		final Collection<FixUpTask> fixUpTasks;
		UserAccount userAccount;

		//		fixUpTasks = brotherhood.getFixUpTasks();
		//		boxes = brotherhood.getBoxes();
		//		socialProfiles = brotherhood.getSocialProfiles();
		//		endorsements = brotherhood.getEndorsements();
		userAccount = brotherhood.getUserAccount();
		//		 if (socialProfiles.isEmpty())
		//		 * socialProfiles = null;
		//		 * if (endorsements.isEmpty())
		//		 * endorsements = null;
		//if (boxes.isEmpty())
		//	boxes = null;

		Area area = null;
		area = brotherhood.getArea();
		final Collection<Area> areas = this.areaRepository.findAll();

		result = new ModelAndView("brotherhood/edit");
		result.addObject("brotherhood", brotherhood);
		result.addObject("areas", areas);
		//		result.addObject("boxes", boxes);
		//		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);
		//		result.addObject("endorsements", endorsements);
		//		result.addObject("fixUpTasks", fixUpTasks);
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
		Brotherhood brotherhood;

		brotherhood = this.brotherhoodService.findByPrincipal();
		//brotherhood = this.brotherhoodService.findOne(brotherhoodId);
		res = this.createShowModelAndView(brotherhood);
		return res;

	}

	protected ModelAndView createShowModelAndView(final Brotherhood brotherhood) {
		ModelAndView result;

		result = this.createShowModelAndView(brotherhood, null);

		return result;

	}

	protected ModelAndView createShowModelAndView(final Brotherhood brotherhood, final String message) {
		ModelAndView result;
		//		Collection<Box> boxes;
		//		final Collection<SocialProfile> socialProfiles;
		//		final Collection<Endorsement> endorsements;
		//		final Collection<FixUpTask> fixUpTasks;
		UserAccount userAccount;

		//		fixUpTasks = brotherhood.getFixUpTasks();
		//		boxes = brotherhood.getBoxes();
		//		socialProfiles = brotherhood.getSocialProfiles();
		//		endorsements = brotherhood.getEndorsements();
		userAccount = brotherhood.getUserAccount();
		//		 if (socialProfiles.isEmpty())
		//		 * socialProfiles = null;
		//		 * if (endorsements.isEmpty())
		//		 * endorsements = null;
		//if (boxes.isEmpty())
		//	boxes = null;

		result = new ModelAndView("brotherhood/show");
		result.addObject("brotherhood", brotherhood);
		//		result.addObject("boxes", boxes);
		//		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);
		//		result.addObject("endorsements", endorsements);
		//		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////DELETE//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "leave")
	public ModelAndView saveLeave(final Brotherhood brotherhood, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditEditModelAndView(brotherhood);
		else
			try {

				this.brotherhoodService.leave();
				result = new ModelAndView("redirect:../../j_spring_security_logout");
			} catch (final Throwable error) {
				if (error.getMessage() == "Wrong email")
					result = this.createEditEditModelAndView(brotherhood, "brotherhood.email.error");
				else
					result = this.createEditEditModelAndView(brotherhood, "brotherhood.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

}
