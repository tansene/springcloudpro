package com.tansene.service.handler;

import com.tansene.entity.Order;
import com.tansene.enums.OrderAction;
import com.tansene.enums.OrderType;
import com.tansene.model.OrderModel;
import com.tansene.service.processor.ActionProcessor;
import org.springframework.stereotype.Component;

/**
 * @author tansene
 * @since 2020/11/22
 */
@Component("ProductOrderHandler")
public class ProductOrderHandler extends OrderHandler {

	@Override
	public Order handleInternal(OrderAction action, OrderType orderType, OrderModel orderDef, Order currentOrder) throws Exception {
		return  ActionProcessor.process(action,orderType,orderDef,currentOrder);
	}
}
