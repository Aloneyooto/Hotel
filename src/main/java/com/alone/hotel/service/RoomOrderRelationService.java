package com.alone.hotel.service;

import com.alone.hotel.entity.RoomOrder;
import com.alone.hotel.entity.RoomOrderRelation;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-30 16:18
 * @Description:
 */
public interface RoomOrderRelationService {
    /**
     * 添加订单与入住人的关联信息
     * @param roomOrderRelation
     * @return
     */
    Boolean addRoomOrderRelation(RoomOrderRelation roomOrderRelation);

    /**
     * 根据订单号查询入住人的信息
     * @param orderId
     * @return
     */
    RoomOrder queryCustomerByOrderId(String orderId);

    /**
     * 删除订单与入住人的关联信息
     * @param roomOrderId
     * @return
     */
    Boolean deleteRoomOrderRelation(String roomOrderId);
}
