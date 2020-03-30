package com.alone.hotel.dao;

import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.RoomOrder;
import com.alone.hotel.entity.RoomOrderRelation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Customer customer = new Customer();
        customer.setCustomerCardNumber("12345789012345678");
        roomOrderRelation.setCustomer(customer);
        roomOrderRelation.setRoomOrder(roomOrder);
        int effectedNum = roomOrderRelationDao.addRoomOrderRelation(roomOrderRelation);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryCustomerByOrderId(){
        RoomOrder roomOrder = roomOrderRelationDao.queryCustomerByOrderId("1");
        assertEquals(1, roomOrder.getCustomerList().size());
    }

    @Test
    public void testDeleteRoomOrderRelation(){
        RoomOrderRelation roomOrderRelation = new RoomOrderRelation();
        RoomOrder roomOrder = new RoomOrder();
        roomOrder.setOrderId("1");
        Customer customer = new Customer();
        customer.setCustomerCardNumber("12345789012345678");
        roomOrderRelation.setCustomer(customer);
        roomOrderRelation.setRoomOrder(roomOrder);
        int effectNum = roomOrderRelationDao.deleteRoomOrderRelation("1");
        assertEquals(1, effectNum);
    }
}