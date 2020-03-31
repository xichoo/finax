package com.xichoo.finax.modules.system.controller;

import com.xichoo.finax.modules.system.entity.User;
import com.xichoo.finax.modules.system.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 用户管理控制器
 * @author xichoo@live.cn
 */

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    /**
     * 加载用户数据
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(HttpServletRequest request){
        startPage(request);
        List<User> list = userService.list();
        return pageData(list);
    }

    @GetMapping("/add/{id}")
    public String add(HttpServletRequest request, @PathVariable String id){
        User user = userService.getById(id);
        request.setAttribute("entity", user==null?new User():user);
        return "/modules/system/user/add";
    }

    @PostMapping("/add")
    @ResponseBody
    public boolean add(User user){
        if(user.getId() == null){
            user.setCreateDate(new Date());
        }
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/delete/{ids}")
    @ResponseBody
    public boolean delete(@PathVariable String ids){
        return userService.removeByIds(Arrays.asList(ids.split(",")));
    }
}
