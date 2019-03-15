package com.java1234.aop;

import com.java1234.annotation.SystemLog;
import com.java1234.entity.Log;
import com.java1234.service.LogService;
import com.java1234.util.UserIpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
@Aspect
@Component
public class SystemLogAspect {

    @Resource
    private LogService logService;

    @Pointcut("@annotation(com.java1234.annotation.SystemLog)")
    public void serviceAspect() {

    }
    @After("serviceAspect()")
    public void doServiceLog(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        try {
            Log log = new Log();
            log.setAddtime(new Date());
            log.setDescription(getDescription(joinPoint));
            log.setParam(joinPoint.getArgs()[0] == null ? "ÎÞ²ÎÊý" : joinPoint.getArgs()[0].toString());
            log.setIp(UserIpUtil.getIp(request));
            log.setUserType(getUserType(joinPoint));
            if (!log.getParam().equals("none")) {
                logService.insert(log);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    public static String getUserType(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String userType = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    userType = method.getAnnotation(SystemLog.class).userType();
                    break;
                }
            }
        }
        return userType;

    }
}
