package devutility.test.app.springboot.common.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * LoginAspect
 * 
 * @author: Aldwin Su
 * @creation: 2020-08-26 19:24:17
 */
@Aspect
@Component
public class LoginAspect {
	Log log = LogFactory.getLog(LoginAspect.class);

	@Around("devutility.test.app.springboot.common.aspect.Pointcuts.pointcutForController()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		log.info(request.getRequestURI());
		log.info(request.getQueryString());

		String status = request.getParameter("status");

		if (status != null) {
			int httpStatus = Integer.valueOf(status);

			/**
			 * Can't do in this way.
			 */
			return new ResponseEntity<Object>(null, HttpStatus.resolve(httpStatus));
		}

		return joinPoint.proceed();
	}
}