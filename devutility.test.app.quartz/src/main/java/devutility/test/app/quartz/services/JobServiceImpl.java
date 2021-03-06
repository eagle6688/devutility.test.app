package devutility.test.app.quartz.services;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devutility.internal.models.OperationResult;
import devutility.test.app.quartz.common.QuartzUtils;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private Scheduler schedulerFactory1;

	@Autowired
	private Scheduler scheduler;

	@Override
	public void start(String name, String group, String cronExpression, Class<? extends Job> clazz) throws SchedulerException {
		QuartzUtils.start(scheduler, name, group, cronExpression, clazz);
	}

	@Override
	public OperationResult interrupt(String group, String name) {
		OperationResult result = new OperationResult();
		JobKey jobKey = JobKey.jobKey(name, group);

		try {
			if (!scheduler.interrupt(jobKey)) {
				result.setErrorMessage(String.format("Interrupt Job with name = \"%s\" group = \"%s\" failed!", name, group));
			}
		} catch (UnableToInterruptJobException e) {
			e.printStackTrace();
			result.setErrorMessage(String.format("Interrupt Job with name = \"%s\" group = \"%s\" failed, system error!", name, group));
		}

		return result;
	}

	@Override
	public OperationResult pause(String group, String name) {
		OperationResult result = new OperationResult();
		JobKey jobKey = JobKey.jobKey(name, group);

		try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
			result.setErrorMessage(String.format("Pause Job with name = \"%s\" group = \"%s\" failed, system error!", name, group));
		}

		return result;
	}

	@Override
	public OperationResult delete(String jobName, String jobGroup) {
		OperationResult result = new OperationResult();
		result.appendMessage(String.format("Removing Quartz job: %s", jobName));

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
			result.appendMessage(String.format("Update cron trigger %s succeeded!", triggerKey.getName()));
		} catch (SchedulerException e) {
			e.printStackTrace();
			result.setErrorMessage(String.format("Update cron trigger %s failed!", triggerKey.getName()));
		}

		return result;
	}
}