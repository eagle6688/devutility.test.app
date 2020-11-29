package devutility.test.app.springboot.common.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 
 * Pointcuts
 * 
 * @author: Aldwin Su
 * @creation: 2020-07-21 14:33:38
 */
public class Pointcuts {
	/**
	 * Pointcut for all controllers.
	 */
	@Pointcut("execution(public * devutility.test.app.springboot.controller..*.*(..))")
	public void pointcutForController() {

	}
}