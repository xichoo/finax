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
 * 异常处理
 * @author xichoo@live.cn
 */
@Slf4j
@ControllerAdvice
public class GlobalException extends BaseController{

    @ExceptionHandler
    public String handleException(Exception e) throws IOException {
        if(e instanceof UnauthorizedException){
            return defaultHandle(e, JSON.toJSONString(new Result(500, e.getMessage())));
        }else{
            log.error("未知的异常请求<{}>，错误信息：{}",
            this.defaultHandle(e, JSON.toJSONString(Result.error())));
        }
        return null;
    }

    public String defaultHandle(Exception e, String msg) throws IOException {
        if(isAjaxRequest()){
            getResponse().setContentType("application/json;charset=UTF-8");
            getResponse().getWriter().write(msg);
        }else{
            getRequest().setAttribute("msg", e.getMessage());
            return "/error";
        }
        return null;
    }

}
