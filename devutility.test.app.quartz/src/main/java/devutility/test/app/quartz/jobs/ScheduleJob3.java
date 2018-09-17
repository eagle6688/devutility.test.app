package devutility.test.app.quartz.jobs;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import devutility.test.app.quartz.common.Executor;

public class ScheduleJob3 implements InterruptableJob {
	private boolean interrupted = false;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Executor.execute("ScheduleJob3 sub-task 1", 1000);

		if (interrupted) {
			return;
		}

		Executor.execute("ScheduleJob3 sub-task 2", 1000);

		if (interrupted) {
			return;
		}

		Executor.execute("ScheduleJob3 sub-task 3", 1000);
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		interrupted = true;
	}
}