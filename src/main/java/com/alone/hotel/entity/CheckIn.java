package com.alone.hotel.entity;

import lombok.Data;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-09 08:46
 * @Description: 入住信息表
 */
@Data
public class CheckIn {
    private Integer roomId;
    private String customerId;
}
