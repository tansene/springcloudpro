package com.tansene.controller;

import com.tansene.config.ResponseHelper;
import com.tansene.config.ResponseModel;
import com.tansene.entity.Order;
import com.tansene.enums.OrderAction;
import com.tansene.enums.OrderType;
import com.tansene.model.OrderModel;
import com.tansene.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tansene
 * @since 2020/11/22
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 测试工作流
     * 订单发货(动作),待发货-->已发货(状态)
     * @param orderModel
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/deliver")
    public ResponseModel<Order> updateDeliver(@RequestBody OrderModel orderModel)//@PathVariable String orderType,
            throws Exception {
        Order orderDef = orderService.handleOrder(OrderAction.deliver, OrderType.getInstance(orderModel.getOrderType()), orderModel);
        return ResponseHelper.buildResponseModel(orderDef);
    }


}
