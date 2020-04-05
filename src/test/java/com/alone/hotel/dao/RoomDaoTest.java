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
        room.setRoomFloor(1);
        room.setRoomDesc("大床 10-25m²");
        room.setRoomState(0);
        RoomType roomType = new RoomType();
        roomType.setTypeId(1);
        room.setRoomType(roomType);
        int effectNum = roomDao.insertRoom(room);
        assertEquals(1, effectNum);
    }

    @Test
    public void testQueryRoomById(){
        Room room = roomDao.queryRoomById(102);
        assertEquals(2, room.getRoomImgList().size());
    }

    @Test
    public void testQueryRoomList(){
        Room roomCondition = new Room();
        RoomType roomType = new RoomType();
        roomType.setTypeId(1);
        roomCondition.setRoomType(roomType);
        List<Room> roomList = roomDao.queryRoomList(roomCondition, 0, 3);
        assertEquals(2, roomList.size());
        int count = roomDao.queryRoomCount(roomCondition);
        assertEquals(2, count);
    }

    @Test
    public void testUpdateRoom(){
        Room newroom = new Room();
        newroom.setRoomId(101);
        newroom.setRoomState(0);
        RoomType roomType = new RoomType();
        roomType.setTypeId(1);
        newroom.setRoomType(roomType);
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