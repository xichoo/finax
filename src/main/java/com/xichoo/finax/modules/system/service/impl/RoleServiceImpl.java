package com.xichoo.finax.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xichoo.finax.modules.system.entity.Role;
import com.xichoo.finax.modules.system.entity.RoleMenu;
import com.xichoo.finax.modules.system.mapper.RoleMapper;
import com.xichoo.finax.modules.system.mapper.RoleMenuMapper;
import com.xichoo.finax.modules.system.service.RoleService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xichoo@live.cn
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(Role role, String menuIds) {
        //保存角色信息
        this.saveOrUpdate(role);
        //更新菜单权限
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("role_id", role.getId()));
        if(Strings.isNotBlank(menuIds)){
            String [] ids = menuIds.split(",");
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(role.getId());
            for(String menuId : ids){
                roleMenu.setMenuId(Long.parseLong(menuId));
                roleMenuMapper.insert(roleMenu);
            }
        }
    }
}
