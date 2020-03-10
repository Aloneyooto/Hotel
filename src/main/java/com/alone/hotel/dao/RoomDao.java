package com.alone.hotel.dao;

import com.alone.hotel.entity.Room;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-09 15:21
 * @Description:
 */
public interface RoomDao {
    /**
     * 插入房间
     * @param room
     * @return
     */
    int insertRoom(Room room);

    /**
     * 通过房间号查询对应房间信息
     * @param roomId
     * @return
     */
    Room queryRoomById(int roomId);


}
