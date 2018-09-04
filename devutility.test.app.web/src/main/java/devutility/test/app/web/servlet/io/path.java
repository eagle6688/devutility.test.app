package devutility.test.app.web.servlet.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// URL: /devutility.test.webapp/servlet/io/path
public class path extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public path() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(test());
	}

	private String test() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("System.getProperty(\"user.dir\"): %s", System.getProperty("user.dir")));
		stringBuffer.append(String.format("\nThread.currentThread().getContextClassLoader().getResource(\"\").getPath(): %s", Thread.currentThread().getContextClassLoader().getResource("").getPath()));
		stringBuffer.append(String.format("\nThread.currentThread().getContextClassLoader().getResource(\"\").getFile(): %s", Thread.currentThread().getContextClassLoader().getResource("").getFile()));
		stringBuffer.append(String.format("\nThread.currentThread().getContextClassLoader().getResource(\"/\").getPath(): %s", Thread.currentThread().getContextClassLoader().getResource("/").getPath()));
		stringBuffer.append(String.format("\nThread.currentThread().getContextClassLoader().getResource(\"./\").getPath(): %s", Thread.currentThread().getContextClassLoader().getResource("./").getPath()));
		stringBuffer.append(String.format("\nThread.currentThread().getContextClassLoader().getResource(\"../\").getPath(): %s", Thread.currentThread().getContextClassLoader().getResource("../").getPath()));
		stringBuffer.append(String.format("\nThread.currentThread().getContextClassLoader().getResource(\"../../\").getPath(): %s", Thread.currentThread().getContextClassLoader().getResource("../../").getPath()));

		if (ClassLoader.getSystemResource("") != null) {
			stringBuffer.append(String.format("\nClassLoader.getSystemResource(\"\").getPath(): %s", ClassLoader.getSystemResource("").getPath()));
		}

		if (path.class.getClassLoader() != null && path.class.getClassLoader().getResource("") != null) {
			stringBuffer.append(String.format("\nPathTest.class.getClassLoader().getResource(\"\").getPath(): %s", path.class.getClassLoader().getResource("").getPath()));
		}

		stringBuffer.append(String.format("\nPathTest.class.getResource(\"\").getPath(): %s", path.class.getResource("").getPath()));
		stringBuffer.append(String.format("\nPathTest.class.getResource(\"/\").getPath(): %s", path.class.getResource("/").getPath()));

		try {
			URI uri = Thread.currentThread().getContextClassLoader().getResource("").toURI();
			File file = new File(uri);
			String rootPath = file.getParentFile().getParentFile().getAbsolutePath();
			stringBuffer.append(String.format("\n%s", rootPath));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return stringBuffer.toString();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}