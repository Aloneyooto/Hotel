package com.alone.hotel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date workTime;
    @JsonFormat(pattern = "HH:mm:ss", timezone="GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "HH:mm:ss", timezone="GMT+8")
    private Date endTime;
    private Integer status;
}
