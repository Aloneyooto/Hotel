package com.alone.hotel.dao;

import com.alone.hotel.entity.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-09 15:28
 * @Description:
 */
@SpringBootTest
class RoomDaoTest {
    @Autowired
    private RoomDao roomDao;

    @Test
    public void testInsertRoom(){
        Room room = new Room();
        room.setRoomId(101);
        room.setRoomFloor("1层");
        room.setRoomDesc("大床 10-25m²");
        room.setRoomState(0);
        room.setRoomType(1);
        room.setRoomPrice(100.1);
        int effectNum = roomDao.insertRoom(room);
        assertEquals(1, effectNum);
    }

    @Test
    public void testQueryRoomById(){
        Room room = roomDao.queryRoomById(101);
        assertEquals(100.1, room.getRoomPrice());
    }
}