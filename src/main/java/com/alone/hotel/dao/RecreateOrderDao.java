package com.alone.hotel.dao;

import com.alone.hotel.entity.RecreateOrder;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-29 07:54
 * @Description:
 */
public interface RecreateOrderDao {
    /**
     * 插入其他消费记录
     * @param recreateOrder
     * @return
     */
    int addRecreateOrder(RecreateOrder recreateOrder);

    /**
     * 根据检索条件检索订单
     * @param recreateOrder
     * @return
     */
    List<RecreateOrder> queryRecreateOrderByCustomer(RecreateOrder recreateOrder);

    /**
     * 查询某天已生成的订单数
     * @param handInTime
     * @return
     */
    int queryOrderCount(Date handInTime);

    /**
     * 更新其他消费记录
     * @param recreateOrder
     * @return
     */
    int updateRecreateOrder(RecreateOrder recreateOrder);

    /**
     * 删除其他消费记录
     * @param recreateOrderId
     * @return
     */
    int deleteRecreateOrder(String recreateOrderId);
}
