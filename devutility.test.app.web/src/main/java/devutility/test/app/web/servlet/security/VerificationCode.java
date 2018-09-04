package devutility.test.app.web.servlet.security;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import devutility.internal.base.Convertor;
import devutility.internal.security.VerificationCodeHelper;

/**
 * Servlet implementation class VerificationCode
 * /devutility.test.webapp/servlet/security/VerificationCode?d=6&w=100&h=30
 */
public class VerificationCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerificationCode() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int digit = Convertor.stringToInt(request.getParameter("d"));
		int width = Convertor.stringToInt(request.getParameter("w"));
		int height = Convertor.stringToInt(request.getParameter("h"));
		BufferedImage bufferedImage = VerificationCodeHelper.create(width, height, digit, 16);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		ImageIO.write(bufferedImage, "jpg", servletOutputStream);
		servletOutputStream.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}