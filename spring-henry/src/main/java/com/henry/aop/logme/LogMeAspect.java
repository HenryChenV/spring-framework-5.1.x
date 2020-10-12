package com.henry.aop.logme;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author henrychen
 * @date created at 2020/10/12 4:57 下午
 */

@Aspect
public class LogMeAspect {

	@Pointcut("within(com.henry.aop..*)")
	public void allMethodsPointcut() {}

	@Pointcut("@within(com.henry.aop.logme.LogMe) || @annotation(com.henry.aop.logme.LogMe)")
	public void logMePointcut() {}

	@Around("logMePointcut()")
	public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("^-^ log before >>>>>>>>>>>>>>>>>>>>>>>>>>");

		Object retVal = pjp.proceed();

		System.out.println("^-^ log after <<<<<<<<<<<<<<<<<<<<<<<<<<<");

		return retVal;
	}

}
