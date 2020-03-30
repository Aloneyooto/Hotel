package com.alone.hotel.dao;

import com.alone.hotel.entity.RoomOrder;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-28 15:05
 * @Description:
 */
public interface RoomOrderDao {
    /**
     * 添加房间订单
     * @param roomOrder
     * @return
     */
    int addRoomOrder(RoomOrder roomOrder);

    /**
     * 根据账号查询订单
     * @param accountName
     * @return
     */
    List<RoomOrder> queryRoomOrderByAccountName(String accountName);

    /**
     * 根据日期查询当天已生成了多少订单
     * @param handInTime
     * @return
     */
    int queryOrderCount(Date handInTime);

    /**
     * 修改房间订单
     * @param roomOrder
     * @return
     */
    int updateRoomOrder(RoomOrder roomOrder);

    /**
     * 删除房间订单
     * @param orderId
     * @return
     */
    int deleteRoomOrder(String orderId);
}
