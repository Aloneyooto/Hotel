package com.alone.hotel.dao;

import com.alone.hotel.entity.CleanOrder;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-04-24 21:30
 * @Description:
 */
public interface CleanOrderDao {

    /**
     * 增加清洁订单
     * @param cleanOrder
     * @return
     */
    int addCleanOrder(CleanOrder cleanOrder);

    /**
     * 查询清洁订单
     * @return
     */
    List<CleanOrder> queryCleanOrder(String employeeId);

    /**
     * 查询当日已生成订单数
     * @param orderId
     * @return
     */
    int queryOrderCount(String orderId);

    /**
     * 修改清洁订单状态
     * @param cleanOrder
     * @return
     */
    int updateCleanOrderStatus(CleanOrder cleanOrder);

    /**
     * 删除清洁订单
     * @param cleanOrder
     * @return
     */
    int deleteCleanOrder(CleanOrder cleanOrder);
}
