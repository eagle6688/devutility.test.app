package devutility.test.app.springboot.service.impl;

import org.springframework.stereotype.Service;

import devutility.test.app.springboot.service.HelloWorldService;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
	@Override
	public String sayHello() {
		return "I am saying hello!";
	}
}