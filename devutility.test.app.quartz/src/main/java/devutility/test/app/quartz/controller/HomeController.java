package devutility.test.app.quartz.controller;

import org.quartz.Job;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devutility.internal.models.OperationResult;
import devutility.test.app.quartz.services.JobService;

@RestController
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private JobService jobService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/job")
	public OperationResult job(String name) {
		OperationResult result = new OperationResult();
		result.setData(name);

		try {
			Class<?> clazz = Class.forName(String.format("devutility.test.app.quartz.jobs.%s", name));
			jobService.start(name, "group1", (Class<Job>) clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			result.setErrorMessage("Class not found!");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		return result;
	}
}