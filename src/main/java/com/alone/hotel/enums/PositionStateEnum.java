package com.alone.hotel.enums;


/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-11 20:35
 * @Description:
 */
public enum PositionStateEnum {
    SUCCESS(1, "操作成功"),
    POSITION_EMPTY(-101, "职位信息为空"),
    POSITION_ID_ERROR(-102, "职位号为空"),
    POSITION_INSERT_ERROR(-103, "职位插入错误"),
    POSITION_PAGE_ERROR(-104, "查询页码错误"),
    POSITION_INNER_ERROR(-105, "查询内部错误"),
    ;

    private int state;
    private String stateInfo;

    private PositionStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static PositionStateEnum stateOf(int state){
        for (PositionStateEnum stateEnum : values()){
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
