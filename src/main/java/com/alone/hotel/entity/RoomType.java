package com.alone.hotel.entity;

import lombok.Data;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-29 16:30
 * @Description: 房间类型
 */
@Data
public class RoomType {
    private Integer typeId;
    private String typeName;
    private Double typePrice;
    private Integer maxAmount;
}
