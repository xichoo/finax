package com.xichoo.finax.common.exception;

import com.alibaba.fastjson.JSON;
import com.xichoo.finax.common.util.Result;
import com.xichoo.finax.modules.system.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * @author xichoo@live.cn
 */
@Slf4j
@ControllerAdvice
public class GlobalException extends BaseController{

    /**
     * 运行异常捕获
     */
    @ExceptionHandler
    public String handleException(Exception e) throws IOException {
        log.error("未知的异常请求<{}>，错误信息：{}",
                getRequest().getRequestURI(), e.getMessage());
        if(isAjaxRequest()){
            getResponse().setContentType("application/json;charset=UTF-8");
            getResponse().getWriter().write(JSON.toJSONString(Result.error()));
        }else{
            getRequest().setAttribute("msg", e.getMessage());
            return "/error";
        }
        return null;
    }

    /**
     * 权限认证失败
     */
    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception e) throws IOException {
        if(isAjaxRequest()){
            getResponse().setContentType("application/json;charset=UTF-8");
            getResponse().getWriter().write(JSON.toJSONString(new Result(403, e.getMessage())));
        }else{
            getRequest().setAttribute("msg", e.getMessage());
            return "/error";
        }
        return null;
    }

}
