package com.xichoo.finax.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xichoo.finax.modules.system.entity.Config;
import com.xichoo.finax.modules.system.mapper.ConfigMapper;
import com.xichoo.finax.modules.system.service.ConfigService;
import org.springframework.stereotype.Service;

/**
 * @author xichoo@live.cn
 */
@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService{


}
