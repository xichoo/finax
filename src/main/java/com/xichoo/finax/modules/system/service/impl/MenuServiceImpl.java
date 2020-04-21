package com.xichoo.finax.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xichoo.finax.common.util.Constant;
import com.xichoo.finax.modules.system.entity.Menu;
import com.xichoo.finax.modules.system.mapper.MenuMapper;
import com.xichoo.finax.modules.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xichoo@live.cn
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService{
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getListByUserid(Long id, Integer menuType) {
        List<Menu> menuList = new ArrayList<>();
        // 超级管理员不受限
        if(id.equals(Constant.SUPER_ADMIN_ID)){
            menuList = this.list(new QueryWrapper<Menu>().
                    eq("menu_type", menuType).
                    orderByAsc("orderby"));
        }else{
            menuList = menuMapper.getListByUserid(id, menuType);
        }
        return menuList;
    }

    @Override
    public List<Menu> getListByUseridAndPid(Long id, Long pid, Integer menuType) {
        List<Menu> menuList = new ArrayList<>();
        // 超级管理员不受限
        if(id.equals(Constant.SUPER_ADMIN_ID)){
            menuList = this.list(new QueryWrapper<Menu>().
                    eq("menu_type", menuType).
                    eq("parent_id", pid).
                    orderByAsc("orderby"));
        }else{
            menuList = menuMapper.getListByUseridAndPid(id, pid, menuType);
        }
        return menuList;
    }

    @Override
    public List<Menu> getListByRoleid(Long roleId) {
        return menuMapper.getListByRoleid(roleId);
    }

}
