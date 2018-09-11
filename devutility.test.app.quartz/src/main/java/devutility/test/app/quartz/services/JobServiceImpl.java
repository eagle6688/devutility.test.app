package devutility.test.app.quartz.services;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devutility.internal.models.OperationResult;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private Scheduler schedulerFactory1;

	@Autowired
	private Scheduler schedulerFactory2;

	@Override
	public void start(String name, String group, Class<? extends Job> clazz) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(name, group).build();

		String triggerName = String.format("trigger_%s", name);
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, group).withSchedule(scheduleBuilder).build();
		schedulerFactory2.scheduleJob(jobDetail, trigger);
	}

	@Override
	public OperationResult delete(String jobName, String jobGroup) {
		OperationResult result = new OperationResult();
		result.append(String.format("Removing Quartz job: %s", jobName));

		try {
			if (!schedulerFactory1.deleteJob(JobKey.jobKey(jobName, jobGroup))) {
				result.setErrorMessage(String.format("Removing job %s failed!", jobName));
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			result.setErrorMessage(String.format("Removing job %s failed with error!", jobName));
		}

		return result;
	}

	@Override
	public OperationResult update(CronTrigger cronTrigger, String cronExpression) {
		OperationResult result = new OperationResult();
		TriggerKey triggerKey = cronTrigger.getKey();
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		CronTrigger newTrigger = cronTrigger.getTriggerBuilder().withSchedule(scheduleBuilder).build();

		try {
			schedulerFactory1.rescheduleJob(triggerKey, newTrigger);
			result.append(String.format("Update cron trigger %s succeeded!", triggerKey.getName()));
		} catch (SchedulerException e) {
			e.printStackTrace();
			result.setErrorMessage(String.format("Update cron trigger %s failed!", triggerKey.getName()));
		}

		return result;
	}
}