package com.tansene.mapper;

import com.tansene.entity.Order;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 订单主表，当前表只保存流转中的订单信息 Mapper 接口
 * </p>
 *
 * @author tansene
 * @since 2020/11/22
 */
public interface OrderMapper extends BaseMapper<Order> {

}
