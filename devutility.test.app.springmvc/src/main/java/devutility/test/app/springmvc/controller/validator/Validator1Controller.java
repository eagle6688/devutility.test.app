package devutility.test.app.springmvc.controller.validator;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import devutility.internal.models.OperationResult;
import devutility.test.app.springmvc.model.FileWrapper;

@Controller
@RequestMapping(value = "/validator1")
public class Validator1Controller extends BaseController {
	// url: /validator1/upload1
	@ResponseBody
	@RequestMapping(value = "/upload1", method = RequestMethod.POST)
	public OperationResult upload1(@Valid FileWrapper fileWrapper, BindingResult result, Model model) {
		OperationResult operationResult = new OperationResult();
		return operationResult;
	}
}