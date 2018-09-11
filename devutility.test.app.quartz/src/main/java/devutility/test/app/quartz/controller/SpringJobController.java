package devutility.test.app.quartz.controller;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devutility.internal.models.OperationResult;

@RestController
@RequestMapping("/spring-job")
public class SpringJobController {
	@Qualifier("schedulerFactory1")
	@Autowired
	private Scheduler scheduler;

	@Qualifier("jobDetailFactory1")
	@Autowired
	private JobDetail jobDetail;

	@Qualifier("cronTriggerFactory2")
	@Autowired
	private CronTrigger cronTrigger;

	@RequestMapping("/delete")
	public OperationResult delete() {
		JobKey jobKey = jobDetail.getKey();
		return delete(jobKey.getName(), jobKey.getGroup());
	}

	private OperationResult delete(String jobName, String group) {
		OperationResult result = new OperationResult();
		result.append(String.format("Removing Quartz job: %s", jobName));

		try {
			if (!scheduler.deleteJob(JobKey.jobKey(jobName, group))) {
				result.setErrorMessage(String.format("Removing job %s failed!", jobName));
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			result.setErrorMessage(String.format("Removing job %s failed with error!", jobName));
		}

		return result;
	}

	@RequestMapping("/update")
	public OperationResult update() {
		System.out.println(cronTrigger.getCronExpression());
		System.out.println(cronTrigger.getKey().getName());
		return update(cronTrigger, "0/2 * * * * ?");
	}

	private OperationResult update(CronTrigger cronTrigger, String cronExpression) {
		OperationResult result = new OperationResult();
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		CronTrigger newTrigger = cronTrigger.getTriggerBuilder().withSchedule(scheduleBuilder).build();

		try {
			scheduler.rescheduleJob(cronTrigger.getKey(), newTrigger);
			result.append(String.format("Update cron trigger %s succeeded!", cronTrigger.getKey().getName()));
		} catch (SchedulerException e) {
			e.printStackTrace();
			result.setErrorMessage(String.format("Update cron trigger %s failed!", cronTrigger.getKey().getName()));
		}

		return result;
	}
}