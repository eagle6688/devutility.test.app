package devutility.test.app.quartz.config;

import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SpringJobConfiguration {
	@Bean
	public MethodInvokingJobDetailFactoryBean jobDetailFactory1(JobsConfiguration jobsConfiguration) {
		MethodInvokingJobDetailFactoryBean jobDetailFactory = new MethodInvokingJobDetailFactoryBean();
		jobDetailFactory.setName("Spring-Job1");
		jobDetailFactory.setGroup("Spring-Group");

		jobDetailFactory.setTargetObject(jobsConfiguration);
		jobDetailFactory.setTargetMethod("job1");

		jobDetailFactory.setConcurrent(false);
		return jobDetailFactory;
	}

	@Bean
	public CronTriggerFactoryBean cronTriggerFactory1(@Qualifier("jobDetailFactory1") MethodInvokingJobDetailFactoryBean jobDetailFactory1) {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setName("cronTriggerFactoryForJobDetailFactory1");
		cronTriggerFactoryBean.setJobDetail(jobDetailFactory1.getObject());
		cronTriggerFactoryBean.setCronExpression("0/3 * * * * ?");
		return cronTriggerFactoryBean;
	}

	@Bean
	public MethodInvokingJobDetailFactoryBean jobDetailFactory2(JobsConfiguration jobsConfiguration) {
		MethodInvokingJobDetailFactoryBean jobDetailFactory = new MethodInvokingJobDetailFactoryBean();
		jobDetailFactory.setName("Spring-Job2");
		jobDetailFactory.setGroup("Spring-Group");

		jobDetailFactory.setTargetObject(jobsConfiguration);
		jobDetailFactory.setTargetMethod("job2");

		jobDetailFactory.setConcurrent(true);
		return jobDetailFactory;
	}

	@Bean
	public CronTriggerFactoryBean cronTriggerFactory2(@Qualifier("jobDetailFactory2") MethodInvokingJobDetailFactoryBean jobDetailFactory2) {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setName("cronTriggerFactoryForJobDetailFactory2");
		cronTriggerFactoryBean.setJobDetail(jobDetailFactory2.getObject());
		cronTriggerFactoryBean.setCronExpression("0/4 * * * * ?");
		return cronTriggerFactoryBean;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactory1(Trigger cronTriggerFactory1, Trigger cronTriggerFactory2) {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setStartupDelay(2);
		schedulerFactoryBean.setTriggers(cronTriggerFactory1, cronTriggerFactory2);
		return schedulerFactoryBean;
	}
}