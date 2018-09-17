package devutility.test.app.quartz.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import devutility.test.app.quartz.common.Executor;

@DisallowConcurrentExecution
public class ScheduleJob2 implements InterruptableJob {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Executor.execute(context, 5000);
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		System.out.println("ScheduleJob2 is interrupting now。。。");
	}
}