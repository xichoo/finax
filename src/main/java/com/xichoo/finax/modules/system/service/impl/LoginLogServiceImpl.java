package com.xichoo.finax.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xichoo.finax.common.util.XxUtil;
import com.xichoo.finax.modules.system.entity.LoginLog;
import com.xichoo.finax.modules.system.entity.User;
import com.xichoo.finax.modules.system.mapper.LoginLogMapper;
import com.xichoo.finax.modules.system.service.LoginLogService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author xichoo@live.cn
 */
@Service("loginLogService")
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {


    @Override
    public void save(User user, HttpServletRequest request) {
        LoginLog entity = new LoginLog();
        try{
            entity.setUserId(user.getId());
            entity.setUsername(user.getUsername());
            entity.setIp(XxUtil.getIpAddr(request));
            entity.setCreateDate(new Date());
            entity.setBroswer(XxUtil.getBroswerInfo(request));
            entity.setOs(XxUtil.getOSInfo(request));
            this.save(entity);
        }catch (Exception e){

        }
    }
}
