package devutility.test.app.web.servlet.crossdomain;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import devutility.internal.io.DirectoryUtils;
import devutility.internal.io.TextFileHelper;
import devutility.test.app.web.config.TemplateConfig;
import devutility.test.app.web.models.crossdomain.HtmlJsonp;

/**
 * Servlet implementation class header /devutility.test.webapp/servlet/crossdomain/header
 */
public class header extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public header() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String callback = request.getParameter("callback");
		String html_HeaderPath = DirectoryUtils.toAbsolutePath(TemplateConfig.Html_Header);

		HtmlJsonp htmlJsonp = new HtmlJsonp();
		htmlJsonp.setHtml(TextFileHelper.read(html_HeaderPath, Charset.forName("utf-8")));

		String jsInvoker_HeaderPath = DirectoryUtils.toAbsolutePath(TemplateConfig.JsInvoker_Header);
		String invokerjs = TextFileHelper.read(jsInvoker_HeaderPath, Charset.forName("utf-8"));

		StringBuffer stringBuffer = new StringBuffer(";");
		stringBuffer.append(invokerjs);
		stringBuffer.append("; ");
		stringBuffer.append(callback);
		stringBuffer.append("(");
		stringBuffer.append(objectMapper.writeValueAsString(htmlJsonp));
		stringBuffer.append(");");

		response.getWriter().append(stringBuffer);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}