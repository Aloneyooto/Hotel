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
     * 添加订单与入住人的关联信息
     * @param roomOrderRelation
     * @return
     */
    int addRoomOrderRelation(RoomOrderRelation roomOrderRelation);

    /**
     * 根据订单号查询入住人的信息
     * @param orderId
     * @return
     */
    RoomOrder queryCustomerByOrderId(String orderId);

    /**
     * 删除订单与入住人的关联信息
     * @param orderId
     * @return
     */
    int deleteRoomOrderRelation(String orderId);
}
