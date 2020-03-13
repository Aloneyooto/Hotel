package com.alone.hotel.service;

import com.alone.hotel.dto.PositionExecution;
import com.alone.hotel.entity.Position;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-13 14:33
 * @Description:
 */
public interface PositionService {
    /**
     * 增加职位
     * @param position
     * @return
     */
    PositionExecution insertPosition(Position position);

    /**
     * 根据Id查询职位
     * @param positionId
     * @return
     */
    PositionExecution queryPositionById(int positionId);

    /**
     * 根据查询条件模糊查询职位(name, note)
     * @param positionCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PositionExecution queryPositionList(Position positionCondition, int pageIndex, int pageSize);

    /**
     * 修改职位
     * @param position
     * @return
     */
    PositionExecution modifyPosition(Position position);

    /**
     * 删除职位
     * @param positionId
     * @return
     */
    PositionExecution deletePosition(int positionId);
}
