package com.alone.hotel.dao;

import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.Inventory;
import com.alone.hotel.entity.PurchaseOrder;
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
 * @CreateTime: 2020-04-26 20:42
 * @Description:
 */
@SpringBootTest
class PurchaseOrderDaoTest {
    @Autowired
    private PurchaseOrderDao purchaseOrderDao;

    @Test
    public void testAddPurchaseOrder(){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderId("1");
        Room room = new Room();
        room.setRoomId(101);
        purchaseOrder.setRoom(room);
        Inventory inventory = new Inventory();
        inventory.setGoodsId(2);
        purchaseOrder.setGoods(inventory);
        Employee employee = new Employee();
        employee.setEmployeeId("1");
        purchaseOrder.setEmployee(employee);
        purchaseOrder.setGoodsAmount(200);
        purchaseOrder.setHandInTime(new Date());
        purchaseOrder.setLastEditTime(new Date());
        purchaseOrder.setStatus(0);
        int effectedNum = purchaseOrderDao.addPurchaseOrder(purchaseOrder);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryPurchaseOrder(){
        PurchaseOrder orderCondition = new PurchaseOrder();
        orderCondition.setStatus(0);
        List<PurchaseOrder> purchaseOrderList = purchaseOrderDao.queryPurchaseOrder(orderCondition);
        assertEquals(1, purchaseOrderList.size());
    }

    @Test
    public void testUpdatePurchaseOrder(){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderId("1");
        purchaseOrder.setStatus(1);
        purchaseOrder.setLastEditTime(new Date());
        int effectedNum = purchaseOrderDao.updatePurchaseOrder(purchaseOrder);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testDeletePurchaseOrder(){
        int effectNum = purchaseOrderDao.deletePurchaseOrder("1");
        assertEquals(1, effectNum);
    }
}