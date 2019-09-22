package devutility.test.app.springboot.service;

import devutility.internal.models.OperationResult;

/**
 * 
 * RedisService
 * 
 * @author: Aldwin Su
 * @version: 2019-09-22 13:16:54
 */
public interface RedisService {
	OperationResult queueProducer(String key, int count);
}