package ru.ma1ko.cafeteria.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Aspect
public class LoggingAspect {
    @Pointcut("execution(public void ru.ma1ko.cafeteria.app.Menu.run())")
    private void menuRun() {
    }

    @Pointcut("execution(public int ru.ma1ko.cafeteria.app.Input.choice(String, int, int))")
    private void inputChoice() {
    }

    @Pointcut("execution(public java.math.BigDecimal ru.ma1ko.cafeteria.strategy.PriceStrategy+.total(..))")
    private void strategyTotal() {
    }

    @Pointcut("execution(public void ru.ma1ko.cafeteria.observer.Observer+.onChange(..))")
    private void observerNotify() {
    }

    @Pointcut("within(ru.ma1ko.cafeteria.app..*) || within(ru.ma1ko.cafeteria.strategy..*) || within(ru.ma1ko.cafeteria.observer..*)")
    private void loggedBeans() {
    }

    @Before("menuRun()")
    public void beforeMenuRun() {
        System.out.println("[AOP][menu] application menu started");
    }

    @AfterReturning("menuRun()")
    public void afterMenuRun() {
        System.out.println("[AOP][menu] application menu finished");
    }

    @Before("inputChoice()")
    public void beforeChoice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("[AOP][input] waiting for choice in range " + args[1] + ".." + args[2]);
    }

    @AfterReturning(pointcut = "inputChoice()", returning = "choice")
    public void afterChoice(int choice) {
        System.out.println("[AOP][input] selected choice: " + choice);
    }

    @AfterReturning(pointcut = "strategyTotal()", returning = "total")
    public void afterStrategyTotal(BigDecimal total) {
        System.out.println("[AOP][strategy] calculated total: " + total);
    }

    @Before("observerNotify()")
    public void beforeObserverNotify(JoinPoint joinPoint) {
        System.out.println("[AOP][observer] notify " + joinPoint.getTarget().getClass().getSimpleName());
    }

    @AfterThrowing(pointcut = "loggedBeans() && execution(public * *(..))", throwing = "exception")
    public void afterBeanException(JoinPoint joinPoint, Throwable exception) {
        System.out.println("[AOP][error] " + joinPoint.getSignature().toShortString()
                + ": " + exception.getMessage());
    }
}
