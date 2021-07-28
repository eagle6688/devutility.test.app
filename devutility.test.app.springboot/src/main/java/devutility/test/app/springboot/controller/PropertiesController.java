package devutility.test.app.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devutility.internal.test.data.model.Person;
import devutility.test.app.springboot.common.config.Person2Configuration;

@RestController
@RequestMapping("properties")
public class PropertiesController {
	@Autowired
	private Person person;

	@GetMapping("person")
	public Person person() {
		return person;
	}

	@Autowired
	private Person2Configuration person2Configuration;

	@GetMapping("person2")
	public String person2() {
		return person2Configuration.getName();
	}
}