package com.alone.hotel.service;

import com.alone.hotel.dto.RecreationExecution;
import com.alone.hotel.entity.Recreation;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-17 20:24
 * @Description:
 */
public interface RecreationService {
    /**
     * 增加职位
     * @param recreation
     * @return
     */
    RecreationExecution insertRecreation(Recreation recreation);

    /**
     * 根据Id查询职位
     * @param recreationId
     * @return
     */
    Recreation queryRecreationById(int recreationId);

    /**
     * 根据查询条件模糊查询职位(name, note)
     * @param recreationCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    RecreationExecution queryRecreationList(Recreation recreationCondition, int pageIndex, int pageSize);

    /**
     * 修改职位
     * @param recreation
     * @return
     */
    RecreationExecution modifyRecreation(Recreation recreation);

    /**
     * 删除职位
     * @param recreationId
     * @return
     */
    RecreationExecution deleteRecreation(int recreationId);
}
