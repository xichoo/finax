package com.xichoo.finax.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.common.annotation.OperationLog;
import com.xichoo.finax.common.util.Constant;
import com.xichoo.finax.common.util.Result;
import com.xichoo.finax.modules.system.entity.User;
import com.xichoo.finax.modules.system.service.UserService;
import org.apache.logging.log4j.util.Strings;
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


    @PostMapping("/list")
    @ResponseBody
    @OperationLog( value = "查询用户列表")
    public Object list(){
        startPage();
        List<User> list = userService.list(new QueryWrapper<User>().orderByDesc("create_date"));
        return pageData(list);
    }


    @GetMapping("/add")
    @OperationLog( value = "进入创建用户页面")
    public String add(String id){
        User user = userService.getById(id);
        getRequest().setAttribute("entity", user==null?new User():user);
        return "/modules/system/user/add";
    }

    @PostMapping("/add")
    @ResponseBody
    @OperationLog( value = "创建/更新用户")
    public Result add(User user){
        if(user.getId() == null){
            user.setCreateDate(new Date());
        }
        if(Strings.isNotBlank(user.getPassword())){
            user.setPassword(Constant.getPassword(user.getPassword(), user.getUsername()));
        }
        userService.saveOrUpdate(user);
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
