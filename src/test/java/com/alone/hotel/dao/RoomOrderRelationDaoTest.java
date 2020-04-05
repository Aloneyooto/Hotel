package com.alone.hotel.dao;

import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.Room;
import com.alone.hotel.entity.RoomOrder;
import com.alone.hotel.entity.RoomOrderRelation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-30 16:08
 * @Description:
 */
@SpringBootTest
public class RoomOrderRelationDaoTest {
    @Autowired
    private RoomOrderRelationDao roomOrderRelationDao;

    @Test
    public void testAddRoomOrderRelation(){
        RoomOrderRelation roomOrderRelation = new RoomOrderRelation();
        RoomOrder roomOrder = new RoomOrder();
        roomOrder.setOrderId("1");
        Room room = new Room();
        room.setRoomId(101);
        roomOrderRelation.setRoomOrder(roomOrder);
        roomOrderRelation.setRoom(room);
        int effectedNum = roomOrderRelationDao.addRoomOrderRelation(roomOrderRelation);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testBatchAddRoomOrderRelation(){
        List<RoomOrderRelation> roomOrderRelationList = new ArrayList<RoomOrderRelation>();
        RoomOrderRelation roomOrderRelation = new RoomOrderRelation();
        RoomOrder roomOrder = new RoomOrder();
        roomOrder.setOrderId("1");
        Room room = new Room();
        room.setRoomId(101);
        roomOrderRelation.setRoomOrder(roomOrder);
        roomOrderRelation.setRoom(room);
        roomOrderRelationList.add(roomOrderRelation);
        RoomOrderRelation roomOrderRelation1 = new RoomOrderRelation();
        Room room1 = new Room();
        room1.setRoomId(102);
        roomOrderRelation1.setRoomOrder(roomOrder);
        roomOrderRelation1.setRoom(room1);
        roomOrderRelationList.add(roomOrderRelation1);
        int effectedNum = roomOrderRelationDao.batchAddRoomOrderRelation(roomOrderRelationList);
        assertEquals(2, effectedNum);
    }

    @Test
    public void testQueryCustomerByOrderId(){
        RoomOrder roomOrder = roomOrderRelationDao.queryRoomByOrderId("1");
        assertEquals(1, roomOrder.getRoomList().size());
    }

    @Test
    public void testDeleteRoomOrderRelation(){
        RoomOrderRelation roomOrderRelation = new RoomOrderRelation();
        RoomOrder roomOrder = new RoomOrder();
        roomOrder.setOrderId("1");
        roomOrderRelation.setRoomOrder(roomOrder);
        int effectNum = roomOrderRelationDao.deleteRoomOrderRelation("1");
        assertEquals(1, effectNum);
    }
}