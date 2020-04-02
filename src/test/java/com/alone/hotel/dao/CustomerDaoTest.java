package com.alone.hotel.dao;

import com.alone.hotel.entity.Customer;
import com.alone.hotel.utils.FaceUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-23 15:04
 * @Description:
 */
@SpringBootTest
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testAddCustomer(){
        Customer customer = new Customer();
        customer.setCustomerCardNumber("12345789012345678");
        customer.setCustomerName("诸葛多恩");
        customer.setCustomerAge(19);
        customer.setCustomerGender(0);
        int effectNum = customerDao.addCustomer(customer);
//        Customer customer = new Customer();
//        customer.setCustomerCardNumber("345678901234567890");
//        customer.setCustomerName("二傻子");
//        customer.setCustomerAge(20);
//        customer.setCustomerGender(0);
//        int effectNum = customerDao.addCustomer(customer)
        assertEquals(1, effectNum);
    }

    @Test
    public void testQueryCustomerById(){
        Customer customer = customerDao.queryCustomerById("12345789012345678");
        assertEquals("诸葛多恩", customer.getCustomerName());
    }

    @Test
    public void testQueryCustomerFaceImages(){
        List<Customer> faceImgList = customerDao.queryCustomerFaceImages();
        assertEquals(3, faceImgList.size());
    }

    @Test
    public void testUpdateCustomer() throws IOException {
        Customer customer = new Customer();
        customer.setCustomerCardNumber("12345789012345678");
        customer.setCustomerPhone("14723849302");
        int effectNum = customerDao.updateCustomer(customer);
        assertEquals(1, effectNum);
    }

    @Test
    public void testDeleteCustomer(){
        int effectNum = customerDao.deleteCustomer("12345789012345678");
        assertEquals(1, effectNum);
    }

    @Test
    public void testCompareFace(){
        List<Customer> customerList = customerDao.queryCustomerFaceImages();
        FaceUtil.initEngine();
        FaceUtil.getDataSoureFeature(customerList);
        String customerCardNumber = FaceUtil.compareFaces(new File("E:\\she said\\life\\yjs3.jpg"));
        FaceUtil.destoryEngine();
        System.out.println(customerCardNumber);
        assertEquals("12345789012345678", customerCardNumber);
    }
}