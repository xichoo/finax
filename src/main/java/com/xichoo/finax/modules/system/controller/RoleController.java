package com.xichoo.finax.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.common.annotation.OperationLog;
import com.xichoo.finax.common.util.Result;
import com.xichoo.finax.modules.system.entity.Menu;
import com.xichoo.finax.modules.system.entity.Role;
import com.xichoo.finax.modules.system.service.MenuService;
import com.xichoo.finax.modules.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 角色管理控制器
 * @author xichoo@live.cn
 */

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController{
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;


    @PostMapping("/list")
    @ResponseBody
    @OperationLog( value = "查询角色列表")
    @RequiresPermissions("sys:role:list")
    public Object list(){
        startPage();
        List<Role> list = roleService.list(new QueryWrapper<Role>().orderByDesc("create_date"));
        return pageData(list);
    }

    @GetMapping("/add")
    @OperationLog( value = "进入创建角色页面")
    @RequiresPermissions("sys:role:add")
    public String add(Long id){
        Role role = roleService.getById(id);
        List<Menu> menuList = new ArrayList<>();
        if(role == null){
            role = new Role();
            menuList = menuService.list();
        }else{
            menuList = menuService.getListByRoleid(id);
        }
        StringBuffer menus = new StringBuffer();
        for(Menu menu : menuList){
            menus.append(menu.toString());
        }
        getRequest().setAttribute("menuList", menus.substring(0, menus.length()-1));
        getRequest().setAttribute("entity", role);
        return "/modules/system/role/add";
    }

    @PostMapping("/add")
    @ResponseBody
    @OperationLog( value = "创建/更新角色")
    @RequiresPermissions("sys:role:add")
    public Result add(Role role, String menuIds){
        if(role.getId() == null){
            role.setCreateDate(new Date());
        }
        roleService.saveRole(role, menuIds);
        return Result.success();
    }

    @GetMapping("/checkRolename")
    @ResponseBody
    public Result checkRolename(String id, String role){
        List<Role> list = roleService.list(new QueryWrapper<Role>().eq("role", role));
        boolean valid = list.size() == 0;
        Role entity = roleService.getById(id);
        if(entity!=null && list.size()>0){
            if(list.get(0).getRole().equals(entity.getRole())){
                valid = true;
            }
        }
        return new Result("valid", valid);
    }

    @GetMapping("/delete/{ids}")
    @ResponseBody
    @OperationLog( value = "删除角色")
    @RequiresPermissions("sys:role:delete")
    public Result delete(@PathVariable String ids){
        roleService.removeByIds(Arrays.asList(ids));
        return Result.success();
    }
}
