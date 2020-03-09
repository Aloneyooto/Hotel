package com.alone.hotel.enums;

import lombok.Data;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-09 16:44
 * @Description:
 */
public enum RoomStateEnum {
    SUCCESS(1, "操作成功"),
    EMPTY(-1001, "房间属性为空"),
    ;

    private int state;
    private String stateInfo;

    private RoomStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static RoomStateEnum stateOf(int state){
        for (RoomStateEnum stateEnum : values()){
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
    }}
