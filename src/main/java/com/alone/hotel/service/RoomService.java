package com.alone.hotel.service;

import com.alone.hotel.dto.ImageExecution;
import com.alone.hotel.dto.RoomExecution;
import com.alone.hotel.entity.Room;
import com.alone.hotel.entity.RoomImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-09 19:30
 * @Description:
 */
public interface RoomService {
    /**
     * 添加房间信息
     * @param room
     * @param files
     * @return
     */
    RoomExecution addRoom(Room room, MultipartFile[] files);

    /**
     * 根据房间号获取房间信息
     * @param roomId
     * @return
     */
    Room getRoomById(int roomId);
}
