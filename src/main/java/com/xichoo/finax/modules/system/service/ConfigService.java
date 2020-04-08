package com.xichoo.finax.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xichoo.finax.modules.system.entity.Config;

/**
 * @author xichoo@live.cn
 */
public interface ConfigService extends IService<Config> {

    String getListByCode(String key);
}
