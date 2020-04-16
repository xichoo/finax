package com.xichoo.finax.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xichoo.finax.modules.system.entity.User;
import com.xichoo.finax.modules.system.entity.UserRole;
import com.xichoo.finax.modules.system.mapper.UserMapper;
import com.xichoo.finax.modules.system.mapper.UserRoleMapper;
import com.xichoo.finax.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xichoo@live.cn
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 保存用户和角色
     * @param user
     * @param role
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(User user, String[] role) {
        this.saveOrUpdate(user);
        // 设置角色
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
        if(role == null){
            return;
        }
        for(String str : role){
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(Long.valueOf(str));
            userRoleMapper.insert(userRole);
        }
    }
}
