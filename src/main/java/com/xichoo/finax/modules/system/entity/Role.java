package com.xichoo.finax.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 角色
 * @author xichoo@live.cn
 */
@Data
@TableName("sys_role")
public class Role {

    @TableId
    private Long id;

    private String role;

    private String description;

    private Date createDate;

}
