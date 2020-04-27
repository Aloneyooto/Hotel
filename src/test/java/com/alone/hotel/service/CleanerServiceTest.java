package com.alone.hotel.service;

import com.alone.hotel.dto.CleanerExecution;
import com.alone.hotel.entity.Cleaner;
import com.alone.hotel.entity.Employee;
import com.alone.hotel.enums.ResultEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-16 21:46
 * @Description:
 */
@SpringBootTest
public class CleanerServiceTest {
    @Autowired
    private CleanerService cleanerService;

    @Test
    public void testAddCleaner(){
        Cleaner cleaner = new Cleaner();
        Employee employee = new Employee();
        employee.setEmployeeId("2");
        cleaner.setEmployee(employee);
        cleaner.setRoomFloor("1层");
        CleanerExecution cleanerExecution = cleanerService.addCleaner(cleaner);
        assertEquals(ResultEnum.POSITION_TYPE_ERROR.getState(), cleanerExecution.getState());
    }
}