package com.alone.hotel.service;

import com.alone.hotel.dto.EmployeeExecution;
import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.Position;
import com.alone.hotel.enums.EmployeeStateEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.channels.MulticastChannel;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-16 09:30
 * @Description:
 */
@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testInsertEmployee() throws IOException {
        Employee employee = new Employee();
        employee.setEmployeeName("王二麻子");
        employee.setEmployeeAge(34);
        employee.setEmployeeGender(1);
        employee.setEmployeeCardNumber("103945844930123478");
        File cardImgFile = new File("E:\\proresources\\images\\kidsama.jpg");
        InputStream inputStream = new FileInputStream(cardImgFile);
        MultipartFile cardFile = new MockMultipartFile(cardImgFile.getName(), inputStream);
        File faceImgFile = new File("E:\\proresources\\images\\latestbg.jpg");
        InputStream inputStream1 = new FileInputStream(faceImgFile);
        MultipartFile faceFile = new MockMultipartFile(faceImgFile.getName(), inputStream1);
        Position position = new Position();
        position.setPositionId(2);
        employee.setPosition(position);
        EmployeeExecution employeeExecution = employeeService.insertEmployee(employee, cardFile, faceFile);
        assertEquals(EmployeeStateEnum.SUCCESS.getState(), employeeExecution.getState());
    }

    @Test
    public void testUpdateEmployee() throws IOException {
        Employee employee = new Employee();
        employee.setEmployeeId("1");
        File cardImgFile = new File("E:\\she said\\life\\Subaru.png");
        InputStream inputStream = new FileInputStream(cardImgFile);
        MultipartFile cardFile = new MockMultipartFile(cardImgFile.getName(), inputStream);
        File faceImgFile = new File("E:\\proresources\\images\\latestbg.jpg");
        InputStream inputStream1 = new FileInputStream(faceImgFile);
        MultipartFile faceFile = new MockMultipartFile(faceImgFile.getName(), inputStream1);
        EmployeeExecution  employeeExecution = employeeService.updateEmployee(employee, cardFile, faceFile);
        assertEquals(EmployeeStateEnum.SUCCESS.getState(), employeeExecution.getState());
    }


}