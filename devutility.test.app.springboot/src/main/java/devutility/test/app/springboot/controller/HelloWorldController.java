package devutility.test.app.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController extends BaseController {
	@RequestMapping("/hello")
	public String index() {
		return "Hello World";
	}
}