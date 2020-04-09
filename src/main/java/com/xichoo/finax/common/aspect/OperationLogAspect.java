package com.xichoo.finax.common.aspect;

import com.xichoo.finax.common.util.IpUtil;
import com.xichoo.finax.modules.system.entity.OperationLog;
import com.xichoo.finax.modules.system.entity.User;
import com.xichoo.finax.modules.system.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author xichoo@live.cn
 */
@Aspect
@Component
@Slf4j
public class OperationLogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperationLogService operationLogService;

    @Pointcut("@annotation(com.xichoo.finax.common.annotation.OperationLog)")
    public void operationLog(){}

    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        try{
            this.saveLog(joinPoint);
        }catch (Exception e){
            log.error("OperationLog保存出错：{}", e.getMessage());
        }
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        com.xichoo.finax.common.annotation.OperationLog operationLog =
                method.getAnnotation(com.xichoo.finax.common.annotation.OperationLog.class);

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        OperationLog entity = new OperationLog();
        entity.setAction(operationLog.value());
        entity.setMethod(className + "." + methodName);
        entity.setUrl(request.getRequestURI());
        User loginUser = (User)SecurityUtils.getSubject().getPrincipal();
        entity.setUserId(loginUser.getId());
        entity.setUsername(loginUser.getUsername());
        entity.setIp(IpUtil.getIpAddr(request));
        entity.setParams("");
        entity.setResult("");
        entity.setCreateDate(new Date());
        operationLogService.save(entity);
    }

}
