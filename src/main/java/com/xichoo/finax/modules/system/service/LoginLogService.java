package com.xichoo.finax.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xichoo.finax.modules.system.entity.LoginLog;
import com.xichoo.finax.modules.system.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xichoo@live.cn
 */
public interface LoginLogService extends IService<LoginLog> {


    void save(User user, HttpServletRequest request);
}
