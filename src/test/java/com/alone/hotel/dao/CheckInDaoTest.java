package com.alone.hotel.dao;

import com.alone.hotel.entity.CheckIn;
import com.alone.hotel.entity.Customer;
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
 * @CreateTime: 2020-03-28 14:09
 * @Description:
 */
@SpringBootTest
public class CheckInDaoTest {
    @Autowired
    private CheckInDao checkInDao;

    @Test
    public void testAddCheckInMessage(){
        Room room = new Room();
        room.setRoomId(102);
        Customer customer = new Customer();
        customer.setCustomerCardNumber("12345789012345678");
        CheckIn checkIn = new CheckIn();
        checkIn.setRoom(room);
        checkIn.setCustomer(customer);
        int effectedNum = checkInDao.addCheckInMessage(checkIn);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryCheckInByRoom(){
        Room room = checkInDao.queryCheckInByRoom(102);
        assertEquals(2, room.getCustomerList().size());
    }

    @Test
    public void testQueryCheckInByCustomer(){
        CheckIn checkIn = checkInDao.queryCheckInByCustomer("103945844930123478");
        assertEquals("测试", checkIn.getRoom().getRoomDesc());
    }

    @Test
    public void testDeleteCheckInMessage(){
        Room room = new Room();
        room.setRoomId(102);
        Customer customer = new Customer();
        customer.setCustomerCardNumber("12345789012345678");
        CheckIn checkIn = new CheckIn();
        checkIn.setRoom(room);
        checkIn.setCustomer(customer);
        int effectNum = checkInDao.deleteCheckInMessage(checkIn);
        assertEquals(1, effectNum);
    }
}