package com.xichoo.finax.common.aspect;

import com.alibaba.fastjson.JSON;
import com.xichoo.finax.common.util.XxUtil;
import com.xichoo.finax.modules.system.entity.OperationLog;
import com.xichoo.finax.modules.system.entity.User;
import com.xichoo.finax.modules.system.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
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


    /**
     * 环绕增强
     */
    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        String result = null;
        try {
            res = joinPoint.proceed();
        } catch (Throwable t) {
            result = t.getMessage();
            throw t;
        } finally {
            this.saveLog(joinPoint, result);
        }
        return res;
    }

    /**
     * 保存操作日志
     */
    private void saveLog(ProceedingJoinPoint joinPoint, String result){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        com.xichoo.finax.common.annotation.OperationLog operationLog =
                method.getAnnotation(com.xichoo.finax.common.annotation.OperationLog.class);

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        try{
            OperationLog entity = new OperationLog();
            entity.setAction(operationLog.value());
            entity.setMethod(className + "." + methodName);
            entity.setUrl("["+ request.getMethod() +"] " + request.getRequestURI());
            User loginUser = (User)SecurityUtils.getSubject().getPrincipal();
            entity.setUserId(loginUser.getId());
            entity.setUsername(loginUser.getUsername());
            entity.setIp(XxUtil.getIpAddr(request));
            entity.setResult(result);
            entity.setCreateDate(new Date());
            //请求参数
            Object[] args = joinPoint.getArgs();
            if(args != null && args.length > 0){
                entity.setParams(JSON.toJSONString(args[0]));
            }
            operationLogService.save(entity);
        }catch (Exception e){
            log.error("operationLog异常：{}", e.getMessage());
        }
    }

}
