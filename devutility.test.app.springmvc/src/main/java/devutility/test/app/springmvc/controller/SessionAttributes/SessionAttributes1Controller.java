package devutility.test.app.springmvc.controller.SessionAttributes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import devutility.internal.models.OperationResult;
import devutility.test.app.springmvc.model.User;

@Controller
@RequestMapping("/sessionattributes/sessionattributes1")
@SessionAttributes(value = { "user" }, types = { User.class })
public class SessionAttributes1Controller {

	// url: /sessionattributes/sessionattributes1/user1
	@ResponseBody
	@RequestMapping("/user1")
	public User user1(Model model) {
		User user = new User();
		user.setId(1);
		user.setAge(30);
		user.setName("Aldwin");
		model.addAttribute("user", user);
		return user;
	}

	// url: /sessionattributes/sessionattributes1/user2
	@RequestMapping("/user2")
	public String user2(Model model) {
		User user = new User();
		user.setId(2);
		user.setAge(40);
		user.setName("James");
		model.addAttribute("user2", user);
		return "/session-attributes/session-attributes1/user2";
	}

	// url: /sessionattributes/sessionattributes1/clear
	@ResponseBody
	@RequestMapping("/clear")
	public OperationResult clear(SessionStatus sessionStatus) {
		OperationResult result = new OperationResult();
		sessionStatus.setComplete();
		return result;
	}
}