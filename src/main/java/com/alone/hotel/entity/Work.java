package com.alone.hotel.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Employee employee;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date workTime;
    private Date startTime;
    private Date endTime;
    private Integer status;
}
