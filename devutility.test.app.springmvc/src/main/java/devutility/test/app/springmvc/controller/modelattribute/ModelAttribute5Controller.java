package devutility.test.app.springmvc.controller.modelattribute;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import devutility.test.app.springmvc.model.User;

@Controller
@RequestMapping(value = "/modelattribute/modelattribute5")
public class ModelAttribute5Controller {
	@ModelAttribute("myuser")
	public User init() {
		System.out.println("Start executing init method...");

		User user = new User();
		user.setAge(30);
		user.setName("Aldwin");
		return user;
	}

	// url: /modelattribute/modelattribute5/user
	@RequestMapping("/user")
	public String user(@ModelAttribute("myuser") User user) {
		user.setId(5);
		return "/modelattribute/modelattribute5/user";
	}
}