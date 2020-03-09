package com.xichoo.finax.modules.system.controller;

import com.alibaba.fastjson.JSON;
import com.xichoo.finax.modules.system.entity.Config;
import com.xichoo.finax.modules.system.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统参数管理控制器
 * @author xichoo@live.cn
 */

@Controller
@RequestMapping("/system/config")
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @RequestMapping("/save")
    @ResponseBody
    public String save(Model model){

        Config entity = new Config();
        entity.setParamKey("1");
        entity.setParamValue("2");
        entity.setRemark("");
        configService.saveOrUpdate(entity);

        return JSON.toJSONString(entity);
    }

}
