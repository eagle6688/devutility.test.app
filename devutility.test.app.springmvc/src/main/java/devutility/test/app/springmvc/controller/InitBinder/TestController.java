package devutility.test.app.springmvc.controller.InitBinder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import devutility.internal.models.OperationResult;
import devutility.test.app.springmvc.model.User;

@Controller("initbinder-TestController")
@RequestMapping(value = "/initbinder/test")
public class TestController extends BaseController {
	// url: /initbinder/test/save
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public OperationResult save(@RequestBody User user, Model model) {
		user.setId(1);

		OperationResult result = new OperationResult();
		result.setData(user);
		return result;
	}
}