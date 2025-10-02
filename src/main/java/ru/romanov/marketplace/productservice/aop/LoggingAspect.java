package ru.romanov.marketplace.productservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private static final String START_MSG = "Начало выполнения {}.{}() с параметрами: {}";

    private static final String SUCCESS_MSG = "Успешное выполнение {}.{}() за {} мс. Результат: {}";

    private static final String ERROR_MSG = "Ошибка в {}.{}() после {} мс: {}";

    @Around("@annotation(logging)")
    public Object loggingMethod(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        String className = joinPoint.getTarget().getClass().getName();

        logAtLevel(logging.level(), START_MSG, className, methodName,
                logging.logParams() ? Arrays.toString(joinPoint.getArgs()) : "[скрыто]");

        long startTime = System.currentTimeMillis();
        Object result;

        try {
            result = joinPoint.proceed();

            long executionTime = System.currentTimeMillis() - startTime;

            logAtLevel(logging.level(), SUCCESS_MSG, className, methodName, executionTime,
                    logging.logResult() ? result : "[скрыто]");

            return result;
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;

            log.error(ERROR_MSG, className, methodName, executionTime, e.getMessage(), e);
            throw e;
        }
    }

    private void logAtLevel(Logging.Level level, String format, Object... args) {
        switch (level) {
            case DEBUG -> log.debug(format, args);
            case INFO -> log.info(format, args);
            case WARN -> log.warn(format, args);
            case ERROR -> log.error(format, args);
            default -> log.info(format, args);
        }
    }
}
