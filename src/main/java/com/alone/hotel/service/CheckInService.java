package com.alone.hotel.service;

import com.alone.hotel.entity.CheckIn;
import com.alone.hotel.entity.Room;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-28 14:58
 * @Description:
 */
public interface CheckInService {
    /**
     * 添加顾客入住信息
     * @param checkIn
     * @return 添加是否成功
     */
    boolean addCheckInMessage(CheckIn checkIn);

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
    boolean deleteCheckInMessage(CheckIn checkIn);
}
