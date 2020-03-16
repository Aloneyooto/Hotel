package com.alone.hotel.entity;

import lombok.Data;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-09 08:47
 * @Description: 清洁员楼层安排
 */
@Data
public class Cleaner {
    private Employee employee;
    private String roomFloor;
}
