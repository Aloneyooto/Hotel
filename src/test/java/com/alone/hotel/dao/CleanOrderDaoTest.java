package com.alone.hotel.dao;

import com.alone.hotel.entity.CleanOrder;
import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-04-24 21:57
 * @Description:
 */
@SpringBootTest
class CleanOrderDaoTest {

    @Autowired
    private CleanOrderDao cleanOrderDao;

    @Test
    public void testAddCleanOrder(){
        CleanOrder cleanOrder = new CleanOrder();
        cleanOrder.setOrderId("1");
        Room room = new Room();
        room.setRoomId(101);
        cleanOrder.setRoom(room);
        Employee employee = new Employee();
        employee.setEmployeeId("3");
        cleanOrder.setEmployee(employee);
        cleanOrder.setStatus(0);
        cleanOrder.setHandInTime(new Date());
        int effectedNum = cleanOrderDao.addCleanOrder(cleanOrder);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryCleanOrder(){
        List<CleanOrder> cleanOrderList = cleanOrderDao.queryCleanOrder(null);
        assertEquals(1, cleanOrderList.size());
        List<CleanOrder> cleanOrderList1 = cleanOrderDao.queryCleanOrder("3");
        assertEquals(1, cleanOrderList1.size());
        List<CleanOrder> cleanOrderList2 = cleanOrderDao.queryCleanOrder("4");
        assertEquals(0, cleanOrderList2.size());
    }

    @Test
    public void testUpdateCleanOrderStatus(){
        CleanOrder cleanOrder = new CleanOrder();
        Room room = new Room();
        room.setRoomId(101);
        cleanOrder.setRoom(room);
        Employee employee = new Employee();
        employee.setEmployeeId("3");
        cleanOrder.setEmployee(employee);
        cleanOrder.setStatus(1);
        int effectedNum = cleanOrderDao.updateCleanOrderStatus(cleanOrder);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testDeleteCleanOrder(){
        CleanOrder cleanOrder = new CleanOrder();
        Room room = new Room();
        room.setRoomId(101);
        cleanOrder.setRoom(room);
        Employee employee = new Employee();
        employee.setEmployeeId("3");
        cleanOrder.setEmployee(employee);
        int effectedNum = cleanOrderDao.deleteCleanOrder(cleanOrder);
        assertEquals(1, effectedNum);
    }
}