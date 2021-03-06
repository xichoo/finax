package com.xichoo.finax.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.common.annotation.OperationLog;
import com.xichoo.finax.common.util.Constant;
import com.xichoo.finax.common.util.Result;
import com.xichoo.finax.modules.system.entity.Menu;
import com.xichoo.finax.modules.system.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 菜单管理控制器
 * @author xichoo@live.cn
 */

@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController{
    @Autowired
    private MenuService menuService;


    @PostMapping("/list")
    @ResponseBody
    @RequiresPermissions("sys:menu:list")
    public Object list(Integer parentId){
        if(parentId == null) parentId = 0;
        startPage();
        List<Menu> list = menuService.list(new QueryWrapper<Menu>().
                eq("parent_id", parentId).orderByAsc("orderby"));
        return pageData(list);
    }

    @GetMapping("/add/{type}/{id}")
    @RequiresPermissions("sys:menu:add")
    public String add(@PathVariable Integer type, @PathVariable Long id, Integer menuType){
        Menu menu = new Menu();
        if(Constant.OperationType.ADD.getType().equals(type)){
            menu.setParentId(id);
            menu.setMenuType(menuType);
        }else if(Constant.OperationType.UPDATE.getType().equals(type)){
            menu = menuService.getById(id);
        }
        getRequest().setAttribute("entity", menu);
        return "/modules/system/menu/add";
    }

    @PostMapping("/add")
    @ResponseBody
    @OperationLog( value = "保存菜单信息")
    @RequiresPermissions("sys:menu:add")
    public Result add(Menu menu){
        if(menu.getId() == null){
            menu.setCreateDate(new Date());
        }
        //设置排序值
        if(menu.getOrderby() == null){
            List<Menu> list = menuService.list(new QueryWrapper<Menu>().
                    eq("parent_id", menu.getParentId()).orderByDesc("orderby"));
            if(list !=null && !list.isEmpty()){
                menu.setOrderby(list.get(0).getOrderby() + 1);
            }else{
                menu.setOrderby(1);
            }
        }
        menuService.saveOrUpdate(menu);
        return Result.success();
    }

    @GetMapping("/delete/{ids}")
    @ResponseBody
    @OperationLog( value = "删除菜单")
    @RequiresPermissions("sys:menu:delete")
    public Result delete(@PathVariable String ids){
        menuService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.success();
    }

}
