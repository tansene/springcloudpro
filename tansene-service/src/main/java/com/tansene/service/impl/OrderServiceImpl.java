package com.tansene.service.impl;

import com.tansene.entity.Order;
import com.tansene.enums.OrderAction;
import com.tansene.enums.OrderType;
import com.tansene.mapper.OrderMapper;
import com.tansene.model.OrderModel;
import com.tansene.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tansene.service.handler.OrderHandler;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单主表，当前表只保存流转中的订单信息 服务实现类
 * </p>
 *
 * @author tansene
 * @since 2018-10-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {


    @Override
    public Order handleOrder(OrderAction action, OrderType orderType, OrderModel orderDef) throws Exception {
        Order order = OrderHandler.handle(action, orderType, orderDef);
        return order;
    }

}
