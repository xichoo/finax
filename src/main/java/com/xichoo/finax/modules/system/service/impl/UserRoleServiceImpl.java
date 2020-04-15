package com.xichoo.finax.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xichoo.finax.modules.system.entity.UserRole;
import com.xichoo.finax.modules.system.mapper.UserRoleMapper;
import com.xichoo.finax.modules.system.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author xichoo@live.cn
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {


}
