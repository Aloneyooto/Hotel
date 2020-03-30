package com.alone.hotel.enums;

import com.alone.hotel.entity.RoomType;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-30 07:23
 * @Description:
 */
public enum RoomTypeStateEnum {
    SUCCESS(1, "操作成功"),
    EMPTY(-301, "房间类别为空"),
    INNER_ERROR(-302, "内部错误"),
    ;
    private int state;
    private String stateInfo;

    private RoomTypeStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static RoomTypeStateEnum stateOf(int state){
        for (RoomTypeStateEnum stateEnum : values()){
            if(stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
