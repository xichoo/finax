package com.xichoo.finax.common.util;


import java.util.HashMap;

public class Result extends HashMap{

    public Result(int code, String msg) {
        put("code", code);
        put("msg", msg);
    }
}