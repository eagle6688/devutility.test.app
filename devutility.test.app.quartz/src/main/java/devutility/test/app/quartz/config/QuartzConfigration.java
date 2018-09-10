package devutility.test.app.quartz.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import devutility.test.app.quartz.jobs.ScheduleJob1;

@Configuration
public class QuartzConfigration {
	@Bean
	public JobDetail jobDetail() {
		JobDetail jobDetail = JobBuilder.newJob(ScheduleJob1.class).build();

		return jobDetail;
	}
}