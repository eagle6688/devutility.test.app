package devutility.test.app.quartz.services;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.SchedulerException;

import devutility.internal.models.OperationResult;

public interface JobService {
	void start(String name, String group, String cronExpression, Class<? extends Job> clazz) throws SchedulerException;

	OperationResult pause(String group, String name);

	OperationResult interrupt(String group, String name);

	OperationResult delete(String jobName, String jobGroup);

	OperationResult update(CronTrigger cronTrigger, String cronExpression);
}