package com.alone.hotel.dao;

import com.alone.hotel.entity.CheckIn;
import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.Room;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-27 21:43
 * @Description:
 */
public interface CheckInDao {
    /**
     * 添加入住关联信息
     * @param checkIn
     * @return
     */
    int addCheckInMessage(CheckIn checkIn);

    /**
     * 查询某一房间的入住人信息
     * @param roomId
     * @return
     */
    Room queryCheckInByRoom(Integer roomId);

    /**
     * 查询某一顾客的入住信息
     * @param customerCardNumber
     * @return
     */
    CheckIn queryCheckInByCustomer(String customerCardNumber);

    /**
     * 删除入住关联信息
     * @param checkIn
     * @return
     */
    int deleteCheckInMessage(CheckIn checkIn);

    /**
     * 删除某房间的入住关联信息
     * @param roomId
     * @return
     */
    int deleteCheckInMessageByRoom(Integer roomId);
}
