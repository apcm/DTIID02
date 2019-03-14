
package controllers.requests.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MemberService;
import services.ProcessionService;
import services.RequestService;
import controllers.AbstractController;
import domain.Member;
import domain.Parade;
import domain.Request;

@Controller
@RequestMapping("/requests/member")
public class RequestMemberController extends AbstractController {

	@Autowired
	MemberService		memberService;

	@Autowired
	ProcessionService	processionService;

	@Autowired
	RequestService		requestService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Collection<Request> requests;

		final Member member = this.memberService.findOnePrincipal();
		requests = this.requestService.findRequestsByMemberId(member);
		res = new ModelAndView("requests/list");
		res.addObject("requests", requests);
		res.addObject("requestURI", "requests/member/list.do");
		res.addObject("memberView", true);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int requestId) {
		final ModelAndView res;
		final Parade p;
		final Request r;

		p = this.processionService.findByRequestId(requestId);
		final Request r1 = new Request();
		r1.setId(requestId);
		r = this.requestService.findOne(r1);
		this.requestService.checkRequestOwnsMember(r1);

		res = new ModelAndView("requests/show");
		res.addObject("procession", p);
		res.addObject("request", r);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.POST, params = "delete")
	public ModelAndView show(@Valid final Request request) {
		final ModelAndView res;
		final Request r;

		final Request r1 = new Request();
		r1.setId(request.getId());
		r = this.requestService.findOne(r1);
		this.requestService.deleteRequest(r);
		res = new ModelAndView("redirect:list.do");
		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView res;
		final Request r;

		r = this.requestService.create();
		res = this.createEditModelAndView(r);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Request r, final BindingResult binding) {
		ModelAndView res;

		r = this.requestService.reconstructMember(r, binding);
		if (binding.hasErrors())
			res = this.createEditModelAndView(r);

		try {

			this.requestService.save(r);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(r, "error.request");
		}

		return res;
	}
	private ModelAndView createEditModelAndView(final Request r) {
		ModelAndView res;
		res = this.createEditModelAndView(r, null);

		return res;
	}

	private ModelAndView createEditModelAndView(final Request r, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("requests/edit");
		final Member member = this.memberService.findOnePrincipal();
		final List<Parade> lp = new ArrayList<>(this.processionService.findAllFinalModeRequests());
		final List<Parade> lp2 = this.processionService.findByMemberId(member);

		lp.retainAll(lp2);

		res.addObject("request", r);
		res.addObject("message", messageCode);
		res.addObject("listProcessions", lp);
		res.addObject("memberView", true);
		res.addObject("formAction", "requests/member/edit.do");
		res.addObject("formBack", "requests/member/list.do");

		return res;
	}

}
