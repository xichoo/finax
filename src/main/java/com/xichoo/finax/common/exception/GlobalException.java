package com.xichoo.finax.common.exception;

import com.xichoo.finax.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xichoo@live.cn
 */
@Slf4j
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    @ResponseBody
    public Result cc(HttpServletRequest request, Exception e){
        log.error("监听到的异常请求<{}>，错误信息：{}", request.getRequestURI(), e.getMessage());
        return Result.error();
    }

}
