
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.EnrolementRepository;
import domain.Brotherhood;
import domain.Enrolement;
import domain.Member;

@Service
@Transactional
public class EnrolementService {

	@Autowired
	public EnrolementRepository	enrolementRepository;

	@Autowired
	public BrotherhoodService	brotherhoodService;

	@Autowired
	public MemberService		memberService;
	
	@Autowired
	private Validator			validator;


	//Constructor
	public EnrolementService() {
		super();
	}

	public Enrolement findEnrolementByIds(final Brotherhood brotherhood, final Member member) {

		return this.enrolementRepository.findEnrolementByIds(brotherhood.getId(), member.getId());
	}

	public Enrolement save(final Enrolement enrolement) {
		Enrolement res;

		final Member m = this.memberService.findOnePrincipal();

		if (enrolement.getId() == 0)
			if (this.enrolementRepository.existEnrolement(m.getId(), enrolement.getBrotherhood().getId()) != null) {
				final Enrolement e = this.enrolementRepository.existEnrolement(m.getId(), enrolement.getBrotherhood().getId());
				//Assert.isTrue(m.getEnrolements().contains(enrolement));
				return this.updateEnrol(e);
			}

		//Assert.isTrue(enrolement.getStatus().toString() == "PENDING");
		res = this.enrolementRepository.save(enrolement);

		if (enrolement.getId() == 0) {
			final List<Enrolement> enrolementsM = new ArrayList<>(m.getEnrolements());
			enrolementsM.add(res);
			m.setEnrolements(enrolementsM);

			this.memberService.save(m);

			final Brotherhood b = new Brotherhood();
			b.setId(res.getBrotherhood().getId());

			final Brotherhood updaB = this.brotherhoodService.findOne(b);
			final List<Enrolement> le = new ArrayList<>(updaB.getEnrolements());
			le.add(res);

			this.brotherhoodService.saveDirectly(updaB);
		}
		return res;
	}
	public Enrolement updateEnrol(final Enrolement e) {
		if (!e.getStatus().contains("APPROVED")) {
			e.setEnrolMoment(Calendar.getInstance().getTime());
			e.setDropOutMoment(null);
			e.setStatus("PENDING");
		}
		final Enrolement res = this.enrolementRepository.save(e);
		return res;
	}
	public Collection<Enrolement> findEnrolementsByMemberId(final Member member) {
		return this.enrolementRepository.findEnrolementsByMemberId(member.getId());
	}

	public void deleteEnrolement(final Enrolement enrolement) {
		Assert.isTrue(enrolement.getStatus() == "PENDING");
		final Brotherhood b = enrolement.getBrotherhood();
		final List<Enrolement> lb = new ArrayList<>(b.getEnrolements());
		lb.remove(enrolement);
		b.setEnrolements(lb);
		this.brotherhoodService.save(b);
		this.enrolementRepository.delete(enrolement);
	}

	public void leaveBrotherhood(final Enrolement e) {
		e.setDropOutMoment(Calendar.getInstance().getTime());
		e.setStatus("EXPELLED");
		final Member m = this.memberService.memberByEnrolemetId(e.getId());
		this.brotherhoodService.deleteRequests(m);
		e.setPosition(null);
		this.saveDirectly(e);
	}
	public Enrolement create() {
		Assert.isTrue(this.memberService.findOnePrincipal() != null);
		final Enrolement res = new Enrolement();
		res.setId(0);
		res.setEnrolMoment(Calendar.getInstance().getTime());
		res.setStatus("PENDING");

		return res;
	}

	public Enrolement saveDirectly(final Enrolement e) {
		return this.enrolementRepository.save(e);
	}
	public Collection<Enrolement> enrolementsPending(final Integer brotherhoodId) {

		return this.enrolementRepository.enrolementsPending(brotherhoodId);
	}

	public void enrolMember(final Enrolement e) {
		e.setStatus("APPROVED");
		this.enrolementRepository.save(e);

	}

	public Enrolement findOne(final Enrolement e) {
		// TODO Auto-generated method stub
		return this.enrolementRepository.findOne(e.getId());
	}

	public void rejectMember(final Enrolement enrolement) {
		// TODO Auto-generated method stub
		enrolement.setStatus("REJECTED");

		enrolement.setDropOutMoment(Calendar.getInstance().getTime());
		this.enrolementRepository.save(enrolement);

	}

	public Collection<Enrolement> findAll() {
		return this.enrolementRepository.findAll();
	}

	public Enrolement reconstruct(final Enrolement e, final BindingResult binding) {
		Enrolement res;

		if (e.getId() == 0)
			res = e;
		else {
			res = this.findOne(e);
			res.setBrotherhood(e.getBrotherhood());
		}
		this.validator.validate(res, binding);
		return res;
	}

	public Enrolement reconstruct2(final Enrolement e, final BindingResult binding) {
		Enrolement res;

		if (e.getId() == 0)
			res = e;
		else {
			res = this.findOne(e);
			res.setPosition(e.getPosition());
		}
		this.validator.validate(res, binding);
		return res;
	}
}
