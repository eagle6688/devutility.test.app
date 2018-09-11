package devutility.test.app.quartz.controller;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devutility.internal.models.OperationResult;
import devutility.test.app.quartz.services.JobService;

@RestController
@RequestMapping("/spring-job")
public class SpringJobController {
	@Autowired
	private JobService jobService;

	@Qualifier("jobDetailFactory1")
	@Autowired
	private JobDetail jobDetail;

	@Qualifier("cronTriggerFactory2")
	@Autowired
	private CronTrigger cronTrigger;

	@RequestMapping("/delete")
	public OperationResult delete() {
		JobKey jobKey = jobDetail.getKey();
		return jobService.delete(jobKey.getName(), jobKey.getGroup());
	}

	@RequestMapping("/update")
	public OperationResult update() {
		System.out.println(cronTrigger.getCronExpression());
		System.out.println(cronTrigger.getKey().getName());
		return jobService.update(cronTrigger, "0/2 * * * * ?");
	}
}