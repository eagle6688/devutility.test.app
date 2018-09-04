package devutility.test.app.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devutility.test.model.Person;

import devutility.test.app.springboot.common.config.Person2Configuration;

@RestController
@RequestMapping("/properties")
public class PropertiesController {
	@Autowired
	private Person person;

	@Autowired
	private Person2Configuration person2Configuration;

	@RequestMapping("/person")
	public Person person() {
		return person;
	}

	@RequestMapping("/person2")
	public String person2() {
		return person2Configuration.getName();
	}
}