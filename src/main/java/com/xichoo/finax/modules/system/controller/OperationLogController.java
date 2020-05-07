package com.xichoo.finax.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.modules.system.entity.OperationLog;
import com.xichoo.finax.modules.system.service.OperationLogService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志控制器
 * @author xichoo@live.cn
 */
@Controller
@RequestMapping("/system/operationLog")
public class OperationLogController extends BaseController{
    @Autowired
    private OperationLogService service;


    @PostMapping("/list")
    @ResponseBody
    public Object list(String keyword){
        startPage();
        List<OperationLog> list = service.list(new QueryWrapper<OperationLog>()
                .like(Strings.isNotBlank(keyword), "username", keyword)
                .or()
                .like(Strings.isNotBlank(keyword), "action", keyword)
                .orderByDesc("create_date"));
        return pageData(list);
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable String id){
        OperationLog entity = service.getById(id);
        getRequest().setAttribute("entity", entity==null?new OperationLog():entity);
        return "/modules/system/operationLog/view";
    }

}
