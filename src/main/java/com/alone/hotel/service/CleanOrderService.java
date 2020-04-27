package com.alone.hotel.service;

import com.alone.hotel.dto.CleanOrderExecution;
import com.alone.hotel.entity.CleanOrder;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-04-25 09:44
 * @Description:
 */
public interface CleanOrderService {
    /**
     * 添加清洁订单
     * @param cleanOrder
     * @return
     */
    CleanOrderExecution addCleanOrder(CleanOrder cleanOrder);

    /**
     * 查询清洁订单
     * @return
     */
    List<CleanOrder> queryCleanOrder(String employeeId);

    /**
     * 修改清洁订单状态
     * @param cleanOrder
     * @return
     */
    CleanOrderExecution updateCleanOrderStatus(CleanOrder cleanOrder);

    /**
     * 删除清洁订单
     * @param cleanOrder
     * @return
     */
    CleanOrderExecution deleteCleanOrder(CleanOrder cleanOrder);
}
