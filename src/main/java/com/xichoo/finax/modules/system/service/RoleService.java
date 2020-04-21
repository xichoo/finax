package com.xichoo.finax.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xichoo.finax.modules.system.entity.Role;

/**
 * @author xichoo@live.cn
 */
public interface RoleService extends IService<Role> {

    /**
     * 保存角色和菜单权限信息
     * @param role
     * @param menuIds
     */
    void saveRole(Role role, String menuIds);
}
