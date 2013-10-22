package com.multicampus.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class TimeCheckAdvice {
	@Pointcut("execution(* com.multicampus..*Service.*(..))")
	public void pointCut(){}
	
	@Around("pointCut()")
	public Object timeCheck(ProceedingJoinPoint jp) throws Throwable{
		long start = System.currentTimeMillis();
		
		Object obj = null;
		obj = jp.proceed();
		
		long end = System.currentTimeMillis();
		String method = jp.getSignature().getName();
		System.out.println(method+"() �޼ҵ� ����ð� : " + 
						  (end-start) + "(ms)��");
		return obj;
	}
}








