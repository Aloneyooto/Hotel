package com.alone.hotel.enums;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-29 14:39
 * @Description:
 */
public enum OrderStateEnum {
    SUCCESS(1, "操作成功"),
    ROOM_ORDER_INSERT_ERROR(-201, "房间订单插入错误"),
    ROOM_ORDER_UPDATE_ERROR(-202, "房间订单更新错误"),
    ROOM_ORDER_DELETE_ERROR(-203, "房间订单删除错误"),
    ROOM_ORDER_EMPTY(-204, "房间订单为空"),
    ROOM_ORDER_ID_EMPTY(-205, "房间订单ID为空"),
    RECREATE_ORDER_INSERT_ERROR(-206, "娱乐订单插入错误"),
    RECREATE_ORDER_UPDATE_ERROR(-207, "娱乐订单更新错误"),
    RECREATE_ORDER_DELETE_ERROR(-208, "娱乐订单删除错误"),
    RECREATE_ORDER_EMPTY(-209, "娱乐订单为空"),
    RECREATE_ORDER_ID_EMPTY(-210, "娱乐订单ID为空"),
    RELATION_INSERT_ERROR(-211, "关联信息插入失败"),
    RELATION_DELETE_ERROR(-212, "关联信息删除失败"),
    ROOM_LACKING(-213, "房间数量不足"),
    ROOM_UPDATE_ERROR(-214, "房间状态更新失败"),
    CUSTOMER_EMPTY(-215, "入住人信息为空"),
    CHECK_IN_INSERT_ERROR(-216, "房间关联信息插入失败"),
    INNER_ERROR(-217, "内部错误"),
    ACCOUNT_EMPTY(-218, "账号信息为空"),
    PAGE_ERROR(-219,"页码错误"),
    ;

    private int state;
    private String stateInfo;

    private OrderStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static OrderStateEnum stateOf(int state){
        for (OrderStateEnum stateEnum : values()){
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
