package controllers;

import java.util.Collection;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.AreaService;
import services.BrotherhoodService;
import domain.Area;
import domain.Brotherhood;


@Controller
@RequestMapping("/area/brotherhood")
public class AreaBrotherhoodController {
	
	@Autowired
	private BrotherhoodService brotherhoodService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result = null;
		UserAccount ua;
		ua = LoginService.getPrincipal();
		Brotherhood b = this.brotherhoodService.getBrotherhoodByUserAccount(ua);
		
		result = this.createEditModelAndView(b);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Brotherhood b, final BindingResult binding) {
		ModelAndView result;
		Area area;
		area = b.getArea();
		Brotherhood x;
		UserAccount ua;
		ua = LoginService.getPrincipal();
		x = this.brotherhoodService.getBrotherhoodByUserAccount(ua);
		Collection<Area> areas = this.areaService.findAll();
		
		b = this.brotherhoodService.reconstructArea(b, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(b);
		else
			try {
				if(areas.contains(x.getArea())){
					result = new ModelAndView("redirect:/welcome/index.do");
				}else{
					this.brotherhoodService.saveMyArea(area);
					result = new ModelAndView("redirect:/welcome/index.do");
				}
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(b, "area.commit.error");
			}
			
		return result;
	}

	
	protected ModelAndView createEditModelAndView(Brotherhood b) {
		ModelAndView result;
		result = this.createEditModelAndView(b, null);

		return result;
	}
	
	protected ModelAndView createEditModelAndView(Brotherhood b, String messageCode) {
		ModelAndView result;
		final Collection<Area> areaList = this.areaService.findAll();
	
		result = new ModelAndView("area/brotherhood/edit");
		result.addObject("brotherhood",b);
		result.addObject("areaList", areaList);
		
		result.addObject("message", messageCode);

		return result;
	}

}
