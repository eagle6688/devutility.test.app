package devutility.test.app.web.servlet.base;

import java.io.IOException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResourceInjection /devutility.test.webapp/servlet/base/ResourceInjection
 */
public class ResourceInjection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private @Resource(name = "rp1") String rp1;

	@Resource(name = "rp2")
	private int rp2;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResourceInjection() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("\nrp1: %s", rp1));
		stringBuffer.append(String.format("\nrp2: %d", rp2));
		response.getWriter().append(stringBuffer);

		try {
			response.getWriter().append(JavaNamingAndDirectoryInterface());
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private String JavaNamingAndDirectoryInterface() throws NamingException {
		Context context = new InitialContext();
		Context envContext = (Context) context.lookup("java:comp/env");

		StringBuffer stringBuffer = new StringBuffer("\nGet resource by JNDI");
		String rp1 = (String) envContext.lookup("rp1");
		stringBuffer.append(String.format("\nrp1: %s", rp1));

		int rp2 = (Integer) envContext.lookup("rp2");
		stringBuffer.append(String.format("\nrp2: %d", rp2));

		return stringBuffer.toString();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}