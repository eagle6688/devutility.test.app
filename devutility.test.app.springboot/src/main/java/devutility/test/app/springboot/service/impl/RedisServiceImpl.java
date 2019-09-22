package devutility.test.app.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devutility.internal.models.OperationResult;
import devutility.internal.util.RandomUtils;
import devutility.test.app.springboot.service.RedisService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 * RedisServiceImpl
 * 
 * @author: Aldwin Su
 * @version: 2019-09-22 13:17:34
 */
@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	private JedisPool jedisPool;

	@Override
	public OperationResult queueProducer(String key, int count) {
		try (Jedis jedis = jedisPool.getResource()) {
			for (int i = 0; i < count; i++) {
				jedis.lpush(key, getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		OperationResult result = new OperationResult();
		result.setData(count);
		return result;
	}

	private String getMessage() {
		return String.format("Hello Word! Index %d", RandomUtils.getNumber(1000000));
	}
}