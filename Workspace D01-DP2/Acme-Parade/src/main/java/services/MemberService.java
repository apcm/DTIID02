
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MemberRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Actor;
import domain.Box;
import domain.Brotherhood;
import domain.Customisation;
import domain.Enrolement;
import domain.Finder;
import domain.Member;
import domain.SocialProfile;
import forms.MemberForm;

@Service
@Transactional
public class MemberService {

	@Autowired
	public MemberRepository		memberRepository;

	@Autowired
	public AdministratorService	administratorService;

	@Autowired
	public ActorService			actorService;

	@Autowired
	public FinderService		finderService;

	@Autowired
	public CustomisationService	customisationService;

	@Autowired
	public SocialProfileService	socialProfileService;


	//Constructor
	public MemberService() {
		super();
	}
	public Member create() {
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

		Member result;
		result = new Member();

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

		// Member

		result.setFinder(new Finder());

		return result;
	}

	public Collection<Member> membersByBrotherhood(final Brotherhood brotherhood) {
		return this.memberRepository.membersByBrotherhood(brotherhood.getId());
	}
	//Simple CRUD
	public Member findOne(final Member member) {
		return this.memberRepository.findOne(member.getId());
	}

	public Member findOnePrincipal() {
		final Authority a1 = new Authority();
		a1.setAuthority(Authority.MEMBER);

		final Actor a = this.actorService.findByPrincipal();

		Assert.isTrue(a.getUserAccount().getAuthorities().contains(a1));
		final Member m = new Member();
		m.setId(a.getId());
		final Member res = this.findOne(m);

		return res;
	}

	// Returns logged member
	public Member findByPrincipal() {
		Member res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public Member findByUserAccount(final UserAccount userAccount) {
		Member res;
		Assert.notNull(userAccount);

		res = this.memberRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Collection<Member> findAll() {
		return this.memberRepository.findAll();
	}

	public Member save(final Member mem) {

		// Restrictions
		Assert.isTrue(mem.getBan() != true);

		final String pnumber = mem.getPhoneNumber();
		final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		final String cc = cus.getPhoneNumberCountryCode();
		if (pnumber.matches("^[0-9]{4,}$"))
			mem.setPhoneNumber(cc.concat(pnumber));

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
			final Finder f = new Finder();
			f.setMoment(new Date());
			final Finder fin = this.finderService.save(f);
			mem.setFinder(fin);
		}
		return this.memberRepository.save(mem);
	}
	public Member memberByEnrolemetId(final Integer enrolementId) {
		return this.memberRepository.memberByEnrolementId(enrolementId);
	}

	public List<Member> membersByEnrolemetId(final Collection<Enrolement> enrolements) {
		// TODO Auto-generated method stub
		final List<Member> res = new ArrayList<>();
		for (final Enrolement e : enrolements)
			res.add(this.memberByEnrolemetId(e.getId()));
		return res;
	}

	public Member findOne(final int memberId) {
		Member c;

		Assert.notNull(memberId);
		Assert.isTrue(memberId != 0);
		c = this.memberRepository.findOne(memberId);

		Assert.notNull(c);
		return c;
	}

	public Member findByRequestId(final int requestId) {
		return this.memberRepository.findByRequestId(requestId);
	}

	public Member reconstruct(final MemberForm memberForm, final BindingResult binding) {
		final Member member = this.create();

		Assert.isTrue(memberForm.isConditionsAccepted());
		final Authority mem = new Authority();
		mem.setAuthority(Authority.MEMBER);
		Assert.isTrue(memberForm.getUserAccount().getAuthorities().contains(mem));
		final Collection<Authority> colMem = new ArrayList<Authority>();
		final Authority memb = new Authority();
		memb.setAuthority(Authority.MEMBER);
		colMem.add(memb);
		//Assert.isTrue(memberForm.getUserAccount().getAuthorities() == colMem);
		//Damos valores a los atributos del member que devolveremos con los datos que nos llegan

		member.setAddress(memberForm.getAddress());
		member.setEmail(memberForm.getEmail());
		member.setMiddleName(memberForm.getMiddleName());
		member.setName(memberForm.getName());
		member.setPhoneNumber(memberForm.getPhoneNumber());
		member.setPhoto(memberForm.getPhoto());
		member.setSurname(memberForm.getSurname());
		member.setUserAccount(memberForm.getUserAccount());

		//		member.setFlagSpam(memberForm.isFlagSpam());
		//		member.setPolarityScore(memberForm.getPolarityScore());
		//		member.setBan(memberForm.getBan());

		member.setBan(false);
		member.setFinder(new Finder());

		return member;
	}


	@Autowired
	private Validator	validator;


	public Member reconstruct(final Member member, final BindingResult binding) {
		Member res;

		//Check authority
		final Authority a = new Authority();
		final UserAccount user = member.getUserAccount();
		a.setAuthority(Authority.MEMBER);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1);

		if (member.getId() == 0)
			res = member;
		else {
			res = this.memberRepository.findOne(member.getId());
			//			res.setBan(member.getBan());
			//			res.setFlagSpam(member.isFlagSpam());
			//			res.setBoxes(member.getBoxes());
			//			res.setEnrolements(member.getEnrolements());
			//			res.setRequests(member.getRequests());
			//			res.setSocialProfiles(member.getSocialProfiles());
			//res.setUserAccount(member.getUserAccount());
			res.setName(member.getName());
			res.setEmail(member.getEmail());
			res.setMiddleName(member.getMiddleName());
			res.setSurname(member.getSurname());
			res.setAddress(member.getAddress());
			res.setPhoneNumber(member.getPhoneNumber());
			res.setPhoto(member.getPhoto());

			System.out.println("debug test");

			this.validator.validate(res, binding);
		}
		return res;
	}

	public void leave() {
		final Member logMember = this.findByPrincipal();

		logMember.setAddress("Unknown");
		logMember.setBan(true);
		logMember.setEmail("unknown@unknown.com");
		logMember.setMiddleName("Unknown");
		logMember.setName("Unknown");
		logMember.setPhoneNumber("Unknown");
		logMember.setPhoto("http://www.unknown.com");
		logMember.setPolarityScore(0);
		for (final SocialProfile sp : logMember.getSocialProfiles())
			this.socialProfileService.deleteLeave(sp);
		logMember.setSocialProfiles(null);
		logMember.setSurname("Unknown");
		final UserAccount ua = logMember.getUserAccount();
		final String tick1 = TickerGenerator.tickerLeave();
		ua.setUsername("Unknown" + tick1);
		final String pass1 = TickerGenerator.generateTicker();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String pass2 = encoder.encodePassword(pass1, null);
		ua.setPassword(pass2);
		logMember.setUserAccount(ua);
	}
}
