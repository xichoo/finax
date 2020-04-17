package com.xichoo.finax.common.exception;

import com.xichoo.finax.common.util.Result;
import com.xichoo.finax.modules.system.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    @ResponseBody
    public Result cc(HttpServletRequest request, Exception e){
        log.error("未知的异常请求<{}>，错误信息：{}", request.getRequestURI(), e.getMessage());
        return Result.error();
    }

    /**
     * 权限认证失败
     */
    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception e) {
        getRequest().setAttribute("msg", e.getMessage());
        return "/error";
    }

}
