package com.xichoo.finax.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xichoo.finax.modules.system.entity.OperationLog;
import com.xichoo.finax.modules.system.mapper.OperationLogMapper;
import com.xichoo.finax.modules.system.service.OperationLogService;
import org.springframework.stereotype.Service;

/**
 * @author xichoo@live.cn
 */
@Service("operationLogService")
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {


}
