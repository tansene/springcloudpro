package com.tansene.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tansene.service.IUserToRoleService;
import com.tansene.entity.UserToRole;
import com.tansene.mapper.UserToRoleMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tansene.util.ComUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tansene
 * @since 2020/11/22
 */
@Service
public class UserToRoleServiceImpl extends ServiceImpl<UserToRoleMapper, UserToRole> implements IUserToRoleService {

    @Override
//    @Cacheable(value = "UserToRole",keyGenerator="wiselyKeyGenerator")
    public UserToRole selectByUserNo(String userNo) {
        EntityWrapper<UserToRole> ew = new EntityWrapper<>();
        ew.where("user_no={0}", userNo);
        List<UserToRole> userToRoleList = this.selectList(ew);
        return ComUtil.isEmpty(userToRoleList)? null: userToRoleList.get(0);
    }
}
