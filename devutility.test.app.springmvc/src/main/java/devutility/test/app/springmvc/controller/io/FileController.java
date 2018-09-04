package devutility.test.app.springmvc.controller.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import devutility.internal.io.DirectoryUtils;
import devutility.internal.models.OperationResult;

@Controller
@RequestMapping(value = "/file")
public class FileController {
	// url: /file/upload1
	@ResponseBody
	@RequestMapping(value = "/upload1", method = RequestMethod.POST)
	public OperationResult upload1(@RequestPart("file") MultipartFile file, Model model) {
		OperationResult result = new OperationResult();
		result.setData(file != null ? file.getSize() : 0);

		if (file == null) {
			return result;
		}

		String projectDirectory = DirectoryUtils.getProjectDirectory();
		Path uploadDirectory = Paths.get(projectDirectory, "data", "upload");
		String filePath = Paths.get(uploadDirectory.toString(), file.getOriginalFilename()).toString();

		try {
			file.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	// url: /file/upload2
	@ResponseBody
	@RequestMapping(value = "/upload2", method = RequestMethod.POST)
	public OperationResult upload2(HttpServletRequest request, HttpServletResponse response) {
		OperationResult result = new OperationResult();
		String projectDirectory = DirectoryUtils.getProjectDirectory();
		Path uploadDirectory = Paths.get(projectDirectory, "data", "upload");

		try {
			request.getParts().forEach(i -> {
				result.append(i.getName());
				result.append(": ");
				result.append(i.getSubmittedFileName());
				result.append("; ");

				if (i.getSubmittedFileName() != null) {
					String filePath = Paths.get(uploadDirectory.toString(), i.getSubmittedFileName()).toString();

					try {
						i.write(filePath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}

		return result;
	}
}