package devutility.test.app.quartz.controller;

import org.quartz.Job;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devutility.internal.models.OperationResult;
import devutility.test.app.quartz.services.JobService;

@RestController
@RequestMapping("/job")
public class JobController {
	@Autowired
	private JobService jobService;

	@RequestMapping("/start")
	public OperationResult start(String name) {
		OperationResult result = new OperationResult();
		result.setData(name);

		try {
			Class<?> clazz = Class.forName(String.format("devutility.test.app.quartz.jobs.%s", name));

			@SuppressWarnings("unchecked")
			Class<Job> jobClazz = (Class<Job>) clazz;

			jobService.start(name, "group1", "0/3 * * * * ?", jobClazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			result.setErrorMessage("Class not found!");
		} catch (SchedulerException e) {
			e.printStackTrace();
			result.setErrorMessage("System error!");
		}

		return result;
	}

	@RequestMapping("/interrupt")
	public OperationResult interrupt(String name) {
		return jobService.interrupt("group1", name);
	}

	@RequestMapping("/pause")
	public OperationResult pause(String name) {
		return jobService.pause("group1", name);
	}
}