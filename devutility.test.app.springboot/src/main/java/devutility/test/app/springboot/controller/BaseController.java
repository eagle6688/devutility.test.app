package devutility.test.app.springboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {
	/**
	 * Request
	 */
	protected HttpServletRequest request;

	/**
	 * Response
	 */
	protected HttpServletResponse response;

	/**
	 * Set context.
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void setContext(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
}