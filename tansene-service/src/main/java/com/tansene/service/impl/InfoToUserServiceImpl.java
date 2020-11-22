package com.tansene.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tansene.entity.InfoToUser;
import com.tansene.mapper.InfoToUserMapper;
import com.tansene.service.IInfoToUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户电话关系表 服务实现类
 * </p>
 *
 * @author tansene
 * @since 2018-10-29
 */
@Service
public class InfoToUserServiceImpl extends ServiceImpl<InfoToUserMapper, InfoToUser> implements IInfoToUserService {

}
