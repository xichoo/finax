package com.xichoo.finax.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 系统参数配置
 * @author xichoo@live.cn
 */
@Data
@TableName("sys_config")
public class Config {
    @TableId
    private Integer id;

    private String paramKey;

    private String paramValue;

    private String remark;

}
