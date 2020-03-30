package com.alone.hotel.dao;

import com.alone.hotel.entity.RoomType;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-29 16:31
 * @Description:
 */
public interface RoomTypeDao {
    /**
     * 增加房间类型
     * @param roomType
     * @return
     */
    int addRoomType(RoomType roomType);

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
    int updateRoomType(RoomType roomType);

    /**
     * 删除房间类型
     * @param roomTypeId
     * @return
     */
    int deleteRoomType(Integer roomTypeId);
}
