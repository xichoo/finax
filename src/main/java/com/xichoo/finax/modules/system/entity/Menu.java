package com.xichoo.finax.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

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

    /**
     * ztree是否选中
     */
    @TableField(exist = false)
    private boolean checked = false;

    /**
     * ztree数据格式处理
     */
    @Override
    public String toString() {
        String str = "";
        if(Strings.isNotBlank(permission)){
            str = " " + permission;
        }
        return "{id:" + id + ", pId:" + parentId + ", name:'" + name + str + "', checked:" + checked + "}_";
    }
}
