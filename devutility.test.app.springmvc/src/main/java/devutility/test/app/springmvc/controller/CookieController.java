package devutility.test.app.springmvc.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import devutility.external.servlet.http.CookieUtils;
import devutility.internal.models.OperationResult;

@Controller
@RequestMapping(value = "/cookie")
public class CookieController {
	// url: /cookie/show
	@ResponseBody
	@RequestMapping("/show")
	public OperationResult show(HttpServletRequest request, HttpServletResponse response) {
		OperationResult result = new OperationResult();
		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			result.appendError("no cookie!");
			return result;
		}

		for (Cookie cookie : cookies) {
			result.append(String.format("name: %s, value: %s", cookie.getName(), cookie.getValue()));
		}

		return result;
	}

	// url: /cookie/add
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public OperationResult add(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		OperationResult result = new OperationResult();
		CookieUtils.set(response, name, value);
		return result;
	}
}