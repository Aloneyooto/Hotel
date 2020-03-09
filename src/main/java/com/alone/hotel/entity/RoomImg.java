package com.alone.hotel.entity;

import lombok.Data;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-09 09:11
 * @Description:
 */
@Data
public class RoomImg {
    private Integer roomImgId;
    private String roomImgAddr;
    private Integer priority;
    private Integer roomId;
}
