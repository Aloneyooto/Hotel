package com.alone.hotel.service;

import com.alone.hotel.dto.OrderExecution;
import com.alone.hotel.entity.RoomOrder;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-29 14:34
 * @Description:
 */
public interface RoomOrderService {
    /**
     * 添加房间订单
     * @param roomOrder
     * @return
     */
    OrderExecution addRoomOrder(RoomOrder roomOrder);

    /**
     * 根据账号查询订单
     * @param accountName
     * @return
     */
    List<RoomOrder> queryRoomOrderByAccountName(String accountName);

    /**
     * 查询已生成的订单数
     * @param handInTime
     * @return
     */
    int queryOrderCount(Date handInTime);

    /**
     * 修改房间订单
     * @param roomOrder
     * @return
     */
    OrderExecution updateRoomOrder(RoomOrder roomOrder);

    /**
     * 删除房间订单
     * @param orderId
     * @return
     */
    OrderExecution deleteRoomOrder(String orderId);
}
