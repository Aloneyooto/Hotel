package com.alone.hotel.dao;

import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.RecreateOrder;
import org.apache.ibatis.annotations.Param;

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
    Customer queryRecreateOrderByCustomer(RecreateOrder recreateOrder);

    /**
     * 查询某天已生成的订单数
     * @param handInTime
     * @return
     */
    int queryOrderCount(Date handInTime);

    /**
     * 根据检索条件检索订单
     * @param orderCondition
     * @return
     */
    List<RecreateOrder> queryRecreateOrderList(@Param("orderCondition")RecreateOrder orderCondition,
                                               @Param("rowIndex")int rowIndex,
                                               @Param("pageSize")int pageSize);

    /**
     * 根据检索条件检索出的订单记录数
     * @param orderCondition
     * @return
     */
    int queryRecreateOrderCount(@Param("orderCondition")RecreateOrder orderCondition);

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
