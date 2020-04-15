package com.xichoo.finax.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.modules.system.entity.OperationLog;
import com.xichoo.finax.modules.system.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public Object list(HttpServletRequest request){
        startPage(request);
        List<OperationLog> list = service.list(new QueryWrapper<OperationLog>().orderByDesc("create_date"));
        return pageData(list);
    }

    @GetMapping("/view/{id}")
    public String view(HttpServletRequest request, @PathVariable String id){
        OperationLog entity = service.getById(id);
        request.setAttribute("entity", entity==null?new OperationLog():entity);
        return "/modules/system/operationLog/view";
    }

}
