package com.alone.hotel.service;

import com.alone.hotel.dto.WorkExecution;
import com.alone.hotel.entity.Work;

import java.util.Date;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-22 20:45
 * @Description:
 */
public interface WorkService {
    /**
     * 插入排班信息
     * @param work
     * @return
     */
    WorkExecution addWorkMessage(Work work);

    /**
     * 查询某个人的排班信息
     * @param employeeId
     * @return
     */
    WorkExecution queryWorkTimeById(String employeeId);

    /**
     * 修改排班信息
     * @param work
     * @return
     */
    WorkExecution updateWorkTime(Work work);

    /**
     * 删除排班信息
     * @param employeeId
     * @param workTime
     * @return
     */
    WorkExecution deleteWorkTime(String employeeId, Date workTime);
}
