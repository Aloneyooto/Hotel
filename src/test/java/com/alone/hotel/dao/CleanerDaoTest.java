package com.alone.hotel.dao;

import com.alone.hotel.entity.Cleaner;
import com.alone.hotel.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-16 19:38
 * @Description:
 */
@SpringBootTest
public class CleanerDaoTest {
    @Autowired
    private CleanerDao cleanerDao;

    @Test
    public void testInsertCleaner(){
        Employee employee = new Employee();
        employee.setEmployeeId("1");
        Cleaner cleaner = new Cleaner();
        cleaner.setEmployee(employee);
        cleaner.setRoomFloor("1层");
        int effectedNum = cleanerDao.insertCleaner(cleaner);
        assertEquals(1, effectedNum);

//        Employee employee = new Employee();
//        employee.setEmployeeId("12345");
//        Cleaner cleaner = new Cleaner();
//        cleaner.setEmployee(employee);
//        cleaner.setRoomFloor("2层");
//        int effectNum = cleanerDao.insertCleaner(cleaner);
//        assertEquals(1, effectNum);
    }

    @Test
    public void testQueryCleanerById(){
        String employeeId = "1";
        Cleaner cleaner = cleanerDao.queryCleanerById(employeeId);
        assertEquals("2层", cleaner.getRoomFloor());
        assertEquals("张三", cleaner.getEmployee().getEmployeeName());
    }

    @Test
    public void testQueryCleaner(){
        String roomFloor = "2";
        List<Cleaner> cleanerList = cleanerDao.queryCleanerList(roomFloor, 0, 2);
        int count = cleanerDao.queryCleanerCount(roomFloor);
        assertEquals(2, cleanerList.size());
        assertEquals(3, count);
    }

    @Test
    public void testUpdateCleaner(){
        Employee employee = new Employee();
        employee.setEmployeeId("1");
        Cleaner cleaner = new Cleaner();
        cleaner.setEmployee(employee);
        cleaner.setRoomFloor("2层");
        int effectedNum = cleanerDao.updateCleaner(cleaner);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testDeleteCleaner(){
        int effectedNum = cleanerDao.deleteCleaner("1");
        assertEquals(1, effectedNum);
    }
}