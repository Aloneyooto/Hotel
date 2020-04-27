package com.alone.hotel.enums;

import lombok.Getter;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-04-25 10:25
 * @Description: 房间状态
 */
@Getter
public enum RoomStateEnum {
    EMPTY(0, "空房间"),
    BOOKING(1, "已预订"),
    CHECK_OUT(2, "已退房"),
    ;
    private Integer state;
    private String stateInfo;

    private RoomStateEnum(Integer state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }
}
