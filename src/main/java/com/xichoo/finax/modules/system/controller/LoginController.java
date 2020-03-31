package com.xichoo.finax.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.common.util.Constant;
import com.xichoo.finax.common.util.Result;
import com.xichoo.finax.modules.system.entity.User;
import com.xichoo.finax.modules.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器
 * @author xichoo@live.cn
 */

@Controller
public class LoginController extends BaseController{
    @Autowired
    private UserService service;

    @GetMapping("/login")
    public String login(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            return "redirect:index";
        }
        return "/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(HttpServletRequest request, String username, String password){
        String msg = null;
        // 登陆错误次数
        int errorcount = 0;
        User user = service.getOne(new QueryWrapper<User>().eq("username", username));
        if(user != null && user.getErrorcount() != null) {
            errorcount = user.getErrorcount();
            request.getSession().setAttribute("loginUser", user);
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
    public void sliderCaptcha(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("loginUser");
        if(user != null){
            user.setErrorcount(user.getErrorcount() - 1);
            service.updateById(user);
        }
    }

    @GetMapping("/index")
    public String index(){
        return "/index";
    }


}
