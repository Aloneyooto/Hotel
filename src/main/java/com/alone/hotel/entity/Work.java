package com.alone.hotel.entity;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-09 09:17
 * @Description:
 */
@Data
public class Work {
    private String employeeId;
    private Date workTime;
    private Date startTime;
    private Date endTime;
    private Integer status;
}
