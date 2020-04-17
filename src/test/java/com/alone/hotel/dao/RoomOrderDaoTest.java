package com.alone.hotel.dao;

import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.entity.Room;
import com.alone.hotel.entity.RoomOrder;
import com.alone.hotel.entity.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-28 15:12
 * @Description:
 */
@SpringBootTest
public class RoomOrderDaoTest {
    @Autowired
    private RoomOrderDao roomOrderDao;

    @Test
    public void testAddRoomOrder(){
        RoomOrder roomOrder = new RoomOrder();
        roomOrder.setOrderId("1");

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setAccountName("alone");
        customerAccount.setAccountPassword("123456");
        roomOrder.setAccount(customerAccount);

        RoomType roomType = new RoomType();
        roomType.setTypeId(1);
        roomOrder.setRoomType(roomType);
        roomOrder.setRoomAmount(1);
        roomOrder.setOrderPrice(105.2d);
        Calendar cstartTime = Calendar.getInstance();
        //月份要减一
        cstartTime.set(2020, 3, 28, 0, 0, 0);
        Date startTime = cstartTime.getTime();
        roomOrder.setStartTime(startTime);

        System.out.println(startTime);

        Calendar cendTime = Calendar.getInstance();
        cendTime.set(2020, 3, 29, 0, 0, 0);
        Date endTime = cendTime.getTime();
        roomOrder.setEndTime(endTime);

        roomOrder.setOrderStatus(0);
        roomOrder.setHandInTime(new Date());

        int effectNum = roomOrderDao.addRoomOrder(roomOrder);
        assertEquals(1, effectNum);
    }

    @Test
    public void testQueryRoomOrderByAccountName(){
        RoomOrder orderCondition = new RoomOrder();
        RoomType roomType = new RoomType();
        roomType.setTypeId(1);
        orderCondition.setRoomType(roomType);
        List<RoomOrder> roomOrderList = roomOrderDao.queryRoomOrderByCondition(orderCondition);
        assertEquals(1, roomOrderList.size());
    }

    @Test
    public void testQueryOrderCount(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 2, 28, 0, 0, 0);
        int effectNum = roomOrderDao.queryOrderCount(calendar.getTime());
        assertEquals(1, effectNum);
    }

    @Test
    public void testUpdateRoomOrder(){
        RoomOrder roomOrder = new RoomOrder();
        roomOrder.setOrderId("1");
        roomOrder.setOrderStatus(1);
        int effectNum = roomOrderDao.updateRoomOrder(roomOrder);
        assertEquals(1, effectNum);
    }

    @Test
    public void testDeleteRoomOrder(){
        int effectNum = roomOrderDao.deleteRoomOrder("1");
        assertEquals(1, effectNum);
    }
}