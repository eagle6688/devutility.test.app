package devutility.test.app.springmvc.model;

import org.springframework.web.multipart.MultipartFile;

public class FileWrapper {
	private MultipartFile multipartFile;

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
}