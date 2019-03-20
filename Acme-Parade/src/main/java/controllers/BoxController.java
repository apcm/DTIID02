
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.BoxService;
import services.MessageService;
import domain.Actor;
import domain.Box;
import domain.Message;

@Controller
@RequestMapping("/boxes")
public class BoxController extends AbstractController {

	@Autowired
	private ActorService	as;

	@Autowired
	private BoxService		mbs;

	@Autowired
	private MessageService	ms;

	@Autowired
	private ActorRepository	ar;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		final Collection<Box> mBox;

		mBox = this.as.getMyBoxes();

		result = new ModelAndView("messageBox/list");

		result.addObject("messageBoxes", mBox);

		return result;

	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Box msb;

		msb = this.mbs.create();

		result = this.createEditModelAndView(msb);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Box mbox) {
		ModelAndView result;
		result = this.createEditModelAndView(mbox, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Box mbox, final String messageCode) {
		final ModelAndView result;
		Collection<Message> messages;

		messages = mbox.getMessages();

		final UserAccount actual = LoginService.getPrincipal();
		final Actor a = this.ar.getActor(actual);

		final Collection<Box> allBoxes = a.getBoxes();
		//Cojo las cajas del sistema y las elimino
		final Object[] array = allBoxes.toArray();

		for (int i = 0; i < 5; i++) {
			final Box x = (Box) array[i];
			allBoxes.remove(x);
		}

		allBoxes.remove(mbox);

		//¿Puedo poner una caja del sistema como descendiente? Entiendo que no

		result = new ModelAndView("messageBox/edit");
		result.addObject("messageBox", mbox);
		result.addObject("messages", messages);
		result.addObject("allBoxes", allBoxes);
		result.addObject("message", messageCode);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int boxId) {
		ModelAndView result;
		Box messageBox;

		messageBox = this.mbs.findOne(boxId);
		try {
			Assert.notNull(messageBox);
			//Assert.isTrue(!messageBox.getPredefined(), "You can't edit a predefined box");
			final UserAccount actual = LoginService.getPrincipal();
			final Actor a = this.ar.getActor(actual);

			Assert.isTrue(a.getBoxes().contains(messageBox));
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect: list.do");
			return result;
		}

		result = this.createEditModelAndView(messageBox);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Box messageBox, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(messageBox);
		else
			try {
				//Compruebo si es una caja del sistema
				if(messageBox.getId() != 0){
					Box sysbox = mbs.findOne(messageBox.getId());
					if(sysbox.getPredefined() == true){
						messageBox.setName(sysbox.getName());
						messageBox.setPredefined(true);
					}
				}
				this.as.editBox(messageBox);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(messageBox, "messageBox.edit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Box messageBox, final BindingResult binding) {
		ModelAndView result;
		//Comprobacion de descendientes
		final Box comp = this.mbs.findOne(messageBox.getId());
		if (comp.getDescendants() != null && !comp.getDescendants().isEmpty())
			result = this.createEditModelAndView(messageBox, "messageBox.descendants.error");
		else
			try {
				this.as.deleteMessageBox(messageBox);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(messageBox, "messageBox.commit.error");
			}
		return result;
	}
	
	@RequestMapping(value = "/move", method = RequestMethod.GET)
	public ModelAndView move(@RequestParam final int messageId) {
		ModelAndView result;

		final Collection<Box> messageBox = this.as.getMyBoxes();

		result = new ModelAndView("messageBox/move");

		System.out.println(messageBox);
		result.addObject("messageBoxes", messageBox);
		result.addObject("messageId", messageId);
		result.addObject("requestURI", "/messageBox/move.do");

		return result;
	}

	@RequestMapping(value = "/copyToBox", method = RequestMethod.GET)
	public ModelAndView copyToBox(@RequestParam final int messageBoxId, @RequestParam final int messageId) {
		ModelAndView result;

		final Box mb = this.mbs.findOne(messageBoxId);
		final Message m = this.ms.findOne(messageId);

		try{
			Assert.isTrue(!mb.getMessages().contains(m));

		} catch (Exception e){
			result = new ModelAndView("redirect:move.do");
			result.addObject("messageId", messageId);

			return result;
		}
		final Collection<Message> messages = mb.getMessages();
		messages.add(m);
		mb.setMessages(messages);

		this.mbs.save(mb);

		result = new ModelAndView("redirect:list.do");

		return result;
	}

	}
