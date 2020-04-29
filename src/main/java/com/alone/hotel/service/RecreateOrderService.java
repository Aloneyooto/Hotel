package com.alone.hotel.service;

import com.alone.hotel.dto.OrderExecution;
import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.entity.RecreateOrder;
import com.mysql.cj.x.protobuf.MysqlxCrud;

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
     * 根据顾客检索订单
     * @param recreationId
     * @param orderStatus
     * @param customerCardNumber
     * @return
     */
    Customer queryRecreateOrderByCustomer(Integer recreationId,
                                          Integer orderStatus,
                                          String customerCardNumber);

    /**
     * 查询账户内所有人的娱乐订单
     * @param accountName
     * @return
     */
    CustomerAccount queryRecreationListByAccount(String accountName);

    /**
     * 根据检索条件检索订单
     * @param orderCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    OrderExecution queryRecreateOrderList(RecreateOrder orderCondition, int pageIndex, int pageSize);

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
