package com.xichoo.finax.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xichoo.finax.modules.system.entity.Dict;

import java.util.List;

/**
 * @author xichoo@live.cn
 */
public interface DictService extends IService<Dict> {

    List<Dict> getListByCode(String code);
}
