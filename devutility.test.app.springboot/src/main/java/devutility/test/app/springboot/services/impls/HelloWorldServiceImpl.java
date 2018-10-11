package devutility.test.app.springboot.services.impls;

import org.springframework.stereotype.Service;

import devutility.test.app.springboot.services.interfaces.HelloWorldService;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
	@Override
	public String sayHello() {
		return "I am saying hello!";
	}
}