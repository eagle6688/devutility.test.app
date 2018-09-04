package devutility.test.app.springmvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import devutility.test.app.springmvc.model.FileWrapper;

public class FileWrapperValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return FileWrapper.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FileWrapper fileWrapper = (FileWrapper) target;

		if (fileWrapper.getMultipartFile() != null) {
			if (fileWrapper.getMultipartFile().getSize() == 0) {
				errors.rejectValue("file", "missing.file");
			}
		}
	}
}