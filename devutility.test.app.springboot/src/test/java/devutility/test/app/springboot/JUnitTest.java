package devutility.test.app.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import devutility.test.app.springboot.service.HelloWorldService;

public class JUnitTest {
	@Autowired
	private HelloWorldService helloWorldService;

	@Test
	public void helloWorld() {
		System.out.println("Hello world!");
	}

	@Test
	public void sayHello() {
		System.out.println(helloWorldService.sayHello());
	}
}