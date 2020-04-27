package com.alone.hotel.service;

import com.alone.hotel.dto.WorkExecution;
import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.Work;
import com.alone.hotel.enums.ResultEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-22 21:33
 * @Description:
 */
@SpringBootTest
public class WorkServiceTest {
    @Autowired
    private WorkService workService;

    @Test
    public void testAdd(){
        Employee employee = new Employee();
        employee.setEmployeeId("1");
        Work work = new Work();
        work.setEmployee(employee);
        Calendar cworkTime = Calendar.getInstance();
        cworkTime.set(2020, 2, 24, 0, 0, 0);
        Calendar cstartTime = Calendar.getInstance();
        cstartTime.set(2020, 2, 24, 8, 0, 0);
        Calendar cendTime = Calendar.getInstance();
        cendTime.set(2020, 2, 24, 17, 0, 0);
        Date workTime = cworkTime.getTime();
        Date startTime = cstartTime.getTime();
        Date endTime = cendTime.getTime();
        work.setEmployee(employee);
        work.setWorkTime(workTime);
        work.setStartTime(startTime);
        work.setEndTime(endTime);
        //work.setStatus(0);
        WorkExecution effectedMessage = workService.addWorkMessage(work);
        assertEquals(ResultEnum.SUCCESS.getState(), effectedMessage.getState());
    }
}