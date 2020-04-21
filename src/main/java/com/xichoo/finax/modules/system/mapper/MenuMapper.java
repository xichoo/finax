package com.xichoo.finax.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xichoo.finax.modules.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xichoo@live.cn
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu>{

    @Select("SELECT m.id,m.parent_id,m.name,m.url,m.icon,m.permission from sys_role_menu rm " +
            "INNER JOIN sys_role r ON rm.role_id = r.id " +
            "INNER JOIN sys_user_role ur ON rm.role_id = ur.role_id " +
            "INNER JOIN sys_user u ON u.id = ur.user_id " +
            "INNER JOIN sys_menu m ON rm.menu_id = m.id " +
            "and u.id = #{id} and m.menu_type = #{menuType} " +
            "group by m.id order by m.orderby asc")
    List<Menu> getListByUserid(@Param("id") Long id, @Param("menuType")Integer menuType);

    @Select("SELECT m.id,m.parent_id,m.name,m.url,m.icon,m.permission from sys_role_menu rm " +
            "INNER JOIN sys_role r ON rm.role_id = r.id " +
            "INNER JOIN sys_user_role ur ON rm.role_id = ur.role_id " +
            "INNER JOIN sys_user u ON u.id = ur.user_id " +
            "INNER JOIN sys_menu m ON rm.menu_id = m.id " +
            "and u.id = #{id} and m.menu_type = #{menuType} and m.parent_id = #{pid} " +
            "group by m.id order by m.orderby asc")
    List<Menu> getListByUseridAndPid(@Param("id") Long id, @Param("pid")Long pid, @Param("menuType")Integer menuType);

    @Select("select m.id,m.parent_id,m.name,m.permission,rm.role_id as checked " +
            "from sys_menu m left join sys_role_menu rm on m.id=rm.menu_id " +
            "and rm.role_id = #{roleId} order by m.orderby asc")
    List<Menu> getListByRoleid(@Param("roleId") Long roleId);
}
