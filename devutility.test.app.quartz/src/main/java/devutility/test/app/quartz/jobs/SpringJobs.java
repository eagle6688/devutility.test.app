package devutility.test.app.quartz.jobs;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import devutility.test.app.quartz.common.Executor;

@Component
@EnableScheduling
public class SpringJobs {
	public void job1() {
		Executor.execute("Job1", 5000);
	}

	public void job2() {
		Executor.execute("Job2", 6000);
	}
}