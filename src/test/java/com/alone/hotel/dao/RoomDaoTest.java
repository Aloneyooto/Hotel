package com.alone.hotel.dao;

import com.alone.hotel.entity.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        Room room = roomDao.queryRoomById(102);
        assertEquals(1, room.getRoomImgList().size());
    }

    @Test
    public void testQueryRoomList(){
        Room roomCondition = new Room();
        roomCondition.setRoomType(1);
        List<Room> roomList = roomDao.queryRoomList(roomCondition, 0, 3);
        assertEquals(2, roomList.size());
        int count = roomDao.queryRoomCount(roomCondition);
        assertEquals(2, count);
    }

    @Test
    public void testUpdateRoom(){
        Room newroom = new Room();
        newroom.setRoomId(101);
        newroom.setRoomType(2);
        int effectedNum = roomDao.updateRoom(newroom);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testDeleteRoom(){
        int roomId = 101;
        int effectedNum = roomDao.deleteRoom(roomId);
        assertEquals(1, effectedNum);
    }
}