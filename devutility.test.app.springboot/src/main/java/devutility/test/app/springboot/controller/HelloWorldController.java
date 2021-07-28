package devutility.test.app.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController extends BaseController {
	@GetMapping("hello")
	public String index() {
		return "Hello World";
	}
}