package com.alone.hotel.dao;

import com.alone.hotel.entity.RoomImg;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-09 10:20
 * @Description:
 */
public interface RoomImgDao {
    /**
     * 批量添加房间详情图片
     * @param roomImgList
     * @return
     */
    int batchInsertRoomImg(List<RoomImg> roomImgList);

    /**
     * 查询某个房间的所有详情图
     * @param roomId
     * @return
     */
    List<RoomImg> queryRoomImgList(int roomId);

    /**
     * 删除指定房间的所有详情图
     * @param roomId
     * @return
     */
    int deleteRoomImgByRoomId(int roomId);
}
