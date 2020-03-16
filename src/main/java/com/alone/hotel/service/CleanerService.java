package com.alone.hotel.service;

import com.alone.hotel.dto.CleanerExecution;
import com.alone.hotel.entity.Cleaner;


/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-16 20:17
 * @Description:
 */
public interface CleanerService {
    /**
     * 添加清洁员楼层信息
     * @param cleaner
     * @return
     */
    CleanerExecution addCleaner(Cleaner cleaner);

    /**
     * 根据id查询employee
     * @param employeeId
     * @return
     */
    Cleaner queryCleanerById(String employeeId);

    /**
     * 查询楼层对应清洁员列表
     * @param roomFloor
     * @return
     */
    CleanerExecution queryCleanerList(String roomFloor, int pageIndex, int pageSize);

    /**
     * 修改清洁员信息
     * @param cleaner
     * @return
     */
    CleanerExecution updateCleaner(Cleaner cleaner);

    /**
     * 删除清洁员信息
     * @param employeeId
     * @return
     */
    CleanerExecution deleteCleaner(String employeeId);
}
