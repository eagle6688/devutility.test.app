package devutility.test.app.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log4j2")
public class Log4j2Controller extends BaseController {
	@RequestMapping("/info")
	public String info(String message) {
		System.out.println(message);
		logger.info(message);
		return message;
	}
}