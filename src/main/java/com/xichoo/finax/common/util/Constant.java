package com.xichoo.finax.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 常量
 * @author xichoo@live.cn
 */
public class Constant {

    /**
     * 密码错误超限后增加验证码
     */
    public static final int MAX_ERROR_COUNT = 3;

    /**
     * 密码加密算法
     */
    public static final String SHIRO_ALGORITHMNAME = "MD5";

    /**
     * 登陆状态
     */
    public enum LoginState {
        SUCCESS(1, "登陆成功"),
        CAPACHA(2, "进行登陆验证"),
        ERROR(3, "登陆失败");

        private Integer code;
        private String msg;

        LoginState(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 密码加密
     */
    public static String getPassword(String source, String salt){
        return new SimpleHash(SHIRO_ALGORITHMNAME, source, salt, 1).toString();
    }

}
