package com.tansene.service;

import com.tansene.entity.User;
import com.tansene.entity.UserThirdparty;
import com.baomidou.mybatisplus.service.IService;
import com.tansene.model.ThirdPartyUser;

/**
 * <p>
 * 第三方用户表 服务类
 * </p>
 *
 * @author tansene
 * @since 2018-07-27
 */
public interface IUserThirdpartyService extends IService<UserThirdparty> {

    User insertThirdPartyUser(ThirdPartyUser param, String password)throws Exception;

}
