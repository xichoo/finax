package com.xichoo.finax.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.common.annotation.OperationLog;
import com.xichoo.finax.common.util.Constant;
import com.xichoo.finax.common.util.Result;
import com.xichoo.finax.modules.system.entity.Role;
import com.xichoo.finax.modules.system.entity.User;
import com.xichoo.finax.modules.system.entity.UserRole;
import com.xichoo.finax.modules.system.service.RoleService;
import com.xichoo.finax.modules.system.service.UserRoleService;
import com.xichoo.finax.modules.system.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 用户管理控制器
 * @author xichoo@live.cn
 */

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/list")
    @ResponseBody
    @RequiresPermissions("sys:user:list")
    public Object list(){
        startPage();
        List<User> list = userService.list(new QueryWrapper<User>().orderByDesc("create_date"));
        return pageData(list);
    }


    @GetMapping("/add")
    @RequiresPermissions("sys:user:add")
    public String add(String id){
        User user = userService.getById(id);
        //查询角色信息
        StringBuffer roles = new StringBuffer();
        List<Role> roleList = roleService.list();
        List<UserRole> userroleList = userRoleService.list(
                new QueryWrapper<UserRole>().eq("user_id", id));
        if(roleList!=null && !roleList.isEmpty()){
            for(Role role : roleList){
                String checked = new String();
                if(userroleList!=null && !userroleList.isEmpty()){
                    for(UserRole userRole : userroleList){
                        if(userRole.getRoleId().equals(role.getId())){
                            checked = "checked";
                            break;
                        }
                    }
                }
                roles.append("<div class=\"form-check form-check-inline\">" +
                        "        <label class=\"form-check-label\">" +
                        "            <input type=\"checkbox\" class=\"form-check-input\" " +
                        "                name=\"role\" value="+ role.getId() +" "+ checked +">" + role.getDescription() +
                        "        </label>" +
                        "    </div>");
            }
        }
        getRequest().setAttribute("entity", user);
        getRequest().setAttribute("roles", roles);
        return "/modules/system/user/add";
    }

    @PostMapping("/add")
    @ResponseBody
    @OperationLog( value = "保存用户信息")
    @RequiresPermissions("sys:user:add")
    public Result add(User user, String[] role){
        if(user.getId() == null){
            user.setCreateDate(new Date());
        }
        if(Strings.isNotBlank(user.getPassword())){
            user.setPassword(Constant.getPassword(user.getPassword(), user.getUsername()));
        }
        userService.saveUser(user, role);
        return Result.success();
    }

    @GetMapping("/checkUsername")
    @ResponseBody
    public Result checkUsername(String id, String username){
        List<User> list = userService.list(new QueryWrapper<User>().eq("username", username));
        boolean valid = list.size() == 0;
        User user = userService.getById(id);
        if(user!=null && list.size()>0){
            if(list.get(0).getUsername().equals(user.getUsername())){
                valid = true;
            }
        }
        return new Result("valid", valid);
    }

    @GetMapping("/delete/{ids}")
    @ResponseBody
    @OperationLog( value = "删除用户")
    @RequiresPermissions("sys:user:delete")
    public Result delete(@PathVariable String ids){
        List<String> list = new ArrayList<>();
        String[] ids_ = ids.split(",");
        for(int i=0;i<ids_.length;i++){
            if(!"1".equals(ids_[i])){
                list.add(ids_[i]);
            }
        }
        userService.removeByIds(list);
        return Result.success();
    }
}
