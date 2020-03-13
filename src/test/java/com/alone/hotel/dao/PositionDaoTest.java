package com.alone.hotel.dao;

import com.alone.hotel.entity.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.Max;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-13 09:17
 * @Description:
 */
@SpringBootTest
public class PositionDaoTest {
    @Autowired
    private PositionDao positionDao;

    @Test
    public void testInsertPosition(){
        Position position = new Position();
        position.setPositionId(2);
        position.setPositionName("经理");
        position.setPositionBasicSalary(5000d);
        position.setPositionNote("test");
        int effectNum = positionDao.insertPosition(position);
        assertEquals(1, effectNum);
    }

    @Test
    public void testQueryPositionById(){
        Position position = positionDao.queryPositionById(2);
        assertEquals("经理", position.getPositionName());
    }

    @Test
    public void testQueryPositionList(){
        Position positionCondition = new Position();
        positionCondition.setPositionNote("test");
        List<Position> positionList = positionDao.queryPositionList(positionCondition, 0, 1);
        assertEquals(1, positionList.size());
        int count = positionDao.queryPositionCount(positionCondition);
        assertEquals(2, count);
    }

    @Test
    public void testUpdatePosition(){
        Position position = new Position();
        position.setPositionId(1);
        position.setPositionBasicSalary(15000d);
        int effectNum = positionDao.updatePosition(position);
        assertEquals(1, effectNum);
    }

    @Test
    public void testDeletePosition(){
        int effectNum = positionDao.deletePosition(2);
        assertEquals(1, effectNum);
    }
}