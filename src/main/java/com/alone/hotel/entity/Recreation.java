package com.alone.hotel.entity;

import lombok.Data;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-09 09:10
 * @Description:
 */
@Data
public class Recreation {
    private Integer recreationId;
    private String recreationName;
    private Double recreationPrice;
}
