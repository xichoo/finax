package com.xichoo.finax.common.util;


import java.util.HashMap;

public class Result extends HashMap{
    private static final long serialVersionUID = 1L;

    public Result(int code, String msg) {
        put("code", code);
        put("msg", msg);
    }

    public static Result success(){
        return new Result(0, "操作成功");
    }

    public static Result error(){
        return new Result(500, "系统繁忙，请稍后再试");
    }

    public Result(String key, Object value){
        put(key, value);
    }
}