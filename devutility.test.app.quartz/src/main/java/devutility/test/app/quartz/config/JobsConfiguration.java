package devutility.test.app.quartz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import devutility.test.app.quartz.common.Executor;

@Configuration
@Component
@EnableScheduling
public class JobsConfiguration {
	public void job1() {
		Executor.execute("Job1", 5000);
	}

	public void job2() {
		Executor.execute("Job2", 6000);
	}
}