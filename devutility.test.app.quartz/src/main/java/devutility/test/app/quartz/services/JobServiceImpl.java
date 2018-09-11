package devutility.test.app.quartz.services;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
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
}