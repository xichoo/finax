package com.xichoo.finax.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xichoo@live.cn
 */
@Controller
public class GlobolController {

    /**
     * 系统模块
     */
    @GetMapping("/system/{module}/{url}")
    public String sys(@PathVariable("module")String module, @PathVariable("url")String url){
        return "/modules/system/" + module + "/" + url;
    }

    /**
     * 业务开发模块
     */
    @GetMapping("/fastdev/{module}/{url}")
    public String fastdev(@PathVariable("module")String module, @PathVariable("url")String url){
        return "/modules/fastdev/" + module + "/" + url;
    }



}
