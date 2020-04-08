package com.xichoo.finax.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xichoo.finax.modules.system.entity.Config;
import com.xichoo.finax.modules.system.mapper.ConfigMapper;
import com.xichoo.finax.modules.system.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xichoo@live.cn
 */
@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService{
    @Autowired
    private ConfigMapper configMapper;

    @Override
    public String getListByCode(String key) {
        List<Config> configList = configMapper.selectList(
                new QueryWrapper<Config>().eq("param_key", key));
        if(configList == null || configList.isEmpty()){
            return null;
        }
        return configList.get(0).getParamValue();
    }
}
