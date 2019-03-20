package controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Position;

import services.PositionService;

@Controller
@RequestMapping("/position/administrator")
public class PositionAdministratorController {
	
	@Autowired
	private PositionService	positionService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final List<Position> positions = (List<Position>) this.positionService.findAll();

		result = new ModelAndView("position/list");
		result.addObject("positions", positions);
		result.addObject("requestURI", "/position/administrator/list.do");

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Position position;

		position = this.positionService.create();

		result = this.createEditModelAndView(position);
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int positionId) {
		ModelAndView result;
		Position position;

		position = this.positionService.findOne(positionId);
		Assert.notNull(position);
		result = this.createEditModelAndView(position);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Position position, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(position);
		else
			try {
				this.positionService.save(position);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(position, "position.commit.error");
			}
			
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Position position, final BindingResult binding) {
		ModelAndView result;

		try {
			Position x;
			x = this.positionService.findOne(position.getId());
			if(x.equals(null)){
				return this.create();
			}
	
			this.positionService.delete(position);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(position);
		}
		
		return result;
	}


	protected ModelAndView createEditModelAndView(Position position) {
		ModelAndView result;
		result = this.createEditModelAndView(position, null);

		return result;
	}
	

	protected ModelAndView createEditModelAndView(Position position, String messageCode) {
		ModelAndView result;
		Collection<Position> positionlist;

		positionlist = this.positionService.findAll();
		if (positionlist.contains(position))
			positionlist.remove(position);

		result = new ModelAndView("position/edit");
		result.addObject("position", position);
		result.addObject("list", positionlist);

		result.addObject("message", messageCode);

		return result;
	}
	
	//-------------------------DISPLAY-----------------------------------
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int positionId) {
		ModelAndView result;
		Position position;

		position = this.positionService.findOne(positionId);
		Assert.notNull(position);

		result = this.createDisplayModelAndView(position);

		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Position position) {
		ModelAndView result;
		result = this.createDisplayModelAndView(position, null);

		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Position position, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("position/display");
		result.addObject("position", position);
		result.addObject("messageCode", messageCode);
	
		return result;

	}
	

}
