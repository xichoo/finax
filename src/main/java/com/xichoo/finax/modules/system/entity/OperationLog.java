package com.xichoo.finax.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author xichoo@live.cn
 */
@Data
@TableName("sys_operation_log")
public class OperationLog {

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
     * 执行动作
     */
    private String action;
    /**
     * 包路径
     */
    private String method;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求ip
     */
    private String ip;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 返回结果
     */
    private String result;

    private Date createDate;

}
