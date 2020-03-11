package com.alone.hotel.dao;

import com.alone.hotel.entity.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据特定条件查询房间列表
     * @param roomCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Room> queryRoomList(@Param("roomCondition") Room roomCondition, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);

    /**
     * 查询对应的房间数
     * @param roomCondition
     * @return
     */
    int queryRoomCount(@Param("roomCondition")Room roomCondition);

    /**
     * 更新房间信息
     * @param room
     * @return
     */
    int updateRoom(Room room);
}
