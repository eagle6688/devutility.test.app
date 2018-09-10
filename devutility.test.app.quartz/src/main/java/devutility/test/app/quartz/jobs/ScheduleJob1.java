package devutility.test.app.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScheduleJob1 implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("ScheduleJob1 executing...");

		JobDetail jobDetail = context.getJobDetail();
		System.out.println(String.format("key: %s", jobDetail.getKey()));
	}
}