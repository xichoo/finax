package com.xichoo.finax.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 数据字典
 * @author xichoo@live.cn
 */
@Data
@TableName("sys_dict")
public class Dict {

    @TableId
    private Long id;

    private Long parentId;

    private String code;

    private String name;

    private String value;

    private Integer isDefault;

    private String remark;

    private Integer orderby;

    private Date createDate;

}
