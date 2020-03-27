package com.alone.hotel.dao;

import com.alone.hotel.entity.CustomerAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-23 08:46
 * @Description:
 */
@SpringBootTest
public class CustomerAccountDaoTest {
    @Autowired
    private CustomerAccountDao customerAccountDao;

    @Test
    public void testAddCustomerAccount(){
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setAccountName("alone");
        customerAccount.setAccountPassword("123456");
        customerAccount.setFlag(0);
        customerAccount.setDeposit(1000d);
        int effectedNum = customerAccountDao.addCustomerAccount(customerAccount);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryCustomerAccountByName(){
        CustomerAccount customerAccount = customerAccountDao.queryCustomerAccountByName("alone", "123456");
        assertEquals(1000d, customerAccount.getDeposit());
    }

    @Test
    public void testUpdateCustomerAccount(){
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setAccountName("alone");
        customerAccount.setAccountPassword("12345678");
        int effectedNum = customerAccountDao.updateCustomerAccount(customerAccount);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testDeleteCustomerAccount(){
        int effectedNum = customerAccountDao.deleteCustomerAccount("alone");
        assertEquals(1, effectedNum);
    }
}