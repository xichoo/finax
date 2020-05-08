package com.xichoo.finax.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author xichoo@live.cn
 */
@Data
@TableName("sys_login_log")
public class LoginLog {

    @TableId
    private Long id;
    /**
     * 操作人id
     */
    private Long userId;
    /**
     * 操作人
     */
    private String username;
    /**
     * 登陆ip
     */
    private String ip;
    /**
     * 浏览器
     */
    private String broswer;
    /**
     * 操作系统
     */
    private String os;

    private Date createDate;

}
