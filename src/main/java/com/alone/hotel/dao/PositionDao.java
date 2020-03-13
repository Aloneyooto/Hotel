package com.alone.hotel.dao;

import com.alone.hotel.entity.Position;
import com.alone.hotel.entity.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-11 20:27
 * @Description:
 */
public interface PositionDao {
    /**
     * 插入职位
     * @param position
     * @return
     */
    int insertPosition(Position position);

    /**
     * 根据职位号查询对应职位信息
     * @param positionId
     * @return
     */
    Position queryPositionById(int positionId);

    /**
     * 根据查询条件查询对应职位信息
     * @param positionCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Position> queryPositionList(@Param("positionCondition") Position positionCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 职位信息总数
     * @return
     */
    int queryPositionCount(@Param("positionCondition") Position positionCondition);

    /**
     * 修改职位信息
     * @param position
     * @return
     */
    int updatePosition(Position position);

    /**
     * 删除职位信息
     * @param positionId
     * @return
     */
    int deletePosition(int positionId);
}
