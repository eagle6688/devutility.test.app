package devutility.test.app.web.servlet.base;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HttpServletRequestTest
 * /devutility.test.webapp/servlet/base/HttpServletRequestTest?asd=qwe&asd=zxc
 */
public class HttpServletRequestTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HttpServletRequestTest() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locale = getLocale(request.getLocale());
		String serverInfo = getServerInfo(request);
		String clientInfo = getClientInfo(request);
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(locale).append(serverInfo).append(clientInfo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String getLocale(Locale locale) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("\nLocale: %s", locale.toString()));
		buffer.append(String.format("\ngetDisplayCountry: %s", locale.getDisplayCountry()));
		buffer.append(String.format("\ngetDisplayCountry: %s", locale.getDisplayLanguage()));
		buffer.append(String.format("\ngetCountry: %s", locale.getCountry()));
		buffer.append(String.format("\ngetLanguage: %s", locale.getLanguage()));
		buffer.append(String.format("\ngetDisplayName: %s", locale.getDisplayName()));
		return buffer.toString();
	}

	private String getServerInfo(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("\ngetAuthType: %s", request.getAuthType()));
		buffer.append(String.format("\ngetLocalAddr: %s", request.getLocalAddr()));
		buffer.append(String.format("\ngetLocalName: %s", request.getLocalName()));
		buffer.append(String.format("\ngetLocalPort: %s", request.getLocalPort()));
		buffer.append(String.format("\ngetServerName: %s", request.getServerName()));
		buffer.append(String.format("\ngetServerPort: %s", request.getServerPort()));
		buffer.append(String.format("\ngetContextPath: %s", request.getContextPath()));
		buffer.append(String.format("\ngetPathInfo: %s", request.getPathInfo()));
		buffer.append(String.format("\ngetProtocol: %s", request.getProtocol()));
		buffer.append(String.format("\ngetRequestedSessionId: %s", request.getRequestedSessionId()));
		// buffer.append(String.format("\ngetSession().getId(): %s",
		// request.getSession().getId()));
		buffer.append(String.format("\ngetScheme: %s", request.getScheme()));
		buffer.append(String.format("\ngetRequestURI: %s", request.getRequestURI()));
		buffer.append(String.format("\ngetRequestURL: %s", request.getRequestURL()));
		buffer.append(String.format("\ngetQueryString: %s", request.getQueryString()));
		buffer.append(String.format("\ngetParameterValues(\"asd\"): %s", String.join(",", request.getParameterValues("asd"))));
		return buffer.toString();
	}

	private String getClientInfo(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("\ngetRemoteAddr: %s", request.getRemoteAddr()));
		buffer.append(String.format("\ngetRemoteHost: %s", request.getRemoteHost()));
		buffer.append(String.format("\ngetRemotePort: %s", request.getRemotePort()));
		buffer.append(String.format("\ngetRemoteUser: %s", request.getRemoteUser()));
		return buffer.toString();
	}
}