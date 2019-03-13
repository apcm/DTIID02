
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.RequestRepository;
import domain.Brotherhood;
import domain.Member;
import domain.Procession;
import domain.Request;

@Service
@Transactional
public class RequestService {

	@Autowired
	public RequestRepository	requestRepository;

	@Autowired
	public ProcessionService	processionService;

	@Autowired
	public BrotherhoodService	brotherhoodService;

	@Autowired
	public MemberService		memberService;
	
	@Autowired
	private Validator			validator;


	public Collection<Request> findRequestsByMemberId(final Member member) {

		return this.requestRepository.findRequestByMemberId(member.getId());
	}

	public Request create() {
		final Request res = new Request();
		res.setId(0);
		res.setStatus("PENDING");
		return res;
	}

	public Request save(final Request r) {
		Request res;

		final Member m = this.memberService.findOnePrincipal();
		if (this.requestRepository.existRequest(m.getId(), r.getProcession().getId()) != null) {
			final Request r1 = this.requestRepository.existRequest(m.getId(), r.getProcession().getId());
			return this.updateRequest(r1);
		}
		Assert.isTrue(r.getStatus().contains("PENDING"));
		res = this.requestRepository.save(r);

		if (r.getId() == 0) {
			final List<Request> requestsM = new ArrayList<>(m.getRequests());
			requestsM.add(res);
			m.setRequests(requestsM);

			this.memberService.save(m);
		}
		return res;

	}

	private Request updateRequest(final Request r1) {
		Request res = this.requestRepository.save(r1);
		if (res.getStatus().contains("REJECTED")) {
			final Request r = new Request();
			r.setProcession(res.getProcession());
			r.setId(res.getId());
			r.setVersion(res.getVersion());
			r.setStatus("PENDING");
			res = this.requestRepository.save(r);
		}

		return res;
	}
	public Request findOne(final Request r1) {
		return this.requestRepository.findOne(r1.getId());
	}

	public void deleteRequest(final Request r) {
		final Request res = new Request();
		res.setId(r.getId());
		res.setVersion(r.getVersion());
		res.setProcession(r.getProcession());
		res.setStatus("REJECTED");
		this.requestRepository.save(res);

	}

	public Collection<Request> findByBrotherhood(final Procession p) {
		return this.requestRepository.findRequestByProcessionId(p.getId());
	}

	public Collection<Request> findByProcessionId(final int processionId) {
		return this.requestRepository.findRequestByProcessionId(processionId);
	}

	public Collection<Integer> suggestPosition(final Procession p) {
		final List<Integer> li = new ArrayList<>();
		li.add(0);
		li.add(0);
		for (int i = 0; i < 1000; i++) {
			li.set(0, i);
			for (int j = 0; j <= 15; j++) {
				li.set(1, j);
				if (this.checkPosition(li, p) == true)
					return li;
			}
		}
		return li;
	}

	public Boolean checkPosition(final Collection<Integer> li, final Procession p) {
		final List<Integer> li1 = new ArrayList<>(li);
		final Integer row = li1.get(0);
		final Integer column = li1.get(1);
		Boolean res;

		if (this.requestRepository.checkPosition(row, column, p.getId()) != null)
			res = false;
		else
			res = true;
		return res;
	}

	public void checkRequestOwnsBrotherhood(final Request r) {
		final Brotherhood b = this.brotherhoodService.findOnePrincipal();
		Assert.isTrue(r.getProcession().getBrotherhood().getId() == b.getId());
	}

	public void checkPositionBeforeSave(final Request r) {
		Assert.isTrue(r.getColumnPosition() >= 0);
		Assert.isTrue(r.getRowPosition() >= 0);

	}

	public void checkRequestOwnsMember(final Request r1) {
		final Member m = this.memberService.findOnePrincipal();
		Assert.isTrue(m.getRequests().contains(r1));

	}

	public Request saveDirectly(final Request r) {
		if (r.getStatus().contains("REJECTED"))
			Assert.isTrue(r.getRejectReason() != "");
		return this.requestRepository.save(r);
	}
public Request reconstructMember(final Request r, final BindingResult binding) {
		Request res;

		if (r.getId() == 0)
			res = r;
		else {
			res = this.findOne(r);
			res.setProcession(r.getProcession());

		}
		this.validator.validate(res, binding);
		return res;
	}

	public Request reconstructBrotherhood(final Request r, final BindingResult binding) {
		Request res;

		if (r.getId() == 0)
			res = r;
		else {
			res = this.findOne(r);

			if (r.getRowPosition() != 0 && r.getColumnPosition() != 0)
				res.setStatus("APPROVED");
			else if (r.getRejectReason() != null) {
				res.setStatus("REJECTED");
				if (r.getRejectReason().length() == 0)
					res.setRejectReason("We don't have positions available. No tenemos posiciones disponibles");
			} else
				res.setStatus("PENDING");

			if (res.getStatus().contains("APPROVED")) {
				res.setRowPosition(r.getRowPosition());
				res.setColumnPosition(r.getColumnPosition());
			} else if (res.getStatus().contains("REJECTED") && r.getRejectReason().length() != 0)
				res.setRejectReason(r.getRejectReason());
		}
		this.validator.validate(res, binding);

		return res;
	}
	public Boolean checkPosition(final Request r) {
		Boolean res = false;
		if (this.requestRepository.checkPosition(r.getRowPosition(), r.getColumnPosition(), r.getProcession().getId()) != null)
			res = true;

		return res;
	}
}
