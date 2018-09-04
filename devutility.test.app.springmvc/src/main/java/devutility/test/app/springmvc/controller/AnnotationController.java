package devutility.test.app.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import devutility.internal.models.OperationResult;
import devutility.test.app.springmvc.model.User;

@Controller
@RequestMapping(value = "/annotation")
public class AnnotationController {
	// url: /annotation/pathvariable/123/
	@RequestMapping(value = "/pathvariable/{id}", method = RequestMethod.GET)
	public String pathvariable(@PathVariable String id, Model model) {
		model.addAttribute("id", id);
		return "annotation/pathvariable";
	}

	// url: /annotation/consumes
	@ResponseBody
	@RequestMapping(value = "/consumes", method = RequestMethod.POST, consumes = "application/json")
	public OperationResult consumes(@RequestBody User user, Model model) {
		user.setId(1);
		OperationResult result = new OperationResult();
		result.setData(user);
		return result;
	}

	// url: /annotation/produces
	@ResponseBody
	@RequestMapping(value = "/produces", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String produces() {
		OperationResult result = new OperationResult();

		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	// url: /annotation/producesObject
	@ResponseBody
	@RequestMapping(value = "/producesObject", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public OperationResult producesObject(Model model) {
		OperationResult result = new OperationResult();
		return result;
	}

	// url: /annotation/requestheader
	@ResponseBody
	@RequestMapping(value = "/requestheader", method = RequestMethod.GET)
	public OperationResult requestheader(@RequestHeader("Accept") String accept, Model model) {
		OperationResult result = new OperationResult();
		result.setData(accept);
		return result;
	}

	// url: /annotation/cookievalue
	@ResponseBody
	@RequestMapping(value = "/cookievalue", method = RequestMethod.GET)
	public OperationResult cookievalue(@CookieValue("JSESSIONID") String cookie, Model model) {
		OperationResult result = new OperationResult();
		result.setData(cookie);
		return result;
	}

	// url: /annotation/requestparam
	@ResponseBody
	@RequestMapping(value = "/requestparam", method = RequestMethod.POST)
	public OperationResult requestparam(@RequestParam("name") String name, Model model) {
		OperationResult result = new OperationResult();
		result.setData(name);
		return result;
	}

	// url: /annotation/requestbodyform
	@ResponseBody
	@RequestMapping(value = "/requestbodyform", method = RequestMethod.POST)
	public OperationResult requestbodyform(User user, Model model, HttpServletRequest request) {
		System.out.println(request.getContentType());
		System.out.println(request.getQueryString());
		OperationResult result = new OperationResult();
		user.setId(1);
		result.setData(user);
		return result;
	}
}