package com.alone.hotel.entity;

import lombok.Data;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-30 15:35
 * @Description:
 */
@Data
public class RoomOrderRelation {
    private RoomOrder roomOrder;
    private Room room;
}
