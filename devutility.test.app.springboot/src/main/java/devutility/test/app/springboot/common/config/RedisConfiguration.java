package devutility.test.app.springboot.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import devutility.external.redis.models.SingleRedisInstance;
import devutility.external.redis.utils.pool.JedisPoolUtil;
import redis.clients.jedis.JedisPool;

/**
 * 
 * RedisConfiguration
 * 
 * @author: Aldwin Su
 * @version: 2019-09-22 13:18:12
 */
@PropertySource("classpath:redis.properties")
@Configuration
public class RedisConfiguration {
	@Bean
	@ConfigurationProperties("redis")
	public SingleRedisInstance singleRedisInstance() {
		return new SingleRedisInstance();
	}

	@Bean
	public JedisPool jedisPool(SingleRedisInstance singleRedisInstance) {
		return JedisPoolUtil.jedisPool(singleRedisInstance);
	}
}