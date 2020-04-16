package com.xichoo.finax.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xichoo.finax.modules.system.entity.User;

/**
 * @author xichoo@live.cn
 */
public interface UserService extends IService<User> {


    void saveUser(User user, String[] role);
}
