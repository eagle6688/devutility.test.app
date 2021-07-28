package devutility.test.app.springboot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devutility.internal.io.DirectoryUtils;
import devutility.internal.models.OperationResult;
import devutility.internal.util.PropertiesUtils;
import devutility.test.app.springboot.model.Tester;

@RestController
@RequestMapping("test")
public class TestController extends BaseController {
	@GetMapping("request")
	public OperationResult request(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return new OperationResult();
	}

	@Autowired
	private Tester tester;

	@GetMapping("tester")
	public Tester tester() {
		return tester;
	}

	@GetMapping("pro")
	public Map<String, Object> properties(String resource) {
		Map<String, Object> map = new HashMap<>();
		map.put("resource", resource);
		map.put("DirectoryUtils.class.getClassLoader().getResource()", DirectoryUtils.class.getClassLoader().getResource(""));

		InputStream inputStream = null;
		String projectDirectory = DirectoryUtils.getProjectDirectory();
		String resourcePath = Paths.get(projectDirectory, "WEB-INF", "classes", resource).toString();
		File file = new File(resourcePath);

		if (file.exists()) {
			try {
				inputStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			Properties properties = PropertiesUtils.getProperties(inputStream);
			map.put("properties", properties);
			return map;
		}

		try {
			inputStream = getInputStream(resource);

			if (inputStream != null) {
				Properties properties = PropertiesUtils.getProperties(inputStream);
				map.put("properties", properties);
			}

		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	public static InputStream getInputStream(String resource) throws URISyntaxException, IOException {
		String projectDirectory = DirectoryUtils.getProjectDirectory();
		URL url = DirectoryUtils.class.getClassLoader().getResource(resource);

		/**
		 * Rank 1: Local resources path.
		 */
		if (url != null && "file".equals(url.getProtocol())) {
			File file = new File(url.toURI());
			String path = file.getAbsolutePath();

			if (file.exists() && path.indexOf(projectDirectory) == 0) {
				return new FileInputStream(file);
			}
		}

		/**
		 * Rank 2: Project directory.
		 */
		Path resourcePath = Paths.get(projectDirectory, resource);
		File resourceFile = resourcePath.toFile();

		if (resourceFile.exists()) {
			return new FileInputStream(resourceFile);
		}

		/**
		 * Rank 3: Other project resources path.
		 */
		if (url != null && "file".equals(url.getProtocol())) {
			File file = new File(url.toURI());

			if (file.exists()) {
				return new FileInputStream(file);
			}
		}

		/**
		 * Rank 4: Dependent jar package file.
		 */
		if (url != null && "jar".equals(url.getProtocol())) {
			return url.openStream();
		}

		return null;
	}
}