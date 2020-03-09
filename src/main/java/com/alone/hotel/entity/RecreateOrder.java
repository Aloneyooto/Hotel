package com.alone.hotel.entity;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-09 09:08
 * @Description:
 */
@Data
public class RecreateOrder {
    private String orderId;
    private String customerId;
    private Integer recreationId;
    private Double orderPrice;
    private Date startTime;
    private Date endTime;
    private Integer orderStatus;
}
