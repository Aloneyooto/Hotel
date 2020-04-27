package com.alone.hotel.service;

import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.enums.ResultEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-04-01 15:38
 * @Description:
 */
@SpringBootTest
public class CustomerAccountServiceTest {
    @Autowired
    private CustomerAccountService customerAccountService;

    @Test
    public void testAddCustomerAccount() throws IOException {
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setAccountName("emmm");
        customerAccount.setAccountPassword("123456");
        customerAccount.setFlag(0);
        customerAccount.setDeposit(200d);
        File file = new File("E:\\she said\\life\\mao.jpg");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
        CustomerAccountExecution customerAccountExecution = customerAccountService.addCustomerAccount(customerAccount);
        assertEquals(customerAccountExecution.getState(), ResultEnum.SUCCESS.getState());
    }

    @Test
    public void testUpdateCustomerAccount() throws IOException {
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setAccountName("test");
        customerAccount.setAccountPassword("12345678");
        File file = new File("E:\\she said\\life\\mao.jpg");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
        CustomerAccountExecution customerAccountExecution = customerAccountService.updateCustomerAccount(customerAccount, multipartFile);
        assertEquals(customerAccountExecution.getState(), ResultEnum.SUCCESS.getState());
    }
}