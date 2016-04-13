package com.emrekoca.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformTimingAdvice {
	@Around(value = "execution ( * com.emrekoca.services.customers..*.* (..) )")
	public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable {
		final long start = System.currentTimeMillis();
		try {
			Object returnValue = method.proceed();
			return returnValue;
		} finally {
			final double responseTiming = (double) (System.currentTimeMillis() - start) / 1000;
			System.out.println("Performance took " + responseTiming 
					+ " ms in method name: " + method.getSignature());
		}
	}

}
