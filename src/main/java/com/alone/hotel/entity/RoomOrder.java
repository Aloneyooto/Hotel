package com.alone.hotel.entity;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-09 09:15
 * @Description:
 */
@Data
public class RoomOrder {
    private String orderId;
    private String accountId;
    private Integer roomId;
    private Double orderPrice;
    private Date startTime;
    private Date endTime;
    private Integer orderStatus;
    private Date handInTime;
}
