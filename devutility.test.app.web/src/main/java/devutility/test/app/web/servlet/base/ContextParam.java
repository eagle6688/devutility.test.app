package devutility.test.app.web.servlet.base;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContextParam /devutility.test.webapp/servlet/base/ContextParam
 */
public class ContextParam extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContextParam() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer stringBuffer = new StringBuffer();
		ServletContext servletContext = getServletConfig().getServletContext();

		String cp1 = servletContext.getInitParameter("cp1");
		stringBuffer.append(String.format("\ncp1: %s", cp1));

		String cp2 = servletContext.getInitParameter("cp2");
		stringBuffer.append(String.format("\ncp2: %s", cp2));

		response.getWriter().append("Served at: ").append(request.getContextPath()).append(stringBuffer.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}