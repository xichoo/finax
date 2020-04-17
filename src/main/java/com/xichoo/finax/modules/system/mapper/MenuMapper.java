package com.xichoo.finax.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xichoo.finax.modules.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xichoo@live.cn
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu>{

    @Select("SELECT m.id,m.name,m.permission from sys_role_menu rm" +
            "INNER JOIN sys_role r ON rm.role_id = r.id" +
            "INNER JOIN sys_user_role ur ON rm.role_id = ur.role_id" +
            "INNER JOIN sys_user u ON u.id = ur.user_id" +
            "INNER JOIN sys_menu m ON rm.menu_id = m.id" +
            "and u.id = #{id} and m.menu_type = #{menuType}")
    List<Menu> getListByUserid(Long id, Integer menuType);
}
