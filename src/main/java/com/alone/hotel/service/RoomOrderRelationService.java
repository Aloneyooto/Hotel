package com.alone.hotel.service;

import com.alone.hotel.entity.RoomOrder;
import com.alone.hotel.entity.RoomOrderRelation;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-30 16:18
 * @Description:
 */
public interface RoomOrderRelationService {
    /**
     * 添加订单与房间的关联信息
     * @param roomOrderRelation
     * @return
     */
    Boolean addRoomOrderRelation(RoomOrderRelation roomOrderRelation);

    /**
     * 批量添加关联信息
     * @param roomOrderRelationList
     * @return
     */
    Boolean batchAddRoomOrderRelation(List<RoomOrderRelation> roomOrderRelationList);

    /**
     * 根据订单号查询房间的信息
     * @param orderId
     * @return
     */
    RoomOrder queryRoomByOrderId(String orderId);

    /**
     * 删除订单与房间的关联信息
     * @param roomOrderId
     * @return
     */
    Boolean deleteRoomOrderRelation(String roomOrderId);
}
