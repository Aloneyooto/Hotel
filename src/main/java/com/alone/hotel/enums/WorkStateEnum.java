package com.alone.hotel.enums;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-22 21:23
 * @Description:
 */
public enum WorkStateEnum {
    SUCCESS(1, "操作成功"),
    EMPTY(-6001, "排班信息为空"),
    INNER_ERROR(-6002, "内部错误"),
    ;

    private int state;
    private String stateInfo;

    private WorkStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static WorkStateEnum stateOf(int state){
        for (WorkStateEnum stateEnum : values()){
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
