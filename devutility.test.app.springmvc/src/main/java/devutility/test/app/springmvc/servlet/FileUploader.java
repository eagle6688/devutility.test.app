package devutility.test.app.springmvc.servlet;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import devutility.internal.io.DirectoryUtils;

/**
 * Servlet implementation class FileUploader
 */
@WebServlet(name = "FileUploader", urlPatterns = "/servlet/FileUploader")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class FileUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploader() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Hello world!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println(String.format("name: %s", name));

		Collection<Part> parts = request.getParts();
		String projectDirectory = DirectoryUtils.getProjectDirectory();
		Path uploadDirectory = Paths.get(projectDirectory, "data", "upload");

		for (Part part : parts) {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format("\nname: %s", part.getName()));
			stringBuffer.append(String.format(", SubmittedFileName: %s", part.getSubmittedFileName()));
			stringBuffer.append(String.format(", size: %s", part.getSize()));
			stringBuffer.append(String.format(", ContentType: %s", part.getContentType()));
			System.out.println(stringBuffer);

			if (part.getContentType() != null && part.getSubmittedFileName() != null) {
				String filePath = Paths.get(uploadDirectory.toString(), part.getSubmittedFileName()).toString();
				part.write(filePath);
			}
		}
	}
}