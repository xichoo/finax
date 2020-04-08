package com.xichoo.finax.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.common.util.Constant;
import com.xichoo.finax.common.util.Result;
import com.xichoo.finax.modules.system.entity.Dict;
import com.xichoo.finax.modules.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 数据字典管理控制器
 * @author xichoo@live.cn
 */

@Controller
@RequestMapping("/system/dict")
public class DictController extends BaseController{
    @Autowired
    private DictService dictService;

    /**
     * 加载字典数据
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(HttpServletRequest request, Integer parentId){
        if(parentId == null) parentId = 0;
        startPage(request);
        List<Dict> list = dictService.list(new QueryWrapper<Dict>().
                eq("parent_id", parentId).orderByDesc("create_date"));
        return pageData(list);
    }

    @GetMapping("/add/{type}/{id}")
    public String add(HttpServletRequest request, @PathVariable Integer type, @PathVariable Long id){
        Dict dict = new Dict();
        if(Constant.OperationType.ADD.getType().equals(type)){
            dict.setParentId(id);
        }else if(Constant.OperationType.UPDATE.getType().equals(type)){
            dict = dictService.getById(id);
        }
        request.setAttribute("entity", dict);
        return "/modules/system/dict/add";
    }

    @PostMapping("/add")
    @ResponseBody
    public Result add(Dict dict){
        if(dict.getId() == null){
            dict.setCreateDate(new Date());
        }
        //设置排序值
        if(dict.getOrderby() == null){
            List<Dict> list = dictService.list(new QueryWrapper<Dict>().
                    eq("parent_id", dict.getParentId()).orderByDesc("orderby"));
            if(list !=null && !list.isEmpty()){
                dict.setOrderby(list.get(0).getOrderby() + 1);
            }else{
                dict.setOrderby(1);
            }
        }
        dictService.saveOrUpdate(dict);
        return Result.success();
    }

    @GetMapping("/checkDictCode")
    @ResponseBody
    public Result checkDictCode(String id, String code){
        List<Dict> list = dictService.list(new QueryWrapper<Dict>().eq("code", code));
        boolean valid = list.size() == 0;
        Dict user = dictService.getById(id);
        if(user!=null && list.size()>0){
            if(list.get(0).getCode().equals(user.getCode())){
                valid = true;
            }
        }
        return new Result("valid", valid);
    }

    @GetMapping("/delete/{ids}")
    @ResponseBody
    public Result delete(@PathVariable String ids){
        dictService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.success();
    }

}
