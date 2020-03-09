package com.alone.hotel.entity;

import lombok.Data;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-09 09:07
 * @Description:
 */
@Data
public class Position {
    private Integer positionId;
    private String positionName;
    private Double positionBasicSalary;
    private String positionNote;
}
