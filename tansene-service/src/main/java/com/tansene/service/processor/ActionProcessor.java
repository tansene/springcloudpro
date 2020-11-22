package com.tansene.service.processor;

import com.tansene.base.BusinessException;
import com.tansene.entity.Order;
import com.tansene.enums.OrderAction;
import com.tansene.enums.OrderType;
import com.tansene.model.OrderModel;
import com.tansene.service.SpringContextBeanService;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * @author tansene
 * @since 2020/11/22
 */
public abstract class ActionProcessor {

    private static final String BEAN_NAME_SUFIX = "Processor";

    private static ActionProcessor getProcessor(OrderAction action, OrderType orderType) throws Exception{
        String beanName = action.name() + orderType.name() + BEAN_NAME_SUFIX;
        ActionProcessor processor = null;
        try{
            processor = SpringContextBeanService.getBean(beanName);
        }catch (NoSuchBeanDefinitionException e){
            throw new BusinessException("未找到对应的流程处理器:" + beanName);
        }
        return processor;
    }

    public static Order process(OrderAction action, OrderType orderType, OrderModel orderDef, Order currentOrder) throws Exception{
        return getProcessor(action,orderType).process(orderDef,currentOrder);
    }

    /**
     * 处理订单,不同的ActionProcessor实现类实现此方法
     * @param orderDef
     * @throws Exception
     */
    public  abstract Order process(OrderModel orderDef,Order currentOrder) throws Exception;


}
