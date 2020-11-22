package com.tansene.service;

import com.tansene.entity.Order;
import com.baomidou.mybatisplus.service.IService;
import com.tansene.enums.OrderAction;
import com.tansene.enums.OrderType;
import com.tansene.model.OrderModel;

/**
 * <p>
 * 订单主表，当前表只保存流转中的订单信息 服务类
 * </p>
 *
 * @author tansene
 * @since 2018-10-17
 */
public interface IOrderService extends IService<Order> {

    Order handleOrder(OrderAction action, OrderType orderType, OrderModel orderDef) throws Exception;

}
