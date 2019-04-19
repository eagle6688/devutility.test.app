package devutility.test.app.springboot.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import devutility.internal.test.data.model.Person;

@Configuration
@PropertySource("classpath:test.properties")
public class Person1Configuration {
	@Bean
	@ConfigurationProperties(prefix = "test.asd")
	public Person person() {
		return new Person();
	}
}