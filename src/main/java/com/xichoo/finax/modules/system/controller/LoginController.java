package com.xichoo.finax.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.common.util.Constant;
import com.xichoo.finax.common.util.Result;
import com.xichoo.finax.modules.system.entity.Menu;
import com.xichoo.finax.modules.system.entity.User;
import com.xichoo.finax.modules.system.service.LoginLogService;
import com.xichoo.finax.modules.system.service.MenuService;
import com.xichoo.finax.modules.system.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;


/**
 * 登录控制器
 * @author xichoo@live.cn
 */

@Controller
public class LoginController extends BaseController{
    @Autowired
    private UserService service;
    @Autowired
    private MenuService menuService;
    @Autowired
    private LoginLogService loginLogService;

    @GetMapping("/login")
    public String login(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            SecurityUtils.getSubject().logout();
        }
        return "/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password){
        String msg = "请输入用户名和密码";
        if(Strings.isBlank(username) || Strings.isBlank(password)){
            return new Result(Constant.LoginState.ERROR.getCode(), msg);
        }
        password = new String(Base64.getDecoder().decode(password));

        // 登陆错误次数
        int errorcount = 0;
        User user = service.getOne(new QueryWrapper<User>().eq("username", username));
        if(user != null && user.getErrorcount() != null) {
            errorcount = user.getErrorcount();
            getSession().setAttribute("loginUser", user);
        }
        if(errorcount >= Constant.MAX_ERROR_COUNT){
            return new Result(Constant.LoginState.CAPACHA.getCode(), null);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            subject.login(token);
            user.setErrorcount(0);
            service.updateById(user);
            loginLogService.save(user, getRequest());
            return new Result(Constant.LoginState.SUCCESS.getCode(), msg);
        }catch (UnknownAccountException e){
            msg = "用户名或密码错误";
        }catch (IncorrectCredentialsException e){
            msg = "用户名或密码错误";
        }catch (AuthenticationException e){
            msg = "登录失败";
        }
        if(user != null){
            user.setErrorcount(++errorcount);
            service.updateById(user);
        }
        return new Result(Constant.LoginState.ERROR.getCode(), msg);
    }

    @GetMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:login";
    }

    @GetMapping("/sliderCaptcha")
    public String toSliderCaptcha(){
        return "/sliderCaptcha";
    }

    @PostMapping("/sliderCaptcha")
    public void sliderCaptcha(){
        User user = (User) getSession().getAttribute("loginUser");
        if(user != null){
            user.setErrorcount(user.getErrorcount() - 1);
            service.updateById(user);
        }
    }

    @GetMapping("/index")
    public String index(){
        Object menusS = getSession().getAttribute("menus");
        if(menusS == null){
            StringBuffer menus = new StringBuffer();
            // 加载一级菜单
            List<Menu> menuList1 = menuService.getListByUserid(
                    currentUser().getId(), Constant.MenuType.FIRST.getType());
            for(Menu menu : menuList1){
                menus.append("<li class=\"nav-item has-treeview\">");
                List<Menu> menuList2 = menuService.getListByUseridAndPid(
                        currentUser().getId(), menu.getId(), Constant.MenuType.SECOND.getType());
                // 加载二级菜单
                if(!menuList2.isEmpty()){
                    menus.append("<a href=\"#\" class=\"nav-link\">" +
                            "     <i class=\"nav-icon fas "+ menu.getIcon() +"\"></i>" +
                            "     <p>"+ menu.getName() +"<i class=\"fas fa-angle-left right\"></i></p>" +
                            " </a>");

                    menus.append("<ul class=\"nav nav-treeview\">");
                    for(Menu menu2 : menuList2){
                        menus.append("<li class=\"nav-item\">" +
                                "     <a href="+ menu2.getUrl() +" class=\"menu_link nav-link\">" +
                                "         <i class=\"fas "+ menu2.getIcon() +" nav-icon\"></i>" +
                                "         <p>"+ menu2.getName() +"</p>" +
                                "     </a>" +
                                " </li>");
                    }
                    menus.append("</ul>");
                }else {
                    // 无子菜单
                    menus.append("<a href="+ menu.getUrl() +" class=\"menu_link nav-link\">" +
                            "     <i class=\"nav-icon fas "+ menu.getIcon() +"\"></i>" +
                            "     <p>"+ menu.getName() +"</p>" +
                            " </a>");
                }
                menus.append("</li>");
            }
            getSession().setAttribute("menus", menus);
            getSession().setAttribute("currentUser", currentUser());
        }
        return "/index";
    }


}
