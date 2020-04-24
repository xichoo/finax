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

    List<Menu> getListByUserid(@Param("id") Long id, @Param("menuType")Integer menuType);

    List<Menu> getListByUseridAndPid(@Param("id") Long id, @Param("pid")Long pid, @Param("menuType")Integer menuType);

    List<Menu> getListByRoleid(@Param("roleId") Long roleId);
}
