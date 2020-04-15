package com.xichoo.finax.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户角色关联
 * @author xichoo@live.cn
 */
@Data
@TableName("sys_user_role")
public class UserRole {

    @TableId
    private Long id;

    private Long userId;

    private Long roleId;

}
