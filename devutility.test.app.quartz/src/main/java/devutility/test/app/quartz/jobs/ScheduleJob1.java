package devutility.test.app.quartz.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import devutility.test.app.quartz.common.Executor;

@DisallowConcurrentExecution
public class ScheduleJob1 implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Executor.execute("ScheduleJob1", 5000);

		JobDetail jobDetail = context.getJobDetail();
		System.out.println(String.format("key: %s", jobDetail.getKey()));
	}
}