package com.alone.hotel.service;

import com.alone.hotel.dto.CustomerExecution;
import com.alone.hotel.entity.Customer;
import com.alone.hotel.enums.CustomerStateEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-23 20:52
 * @Description:
 */
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    
    @Test
    public void testAddCustomer() throws IOException {
        Customer customer = new Customer();
        customer.setCustomerName("王二麻子");
        customer.setCustomerAge(34);
        customer.setCustomerGender(1);
        customer.setCustomerCardNumber("103945844930123478");
        File cardImgFile = new File("E:\\proresources\\images\\kidsama.jpg");
        InputStream inputStream = new FileInputStream(cardImgFile);
        MultipartFile cardFile = new MockMultipartFile(cardImgFile.getName(), inputStream);
        File faceImgFile = new File("E:\\proresources\\images\\latestbg.jpg");
        InputStream inputStream1 = new FileInputStream(faceImgFile);
        MultipartFile faceFile = new MockMultipartFile(faceImgFile.getName(), inputStream1);
        CustomerExecution customerExecution = customerService.addCustomer(customer, cardFile, faceFile);
        assertEquals(CustomerStateEnum.SUCCESS.getState(), customerExecution.getState());
    }

    @Test
    public void testNoImgAddCustomer(){
        Customer customer = new Customer();
        customer.setCustomerCardNumber("234586948501234567");
        customer.setCustomerName("emmm");
        customer.setCustomerAge(45);
        customer.setCustomerGender(0);
        CustomerExecution customerExecution = customerService.addCustomer(customer, null, null);
        assertEquals(CustomerStateEnum.SUCCESS.getState(), customerExecution.getState());
    }

    @Test
    public void testQueryCustomerById(){
        Customer customer = customerService.queryCustomerById("103945844930123478");
        assertEquals("王二麻子", customer.getCustomerName());
    }

    @Test
    public void testUpdateCustomer() throws IOException {
        Customer customer = new Customer();
        customer.setCustomerCardNumber("234586948501234567");
        customer.setCustomerGender(1);
        File cardImgFile = new File("E:\\proresources\\images\\kidsama.jpg");
        InputStream inputStream = new FileInputStream(cardImgFile);
        MultipartFile cardFile = new MockMultipartFile(cardImgFile.getName(), inputStream);
        File faceImgFile = new File("E:\\she said\\life\\Subaru.png");
        InputStream inputStream1 = new FileInputStream(faceImgFile);
        MultipartFile faceFile = new MockMultipartFile(faceImgFile.getName(), inputStream1);
        CustomerExecution customerExecution = customerService.updateCustomer(customer, cardFile, faceFile);
        assertEquals(CustomerStateEnum.SUCCESS.getState(), customerExecution.getState());
    }

    @Test
    public void testDeleteCustomer(){
        CustomerExecution customerExecution = customerService.deleteCustomer("234586948501234567");
        assertEquals(CustomerStateEnum.SUCCESS.getState(), customerExecution.getState());
    }
}