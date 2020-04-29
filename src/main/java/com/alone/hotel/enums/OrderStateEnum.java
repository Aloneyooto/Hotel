package com.alone.hotel.enums;

import lombok.Getter;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-04-25 10:12
 * @Description: 订单状态
 */
@Getter
public enum OrderStateEnum {
    UNFINISHED(0, "未完成"),
    UNPAID(1, "未支付"),
    PAID(2, "已支付"),
    FINISHED(3, "已完成"),
    ;
    private Integer state;
    private String stateInfo;

    private OrderStateEnum(Integer state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }
}
