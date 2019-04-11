package devutility.test.app.quartz.common;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;

/**
 * 
 * QuartzUtils
 * 
 * @author: Aldwin Su
 * @date: 2019-04-11 14:54:48
 */
public class QuartzUtils {
	public static void start(Scheduler scheduler, String expression, Class<? extends Job> clazz) throws SchedulerException {
		start(scheduler, clazz.getName(), "default", expression, clazz);
	}

	public static void start(Scheduler scheduler, String name, String group, String expression, Class<? extends Job> clazz) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(name, group).build();

		if (scheduler.checkExists(jobDetail.getKey())) {
			throw new SchedulerException(String.format("Job %s has existed!", jobDetail.getKey().toString()));
		}

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(expression);
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName(name), group).withSchedule(scheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, trigger);

		if (!scheduler.isStarted()) {
			scheduler.start();
		}
	}

	public static String triggerName(String jobName) {
		return String.format("trigger_%s", jobName);
	}
}