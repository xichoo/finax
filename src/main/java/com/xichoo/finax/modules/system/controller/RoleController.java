package com.xichoo.finax.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.common.annotation.OperationLog;
import com.xichoo.finax.common.util.Result;
import com.xichoo.finax.modules.system.entity.Role;
import com.xichoo.finax.modules.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    @PostMapping("/list")
    @ResponseBody
    @OperationLog( value = "查询角色列表")
    public Object list(){
        startPage();
        List<Role> list = roleService.list(new QueryWrapper<Role>().orderByDesc("create_date"));
        return pageData(list);
    }


    @GetMapping("/add")
    @OperationLog( value = "进入创建角色页面")
    public String add(String id){
        Role role = roleService.getById(id);
        getRequest().setAttribute("entity", role==null?new Role():role);
        return "/modules/system/role/add";
    }

    @PostMapping("/add")
    @ResponseBody
    @OperationLog( value = "创建/更新角色")
    public Result add(Role role){
        if(role.getId() == null){
            role.setCreateDate(new Date());
        }

        roleService.saveOrUpdate(role);
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
    public Result delete(@PathVariable String ids){
        List<String> list = new ArrayList<>();
        String[] ids_ = ids.split(",");
        for(int i=0;i<ids_.length;i++){
            if(!"1".equals(ids_[i])){
                list.add(ids_[i]);
            }
        }
        roleService.removeByIds(list);
        return Result.success();
    }
}
