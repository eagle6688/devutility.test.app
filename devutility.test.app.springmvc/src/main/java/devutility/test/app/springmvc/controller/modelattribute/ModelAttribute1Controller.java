package devutility.test.app.springmvc.controller.modelattribute;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import devutility.internal.models.OperationResult;

@Controller
@RequestMapping(value = "/modelattribute/modelattribute1")
public class ModelAttribute1Controller {
	@ModelAttribute
	public void init() {
		System.out.println("Start executing init method...");
	}

	@ModelAttribute
	public void initCookie(@CookieValue("JSESSIONID") String cookie, Model model) {
		System.out.println("Start executing initCookie method...");
		model.addAttribute("cookie", cookie);
	}

	// url: /modelattribute/modelattribute1/other
	@ResponseBody
	@RequestMapping(value = "/other", method = RequestMethod.GET)
	public OperationResult other(Model model) {
		System.out.println("Start executing other method...");
		OperationResult result = new OperationResult();
		result.setData(model.asMap().get("cookie"));
		return result;
	}
}