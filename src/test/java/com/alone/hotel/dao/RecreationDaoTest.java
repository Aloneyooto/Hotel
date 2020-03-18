package com.alone.hotel.dao;

import com.alone.hotel.entity.Recreation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-17 20:10
 * @Description:
 */
@SpringBootTest
public class RecreationDaoTest {
    @Autowired
    private RecreationDao recreationDao;

    @Test
    public void testAddRecreation(){
        Recreation recreation = new Recreation();
        recreation.setRecreationId(1);
        recreation.setRecreationName("健身房");
        recreation.setRecreationPrice(50d);
        int effectedNum = recreationDao.addRecreation(recreation);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryRecreationById(){
        Recreation recreation = recreationDao.queryRecreationById(1);
        assertEquals("健身房", recreation.getRecreationName());
    }

    @Test
    public void testQueryRecreationList(){
        Recreation recreation = new Recreation();
        recreation.setRecreationName("房");
        List<Recreation> recreationList = recreationDao.queryRecreationList(recreation, 0, 2);
        assertEquals(2, recreationList.size());
        int count = recreationDao.queryRecreationCount(recreation);
        assertEquals(3, count);
    }

    @Test
    public void testUpdateRecreation(){
        Recreation recreation = new Recreation();
        recreation.setRecreationId(1);
        recreation.setRecreationPrice(60d);
        int effectedNum = recreationDao.updateRecreation(recreation);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testDeleteRecreation(){
        int effectNum = recreationDao.deleteRecreation(3);
        assertEquals(1, effectNum);
    }
}