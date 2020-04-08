package com.xichoo.finax.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.common.util.Result;
import com.xichoo.finax.modules.system.entity.Config;
import com.xichoo.finax.modules.system.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 系统参数管理控制器
 * @author xichoo@live.cn
 */

@Controller
@RequestMapping("/system/config")
public class ConfigController extends BaseController{
    @Autowired
    private ConfigService configService;

    /**
     * 加载用户数据
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(HttpServletRequest request){
        startPage(request);
        List<Config> list = configService.list(new QueryWrapper<Config>().orderByDesc("create_date"));
        return pageData(list);
    }

    /**
     * 根据key获取value
     */
    @GetMapping("/getValue/{key}")
    @ResponseBody
    public Object getListByCode(@PathVariable String key){
        return configService.getListByCode(key);
    }

    @GetMapping("/add/{id}")
    public String add(HttpServletRequest request, @PathVariable String id){
        Config config = configService.getById(id);
        request.setAttribute("entity", config==null?new Config():config);
        return "/modules/system/config/add";
    }

    @PostMapping("/add")
    @ResponseBody
    public Result add(Config config){
        if(config.getId() == null){
            config.setCreateDate(new Date());
        }
        configService.saveOrUpdate(config);
        return Result.success();
    }

    @GetMapping("/checkUsername")
    @ResponseBody
    public Result checkParamKey(String id, String paramKey){
        List<Config> list = configService.list(new QueryWrapper<Config>().eq("param_key", paramKey));
        boolean valid = list.size() == 0;
        Config config = configService.getById(id);
        if(config!=null && list.size()>0){
            if(list.get(0).getParamKey().equals(config.getParamKey())){
                valid = true;
            }
        }
        return new Result("valid", valid);
    }

    @GetMapping("/delete/{ids}")
    @ResponseBody
    public Result delete(@PathVariable String ids){
        configService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.success();
    }

}
