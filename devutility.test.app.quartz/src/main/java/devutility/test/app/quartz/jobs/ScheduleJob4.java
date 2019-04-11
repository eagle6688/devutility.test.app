package devutility.test.app.quartz.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import devutility.test.app.quartz.common.Executor;

@DisallowConcurrentExecution
public class ScheduleJob4 implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Executor.execute(context, 60000);
	}
}