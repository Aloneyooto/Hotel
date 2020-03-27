package com.alone.hotel.dao;

import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.entity.CustomerRelation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-23 16:27
 * @Description:
 */
@SpringBootTest
public class CustomerRelationDaoTest {
    @Autowired
    private CustomerRelationDao customerRelationDao;

    @Test
    public void testAddCustomerRelation(){
        CustomerRelation customerRelation = new CustomerRelation();
        CustomerAccount customerAccount = new CustomerAccount();
        Customer customer = new Customer();
        customerAccount.setAccountName("alone");
        customer.setCustomerCardNumber("12345789012345678");
        customerRelation.setAccount(customerAccount);
        customerRelation.setCustomer(customer);
        int effectNum = customerRelationDao.addCustomerRelation(customerRelation);
        assertEquals(1, effectNum);
    }

    @Test
    public void testQueryCustomerByAccount(){
        CustomerAccount account = new CustomerAccount();
        account.setAccountName("alone");
        CustomerRelation customerRelation = new CustomerRelation();
        customerRelation.setAccount(account);
        CustomerAccount customerAccount = customerRelationDao.queryCustomerByAccount(customerRelation);
        assertEquals(2, customerAccount.getCustomerList().size());
    }

    @Test
    public void testQueryAccountByCustomer(){
        Customer customer = customerRelationDao.queryAccountByCustomer("09876543211234579");
        assertEquals(2, customer.getAccountList().size());
    }

    @Test
    public void testDeleteCustomerRelation(){
        CustomerRelation customerRelation = new CustomerRelation();
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setAccountName("alone");
        Customer customer = new Customer();
        customer.setCustomerCardNumber("12345789012345678");
        customerRelation.setAccount(customerAccount);
        customerRelation.setCustomer(customer);
        int effectedNum = customerRelationDao.deleteCustomerRelation(customerRelation);
        assertEquals(1, effectedNum);
    }
}