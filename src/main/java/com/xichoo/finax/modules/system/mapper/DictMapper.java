package com.xichoo.finax.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xichoo.finax.modules.system.entity.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xichoo@live.cn
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict>{

    List<Dict> getListByCode(String code);
}
