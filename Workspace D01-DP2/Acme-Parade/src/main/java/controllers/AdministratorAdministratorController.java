
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
import services.AdministratorService;
import domain.Administrator;
import domain.Box;
import domain.SocialProfile;
import forms.AdministratorForm;

@Controller
@RequestMapping("/administrator/administrator")
public class AdministratorAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;


	// Constructors -----------------------------------------------------------
	public AdministratorAdministratorController() {
		super();
	}

	////////////////////////////
	//////////EDIT//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		final Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		//customer = this.customerService.findOne(customerId);
		res = this.createEditModelAndView(administrator);
		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		try{ //name, surname, address, email, title, departure	
			Assert.notNull(administrator.getName());
			Assert.isTrue(administrator.getName() != "");
			Assert.notNull(administrator.getEmail());
			Assert.isTrue(administrator.getEmail() != "");
			Assert.notNull(administrator.getAddress());
			Assert.isTrue(administrator.getAddress() != "");

		}catch(Throwable error){
			result = this.createEditModelAndView(administrator, "administrator.mandatory");
			return result;
		}
		
		
		final Administrator administratorMod = this.administratorService.reconstruct(administrator, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(administrator);
		else
			try {
				final String vacia = "";
				if (!administratorMod.getEmail().isEmpty() || administratorMod.getEmail() != vacia)
					Assert.isTrue(administratorMod.getEmail().matches("^[A-z0-9]+@$") || administratorMod.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@>$"), "Wrong email");

				this.administratorService.save(administratorMod);
				result = new ModelAndView("redirect:http://localhost:8080/Acme-Madruga");
			} catch (final Throwable error) {
				if (error.getMessage() == "Wrong email")
					result = this.createEditModelAndView(administratorMod, "administrator.email.error");
				else
					result = this.createEditModelAndView(administratorMod, "administrator.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

	protected ModelAndView createEditModelAndView(final Administrator administrator) {
		ModelAndView result;

		result = this.createEditModelAndView(administrator, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Administrator administrator, final String message) {
		ModelAndView result;
		Collection<Box> boxes;
		final Collection<SocialProfile> socialProfiles;
		UserAccount userAccount;

		boxes = administrator.getBoxes();
		socialProfiles = administrator.getSocialProfiles();
		userAccount = administrator.getUserAccount();

		result = new ModelAndView("administrator/edit");
		result.addObject("administrator", administrator);
		result.addObject("boxes", boxes);
		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////CREATE////////////
	////////////////////////////
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;

		Authority authority;
		Collection<Authority> authorities;
		UserAccount userAccount;
		userAccount = new UserAccount();
		authorities = userAccount.getAuthorities();
		authority = new Authority();
		authority.setAuthority(Authority.ADMIN);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		final AdministratorForm adminForm = new AdministratorForm();
		adminForm.setUserAccount(userAccount);
		res = this.createCreateModelAndView(adminForm);

		//return res;

		//		administrator = this.administratorService.create();
		//		res = this.createCreateModelAndView(administrator);
		return res;

	}

	protected ModelAndView createCreateModelAndView(final AdministratorForm administratorForm) {
		ModelAndView result;

		result = this.createCreateModelAndView(administratorForm, null);

		return result;

	}

	protected ModelAndView createCreateModelAndView(final AdministratorForm administratorForm, final String message) {
		ModelAndView result;
		result = new ModelAndView("administrator/create");
		result.addObject("administratorForm", administratorForm);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final AdministratorForm administratorForm, final BindingResult binding) {
		ModelAndView result;

		try { //name, surname, address, email, title, departure	
			;
			Assert.notNull(administratorForm.getEmail());
			Assert.isTrue(administratorForm.getEmail() != "");

		} catch (final Throwable error) {
			result = this.createCreateModelAndView(administratorForm, "administrator.mandatory");
			return result;
		}
		
		if (!administratorForm.isConditionsAccepted())
			result = this.createCreateModelAndView(administratorForm, "member.conditionsError");
		else {
			final Administrator administrator = this.administratorService.reconstruct(administratorForm, binding);
			if (binding.hasErrors())
				result = this.createCreateModelAndView(administratorForm);
			else
				try {
					final String vacia = "";
					if (!administrator.getEmail().isEmpty() || administrator.getEmail() != vacia)
						Assert.isTrue(administrator.getEmail().matches("^[A-z0-9]+@$") || administrator.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@>$"), "Wrong email");

					this.administratorService.save(administrator);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (final Throwable error) {
					if (error.getMessage() == "Wrong email")
						result = this.createCreateModelAndView(administratorForm, "administrator.email.error");
					else
						result = this.createCreateModelAndView(administratorForm, "administrator.comit.error");
					System.out.println(error.getMessage());
				}
		}
		return result;

	}

	////////////////////////////
	//////////DELETE////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "leave")
	public ModelAndView saveLeave(final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(administrator);
		else
			try {

				this.administratorService.leave();
				result = new ModelAndView("redirect:../../j_spring_security_logout");
			} catch (final Throwable error) {
				if (error.getMessage() == "Wrong email")
					result = this.createEditModelAndView(administrator, "brotherhood.email.error");
				else
					result = this.createEditModelAndView(administrator, "brotherhood.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

	////////////////////////////
	///////////SHOW/////////////
	////////////////////////////

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView res;
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		//brotherhood = this.brotherhoodService.findOne(brotherhoodId);
		res = this.createShowModelAndView(administrator);
		return res;

	}

	protected ModelAndView createShowModelAndView(final Administrator administrator) {
		ModelAndView result;

		result = this.createShowModelAndView(administrator, null);

		return result;

	}

	protected ModelAndView createShowModelAndView(final Administrator administrator, final String message) {
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
		userAccount = administrator.getUserAccount();
		//		 if (socialProfiles.isEmpty())
		//		 * socialProfiles = null;
		//		 * if (endorsements.isEmpty())
		//		 * endorsements = null;
		//if (boxes.isEmpty())
		//	boxes = null;

		result = new ModelAndView("administrator/show");
		result.addObject("administrator", administrator);
		//		result.addObject("boxes", boxes);
		//		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);
		//		result.addObject("endorsements", endorsements);
		//		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("userAccount", userAccount);
		return result;
	}

}
