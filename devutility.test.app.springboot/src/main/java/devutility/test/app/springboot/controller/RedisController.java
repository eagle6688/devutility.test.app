package devutility.test.app.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devutility.internal.models.OperationResult;
import devutility.test.app.springboot.service.RedisService;

@RestController
@RequestMapping("/redis")
public class RedisController extends BaseController {
	@Autowired
	private RedisService redisService;

	/**
	 * Produce count number message in Redis queue.
	 * http://localhost:9009/redis/queue-producer?key=p2p-queue-test&count=10000
	 * @param count Number of message.
	 * @return OperationResult
	 */
	@GetMapping("queue-producer")
	public OperationResult queueProducer(String key, int count) {
		return redisService.queueProducer(key, count);
	}
}