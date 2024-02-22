package io.github.wesleyosantos91.metric.aspect;

import io.github.wesleyosantos91.metric.annotation.TimerExecution;
import io.github.wesleyosantos91.metric.tag.timer.MultiTaggedTimer;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public record TimerExecutionAspect(MeterRegistry meterRegistry) {

    @Pointcut("@annotation(timerExecution)")
    public void timerExecutionPointcut(TimerExecution timerExecution) {
    }

    @Around("timerExecutionPointcut(timerExecution)")
    public Object timerExecutionAdvice(ProceedingJoinPoint joinPoint, TimerExecution timerExecution) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();


        MultiTaggedTimer multiTaggedTimer = new MultiTaggedTimer(
                timerExecution.name(),
                meterRegistry,
                "className", "methodName", "status", "message");

        String status = "SUCCESS";
        String message = "Successful executions";

        StopWatch stopWatch = new StopWatch();
        Object result;

        try {
            stopWatch.start();
            result = joinPoint.proceed();
            stopWatch.stop();
        } catch (Throwable throwable) {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            status = "ERROR";
            message = MessageFormat.format("Executions with exception {0}", throwable.getClass().getSimpleName());
            throw throwable;
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            multiTaggedTimer.getTimer(className, methodName, status, message)
                    .record(stopWatch.getTotalTimeMillis(), TimeUnit.MILLISECONDS);
        }

        return result;
    }
}
