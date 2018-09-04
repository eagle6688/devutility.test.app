package devutility.test.app.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import devutility.internal.models.OperationResult;

@Controller
@RequestMapping(value = "/test")
public class TestController {
	// url: /test/cookievalue
	@ResponseBody
	@RequestMapping(value = "/cookievalue")
	public OperationResult cookievalue(@CookieValue("JSESSIONID") String cookie, Model model) {
		OperationResult result = new OperationResult();
		result.setData(cookie);
		return result;
	}

	// url: /test/user
	@ResponseBody
	@RequestMapping(value = "/user")
	public OperationResult user(Model model) {
		OperationResult result = new OperationResult();
		result.setData("Hello World!");
		return result;
	}
}