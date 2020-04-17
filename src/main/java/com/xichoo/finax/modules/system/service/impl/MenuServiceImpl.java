package com.xichoo.finax.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xichoo.finax.modules.system.entity.Menu;
import com.xichoo.finax.modules.system.mapper.MenuMapper;
import com.xichoo.finax.modules.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return menuMapper.getListByUserid(id, menuType);
    }
}
