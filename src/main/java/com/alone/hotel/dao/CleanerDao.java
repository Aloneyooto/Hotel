package com.alone.hotel.dao;

import com.alone.hotel.entity.Cleaner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-16 19:32
 * @Description:
 */
public interface CleanerDao {
    /**
     * 插入清洁员信息
     * @param cleaner
     * @return
     */
    int insertCleaner(Cleaner cleaner);

    /**
     * 根据id查询employee
     * @param employeeId
     * @return
     */
    Cleaner queryCleanerById(String employeeId);

    /**
     * 查询清洁员信息
     * 楼层信息为模糊查询
     * @param roomFloor
     * @return
     */
    List<Cleaner> queryCleanerList(@Param("roomFloor") String roomFloor, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);

    /**
     * 条件查询清洁员总数
     * @param roomFloor
     * @return
     */
    int queryCleanerCount(String roomFloor);

    /**
     * 修改清洁员信息
     * @param cleaner
     * @return
     */
    int updateCleaner(Cleaner cleaner);

    /**
     * 删除清洁员信息
     * @param cleanerId
     * @return
     */
    int deleteCleaner(String cleanerId);
}
