package com.xichoo.finax.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 角色菜单关联
 * @author xichoo@live.cn
 */
@Data
@TableName("sys_role_menu")
public class RoleMenu {

    @TableId
    private Long id;

    private Long roleId;

    private Long menuId;

}
