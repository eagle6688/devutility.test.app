package devutility.test.app.quartz.controller;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devutility.internal.models.OperationResult;

@RestController
@RequestMapping("/spring-job")
public class SpringJobController {
	@Autowired
	private Scheduler schedulerFactory1;

	@Autowired
	private JobDetail jobDetailFactory1;

	@RequestMapping("/delete")
	public OperationResult delete() {
		OperationResult result = new OperationResult();

		JobKey jobKey = jobDetailFactory1.getKey();
		String key = jobKey.getName();
		String message = String.format("Removing Job key %s", key);
		result.append(message);

		try {
			if (!schedulerFactory1.deleteJob(jobKey)) {
				result.setErrorMessage(String.format("Removing Job key %s failed!", key));
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			result.setErrorMessage(String.format("Removing Job key %s failed with error!", key));
		}

		return result;
	}
}