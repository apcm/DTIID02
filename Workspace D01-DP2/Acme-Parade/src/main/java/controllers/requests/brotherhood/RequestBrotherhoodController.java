
package controllers.requests.brotherhood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.MemberService;
import services.ProcessionService;
import services.RequestService;
import controllers.AbstractController;
import domain.Procession;
import domain.Request;

@Controller
@RequestMapping("/requests/brotherhood")
public class RequestBrotherhoodController extends AbstractController {

	@Autowired
	MemberService		memberService;

	@Autowired
	ProcessionService	processionService;

	@Autowired
	RequestService		requestService;

	@Autowired
	BrotherhoodService	brotherhoodService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int processionId) {
		final ModelAndView res;
		final Procession p;

		final Collection<Request> requests = this.requestService.findByProcessionId(processionId);
		final Procession p1 = new Procession();
		p1.setId(processionId);
		p = this.processionService.findOne(p1);

		this.brotherhoodService.checkBrotherhoodOwnsProcession(p);

		res = new ModelAndView("requests/list");
		res.addObject("procession", p);
		res.addObject("requests", requests);
		res.addObject("brotherhoodView", true);

		return res;
	}

	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView acceptRequest(@RequestParam final int requestIdA) {
		final ModelAndView res;
		final Request r;

		final Request r1 = new Request();
		r1.setId(requestIdA);
		r = this.requestService.findOne(r1);
		this.requestService.checkRequestOwnsBrotherhood(r);
		res = this.createEditModelAndView(r, "APPROVED", null);

		return res;
	}

	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	public ModelAndView rejectRequest(@RequestParam final int requestIdR) {
		final ModelAndView res;
		final Request r;

		final Request r1 = new Request();
		r1.setId(requestIdR);
		r = this.requestService.findOne(r1);
		this.requestService.checkRequestOwnsBrotherhood(r);
		res = this.createEditModelAndView(r, "REJECTED", null);

		return res;
	}

	private ModelAndView createEditModelAndView(final Request r, final String status, final String messageCode) {
		ModelAndView res;
		final List<Integer> li = new ArrayList<>(this.requestService.suggestPosition(r.getProcession()));
		res = new ModelAndView("requests/edit");
		res.addObject("row", li.get(0));
		res.addObject("column", li.get(1));
		res.addObject("request", r);
		res.addObject("status", status);
		res.addObject("brotherhoodView", true);
		res.addObject("message", messageCode);
		res.addObject("formAction", "requests/brotherhood/edit.do");
		String redirect = "requests/brotherhood/list.do?processionId=";
		redirect = redirect + r.getProcession().getId();

		res.addObject("formBack", redirect);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Request r, final BindingResult binding) {
		ModelAndView res;
		if (r.getRowPosition() != 0 && r.getColumnPosition() != 0)
			if (this.requestService.checkPosition(r) == true) {
				res = this.createEditModelAndView(r, "APPROVED", "error.position.selected");
				return res;
			}
		r = this.requestService.reconstructBrotherhood(r, binding);

		if (binding.hasErrors())
			res = this.createEditModelAndView(r, r.getStatus(), "error.request");
		else

			try {
				this.requestService.checkPositionBeforeSave(r);

				final Request r1 = this.requestService.saveDirectly(r);

				String redirect = "redirect:list.do?processionId=";
				redirect = redirect + r1.getProcession().getId();
				res = new ModelAndView(redirect);
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(r, r.getStatus(), "error.request");
			}

		return res;
	}
}
