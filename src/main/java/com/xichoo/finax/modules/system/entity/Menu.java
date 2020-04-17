package com.xichoo.finax.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 菜单
 * @author xichoo@live.cn
 */
@Data
@TableName("sys_menu")
public class Menu {

    @TableId
    private Long id;

    private Long parentId;

    private String name;

    private String url;

    private String icon;

    private Integer orderby;

    private Date createDate;

    /**
     * Constant.MenuType
     */
    private Integer menuType;

    /**
     * 权限标识
     */
    private String permission;

}
