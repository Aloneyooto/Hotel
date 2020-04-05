package com.alone.hotel.dao;

import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.RoomOrder;
import com.alone.hotel.entity.RoomOrderRelation;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-30 15:39
 * @Description:
 */
public interface RoomOrderRelationDao {
    /**
     * 添加订单与房间的关联信息
     * @param roomOrderRelation
     * @return
     */
    int addRoomOrderRelation(RoomOrderRelation roomOrderRelation);

    /**
     * 批量添加关联信息
     * @param roomOrderRelationList
     * @return
     */
    int batchAddRoomOrderRelation(List<RoomOrderRelation> roomOrderRelationList);

    /**
     * 根据订单号查询房间的信息
     * @param orderId
     * @return
     */
    RoomOrder queryRoomByOrderId(String orderId);

    /**
     * 删除订单与入住人的关联信息
     * @param orderId
     * @return
     */
    int deleteRoomOrderRelation(String orderId);
}
