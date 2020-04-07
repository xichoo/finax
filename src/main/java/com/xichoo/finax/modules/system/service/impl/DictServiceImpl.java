package com.xichoo.finax.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xichoo.finax.modules.system.entity.Dict;
import com.xichoo.finax.modules.system.mapper.DictMapper;
import com.xichoo.finax.modules.system.service.DictService;
import org.springframework.stereotype.Service;

/**
 * @author xichoo@live.cn
 */
@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService{


}
