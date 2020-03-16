package com.alone.hotel.enums;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-16 21:26
 * @Description:
 */
public enum CleanerStateEnum {
    SUCCESS(1, "操作成功"),
    INSERT_INNER_ERROR(-3001, "插入时内部错误"),
    POSITION_TYPE_ERROR(-3002, "职位类型错误"),
    EMPTY(-3003, "清洁员信息为空"),
    UPDATE_INNER_ERROR(-3004, "修改时内部错误"),
    DELETE_INNER_ERROR(-3005, "删除时内部错误"),
    PAGE_ERROR(-3006, "页码错误"),
    INNER_ERROR(-3007, "内部错误"),
    ;
    private int state;
    private String stateInfo;

    private CleanerStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static CleanerStateEnum stateOf(int state){
        for (CleanerStateEnum stateEnum : values()){
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
