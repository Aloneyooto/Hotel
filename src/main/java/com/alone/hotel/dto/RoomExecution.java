package com.alone.hotel.dto;

import com.alone.hotel.entity.Room;
import com.alone.hotel.enums.RoomStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-09 16:41
 * @Description: 房间信息传输类
 */
@Data
public class RoomExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //商品数量
    private int count;
    //操作的房间
    private Room room;
    //操作的房间列表
    private List<Room> roomList;

    public RoomExecution() {
    }

    public RoomExecution(RoomStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public RoomExecution(RoomStateEnum stateEnum, Room room){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.room = room;
    }

    public RoomExecution(RoomStateEnum stateEnum, List<Room> roomList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.roomList = roomList;
    }
}
