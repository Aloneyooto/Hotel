package com.alone.hotel.dto;

import com.alone.hotel.entity.RoomType;
import com.alone.hotel.enums.ResultEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-29 21:20
 * @Description:
 */
@Data
public class RoomTypeExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //房间数量
    private int count;
    //操作的房间
    private RoomType roomType;
    //操作的房间列表
    private List<RoomType> roomTypeList;

    public RoomTypeExecution() {
    }

    public RoomTypeExecution(ResultEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public RoomTypeExecution(ResultEnum stateEnum, RoomType roomType){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.roomType = roomType;
    }

    public RoomTypeExecution(ResultEnum stateEnum, List<RoomType> roomTypeList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.roomTypeList = roomTypeList;
    }
}
