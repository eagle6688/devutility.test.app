package devutility.test.app.springmvc.controller.modelattribute;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import devutility.test.app.springmvc.model.User;

@Controller
@RequestMapping(value = "/modelattribute/modelattribute2")
public class ModelAttribute2Controller {
	@ModelAttribute
	public User init() {
		System.out.println("Start executing init method...");

		User user = new User();
		user.setAge(30);
		user.setName("Aldwin");
		return user;
	}

	// url: /modelattribute/modelattribute2/user
	@RequestMapping(value = "/user")
	public String user(Model model) {
		User user = (User) model.asMap().get("user");
		user.setId(2);
		model.addAttribute("user", user);
		return "/modelattribute/modelattribute2/user";
	}
}