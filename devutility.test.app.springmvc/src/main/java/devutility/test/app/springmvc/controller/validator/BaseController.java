package devutility.test.app.springmvc.controller.validator;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {
	@InitBinder("fileWrapper")
	protected void initBinderFileWrapper(WebDataBinder binder) {
	}
}