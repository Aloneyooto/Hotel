package com.alone.hotel.service;

import com.alone.hotel.dto.RoomTypeExecution;
import com.alone.hotel.entity.RoomType;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-29 21:20
 * @Description:
 */
public interface RoomTypeService {
    /**
     * 增加房间类型
     * @param roomType
     * @return
     */
    RoomTypeExecution addRoomType(RoomType roomType);

    /**
     * 查询房间类型
     * @param typeId
     * @return
     */
    RoomType queryRoomTypeById(Integer typeId);

    /**
     * 查询房间类型
     * @return
     */
    List<RoomType> queryRoomType();

    /**
     * 更新房间类型
     * @param roomType
     * @return
     */
    RoomTypeExecution updateRoomType(RoomType roomType);

    /**
     * 删除房间类型
     * @param roomTypeId
     * @return
     */
    RoomTypeExecution deleteRoomType(Integer roomTypeId);
}
