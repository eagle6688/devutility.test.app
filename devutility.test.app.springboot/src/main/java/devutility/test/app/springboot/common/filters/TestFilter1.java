package devutility.test.app.springboot.common.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.annotation.Order;

/**
 * 
 * TestFilter1
 * @author: Lenovo
 * @date: 2018-03-13 14:47:14
 * @Copyright: 2018 www.lenovo.com Inc. All rights reserved.
 */
@Order(1)
@WebFilter(filterName = "TestFilter1", urlPatterns = "/*")
public class TestFilter1 implements Filter {
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		System.out.println("TestFilter1");
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}