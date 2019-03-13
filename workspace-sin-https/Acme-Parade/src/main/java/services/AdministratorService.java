
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Actor;
import domain.Administrator;
import domain.Box;
import domain.Customisation;
import domain.Message;
import domain.SocialProfile;
import forms.AdministratorForm;

@Service
@Transactional
public class AdministratorService {

	// Repository
	@Autowired
	private AdministratorRepository	administratorRepository;

	// Services

	@Autowired
	public ActorService				actorService;

	@Autowired
	public FinderService			finderService;

	@Autowired
	public CustomisationService		customisationService;

	@Autowired
	public SocialProfileService		socialProfileService;


	public AdministratorService() {
		super();
	}

	// Simple CRUD

	// 8.1
	public Administrator create() {
		// User can't be logged to register
		//		final Authority a = new Authority();
		//		final Authority b = new Authority();
		//		final Authority c = new Authority();
		//		final Authority d = new Authority();
		//		final Authority e = new Authority();
		//		final UserAccount user = LoginService.getPrincipal();
		//		a.setAuthority(Authority.ADMIN);
		//		b.setAuthority(Authority.HANDYWORKER);
		//		c.setAuthority(Authority.CUSTOMER);
		//		d.setAuthority(Authority.REFEREE);
		//		e.setAuthority(Authority.SPONSOR);
		//		Assert.isTrue(!(user.getAuthorities().contains(a) || user.getAuthorities().contains(b) || user.getAuthorities().contains(c) || user.getAuthorities().contains(d) || user.getAuthorities().contains(e)));

		Administrator result;
		result = new Administrator();

		// Actor
		//		final Box trash = new Box();
		//		final Box out = new Box();
		//		final Box spam = new Box();
		//		final Box in = new Box();
		//		trash.setName("trash");
		//		in.setName("in");
		//		out.setName("out");
		//		spam.setName("spam");
		//		out.setPredefined(true);
		//		in.setPredefined(true);
		//		spam.setPredefined(true);
		//		trash.setPredefined(true);
		final Collection<Box> predefined = new ArrayList<Box>();
		//		predefined.add(in);
		//		predefined.add(out);
		//		predefined.add(spam);
		//		predefined.add(trash);

		//	result.setBoxes(predefined);

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.MEMBER);
		newUser.addAuthority(f);
		result.setUserAccount(newUser);

		result.setSocialProfiles(new ArrayList<SocialProfile>());
		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhoto("");

		// Administrator

		return result;
	}

	// Returns logged administrator
	public Administrator findByPrincipal() {
		Administrator res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	// 11.1
	//	public Administrator findByFixUpTask(final FixUpTask fixUpTask) {
	//		// Logged user must be a handyWorker
	//		final Authority a = new Authority();
	//		final UserAccount user = LoginService.getPrincipal();
	//		a.setAuthority(Authority.HANDYWORKER);
	//		Assert.isTrue(user.getAuthorities().contains(a));
	//
	//		Administrator res;
	//		res = this.administratorRepository.findFixUpTask(fixUpTask.getId());
	//		return res;
	//	}

	// Returns logged administrator from his or her userAccount
	public Administrator findByUserAccount(final UserAccount userAccount) {
		Administrator res;
		Assert.notNull(userAccount);

		res = this.administratorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Collection<Administrator> findAll() {
		return this.administratorRepository.findAll();
	}

	public Administrator save(final Administrator mem) {

		// Restrictions
		Assert.isTrue(mem.getBan() != true);

		if (mem.getId() == 0) {
			Collection<Box> boxes = actorService.createPredefinedBoxes();
			mem.setBoxes(boxes);
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = mem.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = mem.getUserAccount();
			cuenta.setPassword(hash);
			mem.setUserAccount(cuenta);

			//			final Box in1 = new Box();
			//			in1.setName("In");
			//			in1.setPredefined(true);
			//			final Box in = this.boxService.save(in1);
			//
			//			final Collection<Box> boxesPredefined = new ArrayList<Box>();
			//			boxesPredefined.add(in);
			//			cus.setBoxes(boxesPredefined);

		}
		return this.administratorRepository.save(mem);
	}
	public Administrator findOne(final int administratorId) {
		Administrator c;

		Assert.notNull(administratorId);
		Assert.isTrue(administratorId != 0);
		c = this.administratorRepository.findOne(administratorId);

		Assert.notNull(c);
		return c;
	}

	public Administrator reconstruct(final AdministratorForm administratorForm, final BindingResult binding) {
		final Administrator administrator = this.create();

		Assert.isTrue(administratorForm.isConditionsAccepted());
		final Authority adm = new Authority();
		adm.setAuthority(Authority.ADMIN);
		Assert.isTrue(administratorForm.getUserAccount().getAuthorities().contains(adm));
		final Collection<Authority> colAdm = new ArrayList<Authority>();
		final Authority memb = new Authority();
		memb.setAuthority(Authority.ADMIN);
		colAdm.add(memb);
		//Assert.isTrue(memberForm.getUserAccount().getAuthorities() == colMem);
		//Damos valores a los atributos del member que devolveremos con los datos que nos llegan

		administrator.setAddress(administratorForm.getAddress());
		administrator.setEmail(administratorForm.getEmail());
		administrator.setMiddleName(administratorForm.getMiddleName());
		administrator.setName(administratorForm.getName());
		administrator.setPhoneNumber(administratorForm.getPhoneNumber());
		administrator.setPhoto(administratorForm.getPhoto());
		administrator.setSurname(administratorForm.getSurname());
		administrator.setUserAccount(administratorForm.getUserAccount());

		//		member.setFlagSpam(memberForm.isFlagSpam());
		//		member.setPolarityScore(memberForm.getPolarityScore());
		//		member.setBan(memberForm.getBan());

		administrator.setBan(false);

		return administrator;
	}


	@Autowired
	private Validator	validator;


	public Administrator reconstruct(final Administrator administrator, final BindingResult binding) {
		Administrator res;

		//Check authority
		final Authority a = new Authority();
		final UserAccount user = administrator.getUserAccount();
		a.setAuthority(Authority.ADMIN);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1);

		if (administrator.getId() == 0)
			res = administrator;
		else {
			res = this.administratorRepository.findOne(administrator.getId());

			res.setName(administrator.getName());
			res.setEmail(administrator.getEmail());
			res.setMiddleName(administrator.getMiddleName());
			res.setSurname(administrator.getSurname());
			res.setAddress(administrator.getAddress());
			res.setPhoneNumber(administrator.getPhoneNumber());
			res.setPhoto(administrator.getPhoto());

			this.validator.validate(res, binding);
		}
		return res;
	}
	public void calculateScoreApp() {
		Integer score = 0;
		final List<Customisation> cus = new ArrayList<>(this.customisationService.findAll());
		final Customisation cus1 = cus.get(0);
		final List<String> positive = new ArrayList<String>(cus1.getPositiveWords());
		final List<String> negative = new ArrayList<String>(cus1.getNegativeWords());

		final List<Actor> la = new ArrayList<>(this.actorService.findAll());
		Double res1 = null;

		for (final Actor a : la) {
			final List<Box> lb = new ArrayList<>(a.getBoxes());
			for (final Box b : lb)
				if (b.getName().contains("out box")) {
					final List<Message> lm = new ArrayList<>(b.getMessages());
					for (final Message m : lm) {
						final String text = m.getBody();
						for (final String p : positive)
							if (text.contains(p))
								score++;
						for (final String n : negative)
							if (text.contains(n))
								score--;
					}
					break;
				}
			if ((score >= -10 || score <= 10))
				res1 = new Double(score / 10);
			else if (score <= 100 || score >= -100)
				res1 = new Double(score / 100);
			else if (score <= 1000 || score >= -1000)
				res1 = new Double(score / 1000);

			a.setPolarityScore(res1);
		}

	}
	public void leave() {
		final Administrator logAdministrator = this.findByPrincipal();

		logAdministrator.setAddress("Unknown");
		logAdministrator.setBan(true);
		logAdministrator.setEmail("unknown@unknown.com");
		logAdministrator.setMiddleName("Unknown");
		logAdministrator.setName("Unknown");
		logAdministrator.setPhoneNumber("Unknown");
		logAdministrator.setPhoto("http://www.unknown.com");
		logAdministrator.setPolarityScore(0);
		for (final SocialProfile sp : logAdministrator.getSocialProfiles())
			this.socialProfileService.deleteLeave(sp);
		logAdministrator.setSocialProfiles(null);
		logAdministrator.setSurname("Unknown");

		final UserAccount ua = logAdministrator.getUserAccount();
		final String tick1 = TickerGenerator.tickerLeave();
		ua.setUsername("Unknown" + tick1);
		final String pass1 = TickerGenerator.generateTicker();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String pass2 = encoder.encodePassword(pass1, null);
		ua.setPassword(pass2);
		logAdministrator.setUserAccount(ua);
	}
}
