package com.xichoo.finax.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xichoo.finax.modules.system.entity.Menu;

import java.util.List;

/**
 * @author xichoo@live.cn
 */
public interface MenuService extends IService<Menu> {


    List<Menu> getListByUserid(Long id, Integer menuType);
}
