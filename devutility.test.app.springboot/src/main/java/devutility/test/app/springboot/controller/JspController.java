package devutility.test.app.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp")
public class JspController extends BaseController {
	@RequestMapping("/index")
	public String index(Model model) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		model.addAttribute("SessionId", request.getSession().getId());
		return "index";
	}

	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("SessionId", request.getSession().getId());
		return "index";
	}
}