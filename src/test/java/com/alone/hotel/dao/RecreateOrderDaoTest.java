package com.alone.hotel.dao;

import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.RecreateOrder;
import com.alone.hotel.entity.Recreation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-29 14:17
 * @Description:
 */
@SpringBootTest
public class RecreateOrderDaoTest {
    @Autowired
    private RecreateOrderDao recreateOrderDao;

    @Test
    public void testAddRecreateOrder(){
        RecreateOrder recreateOrder = new RecreateOrder();
        recreateOrder.setOrderId("1");
        Customer customer = new Customer();
        customer.setCustomerCardNumber("12345789012345678");
        recreateOrder.setCustomer(customer);
        Recreation recreation = new Recreation();
        recreation.setRecreationId(1);
        recreateOrder.setRecreation(recreation);
        recreateOrder.setOrderStatus(0);
        recreateOrder.setStartTime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 2, 29, 19, 0, 0);
        recreateOrder.setEndTime(calendar.getTime());
        recreateOrder.setOrderPrice(60d);
        recreateOrder.setHandInTime(new Date());
        int effectedNum = recreateOrderDao.addRecreateOrder(recreateOrder);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryRecreateOrderByCustomer(){
        RecreateOrder recreateOrder = new RecreateOrder();
        Customer customer = new Customer();
        customer.setCustomerCardNumber("12345789012345678");
        recreateOrder.setCustomer(customer);
        List<RecreateOrder> recreateOrders = recreateOrderDao.queryRecreateOrderByCustomer(recreateOrder);
        assertEquals(1, recreateOrders.size());
    }

    @Test
    public void testQueryOrderCount(){
        int effectNum = recreateOrderDao.queryOrderCount(new Date());
        assertEquals(1, effectNum);
    }

    @Test
    public void testUpdateRecreateOrder(){
        RecreateOrder recreateOrder = new RecreateOrder();
        recreateOrder.setOrderId("1");
        recreateOrder.setOrderStatus(1);
        int effectNum = recreateOrderDao.updateRecreateOrder(recreateOrder);
        assertEquals(1, effectNum);
    }

    @Test
    public void testDeleteRecreateOrder(){
        int effectNum = recreateOrderDao.deleteRecreateOrder("1");
        assertEquals(1, effectNum);
    }
}