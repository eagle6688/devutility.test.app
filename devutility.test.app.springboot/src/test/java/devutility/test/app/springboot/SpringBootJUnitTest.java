package devutility.test.app.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import devutility.test.app.springboot.services.interfaces.HelloWorldService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJUnitTest {
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