package bg.softuni.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class BasicPointcutSample {

    //*-те покриват някакви параметри 1-тип 2-метод
    @Pointcut("execution(* bg.softuni.aop.models.Student.sayHello (..))")
    public void trackSayHello(){}

    //@Before("trackSayHello()")
    public void  logBeforeMethod(){
        System.out.println("Before executing of hello!");
    }

    //@After("trackSayHello()")
    public void logAfterMethod(){
        System.out.println("After executing of hello!");
    }

    @Around("trackSayHello()")

    public Object logAroundHello(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Before execution");
        Object object=pjp.proceed();
        System.out.println("After execution");
        return object;
    }
}
