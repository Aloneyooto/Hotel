package com.alone.hotel.enums;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-17 20:25
 * @Description:
 */
public enum RecreationStateEnum {
    SUCCESS(1, "操作成功"),
    RECREATION_EMPTY(-4001, "消费项目信息为空"),
    INNER_ERROR(-4002, "内部错误"),
    RECREATION_ID_ERROR(-4003, "消费项目ID错误"),
    PAGE_ERROR(-4004, "页码错误"),
    ;

    private int state;
    private String stateInfo;

    private RecreationStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static RecreationStateEnum stateOf(int state){
        for (RecreationStateEnum stateEnum : values()){
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
