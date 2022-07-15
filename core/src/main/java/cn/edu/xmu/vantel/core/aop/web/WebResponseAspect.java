package cn.edu.xmu.vantel.core.aop.web;

import cn.edu.xmu.vantel.core.util.Common;
import cn.edu.xmu.vantel.core.util.ReturnObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WebResponseAspect {
    //Controller层切点
//    @Pointcut(value = "@within(org.springframework.web.bind.annotation.RestController) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    @Pointcut(value = "@within(org.springframework.web.bind.annotation.RestController) && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void webResponseAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("webResponseAspect()")
    public void doBefore(JoinPoint joinPoint) {
    }

    @Around("webResponseAspect()")
    public Object around(JoinPoint joinPoint) throws Throwable {
        Object obj;
        try {
            obj = ((ProceedingJoinPoint) joinPoint).proceed();
            if (obj instanceof ReturnObject) {
                ReturnObject ret = (ReturnObject) obj;
                obj = Common.decorateReturnObject(ret);
            }
        } catch (Throwable e) {
            throw e;
        }
        return obj;
    }
}
