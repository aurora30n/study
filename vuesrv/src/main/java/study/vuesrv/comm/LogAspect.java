package study.vuesrv.comm;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    @Pointcut("execution(* study.vuesrv..*.*(..))" +
            " || execution(* study.vuesrv..*.*(..))" +
            " || execution(* study.vuesrv..*.*(..))")//切入点描述 这个是controller包的切入点，多个用|| && 关联
    public void log(){}//签名，可以理解成这个切入点的一个名称

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String clsName = joinPoint.getTarget().getClass().getSimpleName();
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        log.info("{}->{}->start", clsName, methodName);
        StopWatch sw = new StopWatch();
        sw.start();
        Object obj = joinPoint.proceed();
        sw.stop();
        log.info("{}->{}->-end->耗时:{}", clsName, methodName, sw.toString());
        return obj;
    }

}
