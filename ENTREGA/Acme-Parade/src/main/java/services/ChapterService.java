
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AreaRepository;
import repositories.BrotherhoodRepository;
import repositories.ChapterRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Actor;
import domain.Box;
import domain.Chapter;
import domain.Customisation;
import domain.SocialProfile;
import forms.ChapterForm;

@Service
@Transactional
public class ChapterService {

	@Autowired
	public BrotherhoodRepository	brotherhoodRepository;

	@Autowired
	public ActorService				actorService;

	@Autowired
	public CustomisationService		customisationService;

	@Autowired
	public SocialProfileService		socialProfileService;

	@Autowired
	public ChapterRepository		chapterRepository;

	@Autowired
	public AreaRepository			areaRepository;

	@Autowired
	public AreaService				areaService;


	//Constructor
	public ChapterService() {
		super();
	}

	public Chapter create() {
		Chapter result;
		result = new Chapter();

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.BROTHERHOOD);
		newUser.addAuthority(f);
		result.setUserAccount(newUser);

		result.setSocialProfiles(new ArrayList<SocialProfile>());
		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhoto("");

		// Chapter

		final Collection<SocialProfile> socPros = new ArrayList<SocialProfile>();
		result.setSocialProfiles(socPros);

		return result;
	}

	public Chapter findByPrincipal() {
		Chapter res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public Chapter findOnePrincipal() {
		final Actor a = this.actorService.findByPrincipal();
		return this.chapterRepository.findOne(a.getId());
	}

	public Chapter findOne(final Chapter chapter) {
		return this.chapterRepository.findOne(chapter.getId());
	}

	public Collection<Chapter> findAll() {
		return this.chapterRepository.findAll();
	}

	public Chapter save(final Chapter chapter) {
		Assert.notNull(chapter);
		Assert.isTrue(!chapter.getBan());
		//		// Logged user must be a chapter
		//		final Authority a = new Authority();
		//		final UserAccount user = LoginService.getPrincipal();
		//		a.setAuthority(Authority.BROTHERHOOD);
		//		Assert.isTrue(user.getAuthorities().contains(a));

		final String pnumber = chapter.getPhoneNumber();
		final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		final String cc = cus.getPhoneNumberCountryCode();
		if (pnumber.matches("^[0-9]{4,}$"))
			chapter.setPhoneNumber(cc.concat(pnumber));

		//Area seleccionada está disponible
		//	final Collection<Area> notAssigned = this.areaService.findNotAssigned();
		//	if (chapter.getArea() != null)
		//		Assert.isTrue(notAssigned.contains(chapter.getArea()));

		if (chapter.getId() != 0) {
			Assert.isTrue(this.actorService.checkChapter());

			// Modified chapter must be logged chapter
			final Chapter logChapter;
			logChapter = this.findByPrincipal();
			Assert.notNull(logChapter);
			Assert.notNull(logChapter.getId());

			//No se ha modificado su área
			final Chapter oldChapter = this.chapterRepository.findOne(chapter.getId());
			if (chapter.getArea() != null)
				Assert.isTrue(chapter.getArea() == oldChapter.getArea());

		} else {

			final Collection<Box> boxes = this.actorService.createPredefinedBoxes();
			chapter.setBoxes(boxes);
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = chapter.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = chapter.getUserAccount();
			cuenta.setPassword(hash);
			chapter.setUserAccount(cuenta);
		}
		// Restrictions
		Chapter res;

		res = this.chapterRepository.save(chapter);
		return res;
	}
	public Chapter findByUserAccount(final UserAccount userAccount) {
		Chapter res;
		Assert.notNull(userAccount);

		res = this.chapterRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Chapter saveScore(final Chapter chapter, final Customisation custo) {
		//		// Logged user must be an administrator
		//		final Authority a = new Authority();
		//		final UserAccount user = LoginService.getPrincipal();
		//		a.setAuthority(Authority.ADMIN);
		//		Assert.isTrue(user.getAuthorities().contains(a));

		Assert.isTrue(this.actorService.checkChapter());

		Assert.notNull(chapter);

		final Integer score = 0;

		final List<String> positive = new ArrayList<String>(custo.getPositiveWords());
		final List<String> negative = new ArrayList<String>(custo.getNegativeWords());
		//		final List<Endorsement> endorsements = new ArrayList<Endorsement>(chapter.getEndorsements());
		//
		//		for (final Endorsement e : endorsements) {
		//			final String text = e.getComment();
		//			for (final String p : positive)
		//				if (text.contains(p))
		//					score++;
		//			for (final String n : negative)
		//				if (text.contains(n))
		//					score--;
		//		}
		//		chapter.setScore(score);
		return this.chapterRepository.save(chapter);
	}

	public Chapter findOne(final int chapterId) {
		Chapter c;

		Assert.notNull(chapterId);
		Assert.isTrue(chapterId != 0);
		c = this.chapterRepository.findOne(chapterId);

		Assert.notNull(c);
		return c;
	}

	public Chapter reconstruct(final ChapterForm chapterForm, final BindingResult binding) {
		final Chapter chapter = this.create();

		Assert.isTrue(chapterForm.isConditionsAccepted());
		final Authority bro = new Authority();
		bro.setAuthority(Authority.CHAPTER);
		Assert.isTrue(chapterForm.getUserAccount().getAuthorities().contains(bro));
		final Collection<Authority> colMem = new ArrayList<Authority>();
		colMem.add(bro);
		//Assert.isTrue(chapterForm.getUserAccount().getAuthorities() == colMem);
		//Damos valores a los atributos de la hermandad con los datos que nos llegan

		chapter.setAddress(chapterForm.getAddress());
		chapter.setEmail(chapterForm.getEmail());
		chapter.setMiddleName(chapterForm.getMiddleName());
		chapter.setName(chapterForm.getName());
		chapter.setPhoneNumber(chapterForm.getPhoneNumber());
		chapter.setPhoto(chapterForm.getPhoto());
		chapter.setSurname(chapterForm.getSurname());
		chapter.setUserAccount(chapterForm.getUserAccount());

		chapter.setArea(chapterForm.getArea());
		chapter.setTitle(chapterForm.getTitle());
		chapter.setBan(false);

		this.validator.validate(chapter, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		chapter.setBan(false);

		return chapter;
	}


	@Autowired
	private Validator	validator;


	public Chapter reconstruct(final Chapter chapter, final BindingResult binding) {
		Chapter res;

		//Check authority
		final Authority a = new Authority();
		final UserAccount user = chapter.getUserAccount();
		a.setAuthority(Authority.CHAPTER);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1);

		if (chapter.getId() == 0)
			res = chapter;
		else
			res = this.chapterRepository.findOne(chapter.getId());

		res.setName(chapter.getName());
		res.setEmail(chapter.getEmail());
		res.setMiddleName(chapter.getMiddleName());
		res.setSurname(chapter.getSurname());
		res.setAddress(chapter.getAddress());
		res.setPhoneNumber(chapter.getPhoneNumber());
		res.setPhoto(chapter.getPhoto());
		if (res.getArea() == null)
			res.setArea(chapter.getArea());

		this.validator.validate(res, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public Chapter reconstructArea(final Chapter b, final BindingResult binding) {
		Chapter result;

		if (b.getId() == 0)
			result = b;
		else {
			result = this.chapterRepository.findOne(b.getId());
			if (result.getArea() == null)
				result.setArea(b.getArea());
			this.validator.validate(result, binding);
		}

		return result;
	}

	public void leave() {
		final Chapter logChapter = this.findByPrincipal();

		logChapter.setAddress("Unknown");
		logChapter.setBan(true);
		logChapter.setEmail("unknown@unknown.com");
		logChapter.setMiddleName("Unknown");
		logChapter.setName("Unknown");
		logChapter.setPhoneNumber("Unknown");
		logChapter.setPhoto("http://www.unknown.com");
		logChapter.setPolarityScore(0);
		for (final SocialProfile sp : logChapter.getSocialProfiles())
			this.socialProfileService.deleteLeave(sp);
		logChapter.setSocialProfiles(null);
		logChapter.setSurname("Unknown");

		logChapter.setArea(null);
		logChapter.setTitle("Unknown");

		final UserAccount ua = logChapter.getUserAccount();
		final String tick1 = TickerGenerator.tickerLeave();
		ua.setUsername("Unknown" + tick1);
		final String pass1 = TickerGenerator.generateTicker();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String pass2 = encoder.encodePassword(pass1, null);
		ua.setPassword(pass2);
		logChapter.setUserAccount(ua);
	}

}
