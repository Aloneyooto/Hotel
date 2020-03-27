package com.alone.hotel.enums;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-24 08:51
 * @Description:
 */
public enum CustomerRelationStateEnum {
    SUCCESS(1, "操作成功"),
    INNER_ERROR(-201, "内部错误"),
    EMPTY(-202, "关联信息为空"),
    ;

    private int state;
    private String stateInfo;

    private CustomerRelationStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static CustomerRelationStateEnum stateOf(int state){
        for (CustomerRelationStateEnum stateEnum : values()){
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
