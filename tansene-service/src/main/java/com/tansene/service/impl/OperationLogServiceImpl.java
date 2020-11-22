package com.tansene.service.impl;

import com.tansene.entity.OperationLog;
import com.tansene.mapper.OperationLogMapper;
import com.tansene.service.IOperationLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author tansene
 * @since 2020/11/22
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}
