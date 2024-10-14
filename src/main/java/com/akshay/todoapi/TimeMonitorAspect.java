package com.akshay.todoapi;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {
    // @after,@before,@around, defines an advice i.e. when this method will execute
    // i.e. before, after or around(before and after) join point execution
    @Around("@annotation(TimeMonitor)")
    public void logTime(ProceedingJoinPoint joinPoint){ // Proceeding join point is used with around so that we can execute the join point
        long startTime = System.currentTimeMillis(); // start time of code

        try{
            // execute the join point
            joinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("Something Went Wrong");
            throw new RuntimeException(e);
        } finally {
            long endTime = System.currentTimeMillis();
            long totalExecutionTime = endTime - startTime;
            System.out.println("Time time of execution of the method is: " + totalExecutionTime + " millisecond");
        }
    }
}
