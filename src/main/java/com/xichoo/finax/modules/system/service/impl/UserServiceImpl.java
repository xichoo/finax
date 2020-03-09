package com.xichoo.finax.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xichoo.finax.modules.system.entity.User;
import com.xichoo.finax.modules.system.mapper.UserMapper;
import com.xichoo.finax.modules.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xichoo@live.cn
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
