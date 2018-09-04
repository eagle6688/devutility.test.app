package devutility.test.app.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class DUTA_SpringBootApp {
	public static void main(String[] args) {
		SpringApplication.run(DUTA_SpringBootApp.class, args);
	}
}