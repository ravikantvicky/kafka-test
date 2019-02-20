package com.stg.kafkacons.aspects;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.annotation.KafkaListener;

@Aspect
@Configuration
public class KafkaConsumerAspect {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("@annotation(org.springframework.kafka.annotation.KafkaListener)")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		joinPoint.proceed();
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();

		KafkaListener annotation = method.getAnnotation(KafkaListener.class);
		String topic = annotation.topics().toString();

		logger.info("Topic Value: {}", topic);

		Object argData = joinPoint.getArgs()[0];
		
		logger.info("Argument Data: {}", argData);
		
		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
	}
}
