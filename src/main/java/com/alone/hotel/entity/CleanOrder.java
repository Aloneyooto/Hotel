package com.alone.hotel.entity;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-04-24 20:41
 * @Description: 清洁订单
 */
@Data
public class CleanOrder {

    private String orderId;

    private Room room;

    private Employee employee;

    private Integer status;

    private Date handInTime;
}
