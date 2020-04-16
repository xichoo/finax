package com.xichoo.finax.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.common.annotation.OperationLog;
import com.xichoo.finax.common.util.Constant;
import com.xichoo.finax.common.util.Result;
import com.xichoo.finax.modules.system.entity.Dict;
import com.xichoo.finax.modules.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/list")
    @ResponseBody
    @OperationLog( value = "查询字典列表")
    public Object list(Integer parentId){
        if(parentId == null) parentId = 0;
        startPage();
        List<Dict> list = dictService.list(new QueryWrapper<Dict>().
                eq("parent_id", parentId).orderByDesc("create_date"));
        return pageData(list);
    }

    @GetMapping("/getList/{code}")
    @ResponseBody
    @OperationLog( value = "根据code获取字典")
    public Object getListByCode(@PathVariable String code){
        List<Dict> dictList = dictService.getListByCode(code);
        return jsonArray(dictList);
    }

    @GetMapping("/add/{type}/{id}")
    @OperationLog( value = "进入创建字典页面")
    public String add(@PathVariable Integer type, @PathVariable Long id){
        Dict dict = new Dict();
        if(Constant.OperationType.ADD.getType().equals(type)){
            dict.setParentId(id);
        }else if(Constant.OperationType.UPDATE.getType().equals(type)){
            dict = dictService.getById(id);
        }
        getRequest().setAttribute("entity", dict);
        return "/modules/system/dict/add";
    }

    @PostMapping("/add")
    @ResponseBody
    @OperationLog( value = "创建/更新字典")
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
    @OperationLog( value = "删除字典")
    public Result delete(@PathVariable String ids){
        dictService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.success();
    }

}
