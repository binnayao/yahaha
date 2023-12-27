package com.yahaha.aspect;

import com.alibaba.fastjson.JSON;
import com.yahaha.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.yahaha.annotation.SystemLog))")
    public void put() {

    }

    @Around("put()")
    public Object pointLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            // 结束后 换行
            log.info("=======================end=======================" + System.lineSeparator());
        }
        return ret;
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        log.info("======================Start======================");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //下面的几个log输出，第一个参数{}表示占位符，具体的值是第二个参数的变量
        if (requestAttributes instanceof ServletRequestAttributes attributes) {
            HttpServletRequest request = attributes.getRequest();
            // 打印请求 URL
            log.info("请求URL   : {}", request.getRequestURL());
            // 打印描述信息，例如获取UserController类的updateUserInfo方法上一行的@mySystemlog注解的描述信息
            log.info("接口描述   : {}", Objects.requireNonNull(getSystemLog(joinPoint)).BusinessName());
            // 打印 Http method
            log.info("请求方式   : {}", request.getMethod());
            // 打印调用 controller 的全路径(全类名)、方法名
            log.info("请求类名   : {}.{}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName()
            );
            // 打印请求的 IP
            log.info("访问IP    : {}", request.getRemoteHost());
            // 打印请求入参。JSON.toJSONString十FastJson提供的工具方法，能把数组转成JSON
            log.info("传入参数   : {}", JSON.toJSONString(joinPoint.getArgs()));
        }


    }

    private void handleAfter(Object o) {
        log.info("返回参数   : {}", JSON.toJSONString(o));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature sig) {
            return sig.getMethod().getAnnotation(SystemLog.class);
        }
        return null;
    }
}
