package com.alone.hotel.dao;

import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.Position;
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
 * @CreateTime: 2020-03-14 21:37
 * @Description:
 */
@SpringBootTest
public class EmployeeDaoTest {
    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testInsertEmployee(){
        Employee employee = new Employee();
        employee.setEmployeeId("3");
        employee.setEmployeeName("基德");
        employee.setEmployeeAge(30);
        employee.setEmployeeGender(1);
        employee.setEmployeeCardNumber("102485934023234567");
        employee.setEmployeeCardImg("test");
        employee.setEmployeePhone("10493845932");
        Position position = new Position();
        position.setPositionId(1);
        employee.setPosition(position);
        employee.setEmployeeCreateTime(new Date());
        int effectNum = employeeDao.insertEmployee(employee);
        assertEquals(1, effectNum);
    }

    @Test
    public void testQueryEmployeeById(){
        String employeeId = "3";
        Employee employee = employeeDao.queryEmployeeById(employeeId);
        assertEquals("经理", employee.getPosition().getPositionName());
    }

    @Test
    public void testQueryEmployeeList(){
        Employee employee = new Employee();
        employee.setEmployeeGender(1);
        List<Employee> employeeList = employeeDao.queryEmployeeList(employee, 0, 2);
        assertEquals(2, employeeList.size());
        int count = employeeDao.queryEmployeeCount(employee);
        assertEquals(3, count);
    }

    @Test
    public void testUpdateEmployee(){
        Employee employee = new Employee();
        employee.setEmployeeId("3");
        employee.setEmployeeAge(18);
        employee.setEmployeeGender(0);
        Position position = new Position();
        position.setPositionId(2);
        employee.setPosition(position);
        int effectedNum = employeeDao.updateEmployee(employee);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testDeleteEmployee(){
        String employeeId = "3";
        int effectedNum = employeeDao.deleteEmployee(employeeId);
        assertEquals(1, effectedNum);
    }
}