package com.xichoo.finax.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xichoo.finax.modules.system.entity.Role;
import com.xichoo.finax.modules.system.mapper.RoleMapper;
import com.xichoo.finax.modules.system.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author xichoo@live.cn
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


}
