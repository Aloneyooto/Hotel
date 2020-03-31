package com.alone.hotel.service;

import com.alone.hotel.dto.OrderExecution;
import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.RecreateOrder;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-29 15:16
 * @Description:
 */
public interface RecreateOrderService {
    /**
     * 插入其他消费记录
     * @param recreateOrder
     * @return
     */
    OrderExecution addRecreateOrder(RecreateOrder recreateOrder);

    /**
     * 根据检索条件检索订单
     * @param recreateOrder
     * @return
     */
    Customer queryRecreateOrderByCustomer(RecreateOrder recreateOrder);

    /**
     * 更新其他消费记录
     * @param recreateOrder
     * @return
     */
    OrderExecution updateRecreateOrder(RecreateOrder recreateOrder);

    /**
     * 删除其他消费记录
     * @param recreateOrderId
     * @return
     */
    OrderExecution deleteRecreateOrder(String recreateOrderId);
}
