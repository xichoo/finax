package com.xichoo.finax.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xichoo.finax.modules.system.entity.Menu;

import java.util.List;

/**
 * @author xichoo@live.cn
 */
public interface MenuService extends IService<Menu> {

    /**
     * 查询用户菜单
     * @param id 用户id
     * @param menuType 菜单类型
     * @return
     */
    List<Menu> getListByUserid(Long id, Integer menuType);

    /**
     * 查询用户菜单
     * @param id 用户id
     * @param pid 父节点
     * @param menuType 菜单类型
     * @return
     */
    List<Menu> getListByUseridAndPid(Long id, Long pid, Integer menuType);
}
