package com.sol.app.aops.pays;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class Card {
	
	@Around("execution(* com.sol.app.aops.transfers.Transfer.take*(..))")
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("==== Before Card Check ====");
		log.info("==== ARGS: {}====", Arrays.toString(joinPoint.getArgs()));
		Object obj = joinPoint.proceed();
		log.info("Return: {}", obj);
		log.info("==== After Card Check ====");
		
		return obj;
	}
	
}
