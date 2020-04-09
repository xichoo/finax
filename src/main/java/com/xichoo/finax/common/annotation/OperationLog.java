package com.xichoo.finax.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义操作日志注解
 * @author xichoo@live.cn
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface OperationLog {

    String value() default "";

}
