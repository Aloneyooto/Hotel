package com.alone.hotel.dao;

import com.alone.hotel.entity.Work;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-19 19:31
 * @Description:
 */
public interface WorkDao {
    /**
     * 插入排班信息
     * @param work
     * @return
     */
    int addWorkMessage(Work work);

    /**
     * 查询某个人的排班时间
     * @param employeeId
     * @return
     */
    List<Work> queryWorkTimeById(String employeeId);

    /**
     * 修改排班信息
     * @param work
     * @return
     */
    int updateWorkTime(Work work);

    /**
     * 删除排班信息
     * @param employeeId
     * @param workTime
     * @return
     */
    int deleteWorkTime(String employeeId, Date workTime);
}
