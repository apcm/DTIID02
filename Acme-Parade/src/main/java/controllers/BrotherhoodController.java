
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

import repositories.AreaRepository;
import security.Authority;
import security.UserAccount;
import services.BrotherhoodService;
import domain.Area;
import domain.Brotherhood;
import domain.InceptionRecord;
import domain.LegalRecord;
import domain.LinkRecord;
import domain.PeriodRecord;
import forms.BrotherhoodForm;

@Controller
@RequestMapping("/brotherhood")
public class BrotherhoodController extends AbstractController {

	@Autowired
	BrotherhoodService	brotherhoodService;

	@Autowired
	AreaRepository		areaRepository;


	// Constructors -----------------------------------------------------------

	public BrotherhoodController() {
		super();
	}

	// Edition ------------------------------
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;

		Authority authority;
		Collection<Authority> authorities;
		UserAccount userAccount;
		userAccount = new UserAccount();
		authorities = userAccount.getAuthorities();
		authority = new Authority();
		authority.setAuthority(Authority.BROTHERHOOD);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		final BrotherhoodForm brotherhoodForm = new BrotherhoodForm();
		brotherhoodForm.setUserAccount(userAccount);
		res = this.createEditModelAndView(brotherhoodForm);

		//brotherhood = this.brotherhoodService.create();
		//res = this.createEditModelAndView(brotherhood);
		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final BrotherhoodForm brotherhoodForm, final BindingResult binding) {
		ModelAndView result;

		try { //name, surname, address, email, title, departure	
			Assert.notNull(brotherhoodForm.getEmail());
			Assert.isTrue(brotherhoodForm.getEmail() != "");

		} catch (final Throwable error) {
			result = this.createEditModelAndView(brotherhoodForm, "brotherhood.mandatory");
			return result;
		}

		if (!brotherhoodForm.isConditionsAccepted())
			result = this.createEditModelAndView(brotherhoodForm, "brotherhood.conditionsError");
		//result.addObject("conditionsError", true);
		else {
			final Brotherhood brotherhood = this.brotherhoodService.reconstruct(brotherhoodForm, binding);
			if (binding.hasErrors())
				result = this.createEditModelAndView(brotherhoodForm);
			else
				try {
					final String vacia = "";
					if (!brotherhood.getEmail().isEmpty() || brotherhood.getEmail() != vacia)
						Assert.isTrue(brotherhood.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || brotherhood.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

					this.brotherhoodService.save(brotherhood);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (final Throwable error) {
					if (error.getMessage() == "Wrong email")
						result = this.createEditModelAndView(brotherhoodForm, "brotherhood.email.error");
					else
						result = this.createEditModelAndView(brotherhoodForm, "brotherhood.comit.error");
					System.out.println(error.getMessage());
				}
		}

		return result;

	}

	// Ancillary methods ------------------------------
	protected ModelAndView createEditModelAndView(final BrotherhoodForm brotherhoodForm) {
		ModelAndView result;

		result = this.createEditModelAndView(brotherhoodForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final BrotherhoodForm brotherhoodForm, final String message) {
		ModelAndView result;

		final Collection<Area> areas = this.areaRepository.findAll();

		result = new ModelAndView("brotherhood/register");
		result.addObject("areas", areas);
		result.addObject("brotherhoodForm", brotherhoodForm);
		result.addObject("message", message);

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;

		final Collection<Brotherhood> brotherhoods = this.brotherhoodService.findAll();

		res = new ModelAndView("brotherhood/list");
		res.addObject("requestURI", "brotherhood/list.do");
		res.addObject("brotherhoods", brotherhoods);
		return res;
	}

	@RequestMapping(value = "/showRecords", method = RequestMethod.GET)
	public ModelAndView showRecords(@RequestParam final int brotherhoodId) {
		ModelAndView res;
		Brotherhood brotherhood;

		//brotherhood = this.brotherhoodService.findByPrincipal();
		brotherhood = this.brotherhoodService.findOne(brotherhoodId);
		res = this.createShowRecordsModelAndView(brotherhood);
		return res;

	}

	protected ModelAndView createShowRecordsModelAndView(final Brotherhood brotherhood) {
		ModelAndView result;

		result = this.createShowRecordsModelAndView(brotherhood, null);

		return result;

	}

	protected ModelAndView createShowRecordsModelAndView(final Brotherhood brotherhood, final String message) {
		final ModelAndView result;

		UserAccount userAccount;
		userAccount = brotherhood.getUserAccount();

		final Collection<LegalRecord> legalRecords = brotherhood.getLegalRecords();
		final Collection<PeriodRecord> periodRecords = brotherhood.getPeriodRecords();
		final InceptionRecord inceptionRecord = brotherhood.getInceptionRecord();
		final Collection<LinkRecord> linkRecords = brotherhood.getLinkRecords();

		result = new ModelAndView("brotherhood/showRecords");
		result.addObject("brotherhood", brotherhood);
		result.addObject("legalRecords", legalRecords);
		result.addObject("periodRecords", periodRecords);
		result.addObject("inceptionRecord", inceptionRecord);
		result.addObject("linkRecords", linkRecords);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}
}
