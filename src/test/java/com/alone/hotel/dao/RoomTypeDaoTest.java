package com.alone.hotel.dao;

import com.alone.hotel.entity.Room;
import com.alone.hotel.entity.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-29 16:43
 * @Description:
 */
@SpringBootTest
public class RoomTypeDaoTest {
    @Autowired
    private RoomTypeDao roomTypeDao;

    @Test
    public void testAddRoomType(){
        RoomType roomType = new RoomType();
        roomType.setTypeName("大床房");
        roomType.setTypePrice(120d);
        roomType.setMaxAmount(2);
        int effectNum = roomTypeDao.addRoomType(roomType);
        assertEquals(1, effectNum);
    }

    @Test
    public void testQueryRoomType(){
        List<RoomType> roomTypeList = roomTypeDao.queryRoomType();
        assertEquals(2, roomTypeList.size());
    }

    @Test
    public void testUpdateRoomType(){
        RoomType roomType = new RoomType();
        roomType.setTypeId(2);
        roomType.setTypePrice(150d);
        int effectNum = roomTypeDao.updateRoomType(roomType);
        assertEquals(1, effectNum);
    }

    @Test
    public void testDeleteRoomType(){
        int effectNum = roomTypeDao.deleteRoomType(2);
        assertEquals(1, effectNum);
    }
}