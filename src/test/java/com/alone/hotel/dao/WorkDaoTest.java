package com.alone.hotel.dao;

import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.Work;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-19 19:39
 * @Description:
 */
@SpringBootTest
public class WorkDaoTest {
    @Autowired
    private WorkDao workDao;

    @Test
    public void testAddWorkMessage(){
        Employee employee = new Employee();
        employee.setEmployeeId("1");
        Work work = new Work();
        work.setEmployee(employee);
        Calendar cworkTime = Calendar.getInstance();
        cworkTime.set(2020, 3, 21, 0, 0, 0);
        Calendar cstartTime = Calendar.getInstance();
        cstartTime.set(2020, 3, 21, 8, 0, 0);
        Calendar cendTime = Calendar.getInstance();
        cendTime.set(2020, 3, 21, 17, 0, 0);
        Date workTime = cworkTime.getTime();
        Date startTime = cstartTime.getTime();
        Date endTime = cendTime.getTime();
        work.setEmployee(employee);
        work.setWorkTime(workTime);
        work.setStartTime(startTime);
        work.setEndTime(endTime);
        work.setStatus(0);
        int effectedNum = workDao.addWorkMessage(work);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryWorkTimeById(){
        List<Work> workTimeList = workDao.queryWorkTimeById("1");
        for (Work work : workTimeList) {
            System.out.println(work.getWorkTime());
            System.out.println(work.getStartTime());
            System.out.println(work.getEndTime());
        }
        assertEquals(2, workTimeList.size());
    }

    @Test
    public void testUpdateWorkTime(){
        Work work = new Work();
        Employee employee = new Employee();
        employee.setEmployeeId("1");
        work.setEmployee(employee);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 3, 21, 0, 0, 0);
        System.out.println(calendar.getTime());
        Date date = calendar.getTime();
        work.setWorkTime(date);
        work.setStatus(1);
        int effectedNum = workDao.updateWorkTime(work);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testDeleteWorkTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 3, 21, 0, 0, 0);
        int effectedNum = workDao.deleteWorkTime("1", calendar.getTime());
        assertEquals(1, effectedNum);
    }
}