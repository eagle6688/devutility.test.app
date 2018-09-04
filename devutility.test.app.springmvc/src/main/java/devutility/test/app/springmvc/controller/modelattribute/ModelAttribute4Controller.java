package devutility.test.app.springmvc.controller.modelattribute;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import devutility.test.app.springmvc.model.User;

@Controller
@RequestMapping(value = "/modelattribute/modelattribute4")
public class ModelAttribute4Controller {
	@ModelAttribute
	public User init() {
		System.out.println("Start executing init method...");

		User user = new User();
		user.setAge(30);
		user.setName("Aldwin");
		return user;
	}

	// url: /modelattribute/modelattribute4/user
	@RequestMapping(value = "/user")
	public String user(User user) {
		user.setId(4);
		return "/modelattribute/modelattribute2/user";
	}
}