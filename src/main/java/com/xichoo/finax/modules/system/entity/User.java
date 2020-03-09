package com.xichoo.finax.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 系统用户
 * @author xichoo@live.cn
 */
@Data
@TableName("sys_user")
public class User{

    @TableId
    private Long id;

    private String username;

    private String password;

    private String email;

    private String mobile;

    private Date createDate;

    /**
     * 登录错误次数
     */
    private Integer errorcount;

}
