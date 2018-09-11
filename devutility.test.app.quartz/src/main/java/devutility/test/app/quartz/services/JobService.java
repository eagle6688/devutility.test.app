package devutility.test.app.quartz.services;

import org.quartz.Job;
import org.quartz.SchedulerException;

public interface JobService {
	void start(String name, String group, Class<? extends Job> clazz) throws SchedulerException;
}