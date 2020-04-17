package com.alone.hotel.dao;

import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.EmployeeAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-18 10:56
 * @Description:
 */
@SpringBootTest
public class EmployeeAccountDaoTest {
    @Autowired
    private EmployeeAccountDao employeeAccountDao;

    @Test
    public void testAddEmployeeAccount(){
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setAccountName("1");
        employeeAccount.setAccountPassword("123456");
        employeeAccount.setAccountPower(3);
        int effectedNum = employeeAccountDao.addEmployeeAccount(employeeAccount);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryEmployeeAccountByName(){
        EmployeeAccount employeeAccount = employeeAccountDao.queryEmployeeAccountByName("1", "123456");
        assertEquals("123456", employeeAccount.getAccountPassword());
    }

    @Test
    public void testQueryEmployeeList(){
        List<EmployeeAccount> employeeAccountList = employeeAccountDao.queryEmployeeAccountList();
        assertEquals(5, employeeAccountList.size());
    }

    @Test
    public void testUpdateEmployeeAccount(){
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setAccountName("1");
        employeeAccount.setAccountPassword("654321");
        int effectedNum = employeeAccountDao.updateEmployeeAccount(employeeAccount);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testDeleteEmployeeAccount(){
        int effectNum = employeeAccountDao.deleteEmployeeAccount("1");
        assertEquals(1, effectNum);
    }
}