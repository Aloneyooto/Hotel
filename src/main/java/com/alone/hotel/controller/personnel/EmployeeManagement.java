package com.alone.hotel.controller.personnel;

import com.alone.hotel.dto.CleanerExecution;
import com.alone.hotel.dto.EmployeeAccountExecution;
import com.alone.hotel.dto.EmployeeExecution;
import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.EmployeeAccount;
import com.alone.hotel.enums.CleanerStateEnum;
import com.alone.hotel.enums.EmployeeAccountStateEnum;
import com.alone.hotel.enums.EmployeeStateEnum;
import com.alone.hotel.service.CleanerService;
import com.alone.hotel.service.EmployeeAccountService;
import com.alone.hotel.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.pattern.PathPattern;

import java.io.IOException;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.personnel
 * @Author: Alone
 * @CreateTime: 2020-03-16 10:07
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/personnel")
public class EmployeeManagement {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CleanerService cleanerService;
    @Autowired
    private EmployeeAccountService employeeAccountService;

    @PostMapping("/addemployee")
    private EmployeeExecution addEmployee(@RequestParam("employeeStr")String employeeStr,
                                          @RequestParam("cardImg")MultipartFile cardImg,
                                          @RequestParam("faceImg")MultipartFile faceImg){
        Employee employee = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            employee = mapper.readValue(employeeStr, Employee.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //空值判断
        if(employee != null && cardImg != null && faceImg != null){
            try{
                EmployeeExecution employeeExecution = employeeService.addEmployee(employee, cardImg, faceImg);
                if(employeeExecution.getState() == EmployeeStateEnum.SUCCESS.getState()){
                    try{
                        //自动生成账号
                        EmployeeAccount employeeAccount = new EmployeeAccount();
                        //用户名为工号
                        employeeAccount.setAccountName(employeeExecution.getEmployee().getEmployeeId());
                        //初始密码为123456
                        employeeAccount.setAccountPassword("123456");
                        EmployeeAccountExecution employeeAccountExecution = employeeAccountService.addEmployeeAccount(employeeAccount);
                        if(employeeAccountExecution.getState() != EmployeeAccountStateEnum.SUCCESS.getState()){
                            return new EmployeeExecution(EmployeeStateEnum.CREATE_ACCOUNT_ERROR);
                        }
                    } catch (Exception e){
                        return new EmployeeExecution(EmployeeStateEnum.CREATE_ACCOUNT_ERROR);
                    }
                    return new EmployeeExecution(EmployeeStateEnum.SUCCESS, employee);
                } else {
                    return new EmployeeExecution(EmployeeStateEnum.INSERT_ERROR);
                }
            } catch (IOException e) {
                return new EmployeeExecution(EmployeeStateEnum.INSERT_ERROR);
            }
        } else {
            return new EmployeeExecution(EmployeeStateEnum.EMPTY);
        }
    }

    @GetMapping("/getemployeebyid")
    private EmployeeExecution getEmployeeById(@RequestParam String employeeId){
        EmployeeExecution employeeExecution = null;
        if(employeeId != null){
            Employee employee = employeeService.queryEmployeeById(employeeId);
            employeeExecution = new EmployeeExecution(EmployeeStateEnum.SUCCESS, employee);
        } else {
            employeeExecution = new EmployeeExecution(EmployeeStateEnum.EMPTY);
        }
        return employeeExecution;
    }

    @GetMapping("/getemployeelist")
    private EmployeeExecution getEmployeeList(@RequestParam Employee employeeCondition, @RequestParam int pageIndex, @RequestParam int pageSize){
        //保证页码合法
        if(pageIndex > 0 && pageSize > 0){
            EmployeeExecution employeeExecution = employeeService.queryEmployeeList(employeeCondition, pageIndex, pageSize);
            return employeeExecution;
        } else {
            return new EmployeeExecution(EmployeeStateEnum.PAGE_ERROR);
        }
    }

    @PostMapping("/updateemployee")
    private EmployeeExecution updateEmployee(@RequestParam("employeeStr")String employeeStr, @RequestParam("cardImg")MultipartFile cardImg, @RequestParam("faceImg")MultipartFile faceImg){
        Employee employee = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            employee = mapper.readValue(employeeStr, Employee.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //空值判断
        if(employee != null){
            try{
                EmployeeExecution employeeExecution = employeeService.updateEmployee(employee, cardImg, faceImg);
                if(employeeExecution.getState() == EmployeeStateEnum.SUCCESS.getState()){
                    return new EmployeeExecution(EmployeeStateEnum.SUCCESS);
                } else {
                    return new EmployeeExecution(EmployeeStateEnum.UPDATE_ERROR);
                }
            } catch (IOException e) {
                return new EmployeeExecution(EmployeeStateEnum.UPDATE_ERROR);
            }
        } else {
            return new EmployeeExecution(EmployeeStateEnum.EMPTY);
        }
    }

    @PostMapping("/deleteEmployee")
    private EmployeeExecution deleteEmployee(@RequestParam String employeeId){
        EmployeeExecution employeeExecution = null;
        if(employeeId != null){
            try{
                Employee employee = employeeService.queryEmployeeById(employeeId);
                if(employee.getPosition().getPositionId() == 3){
                    try {
                        CleanerExecution cleanerExecution = cleanerService.deleteCleaner(employeeId);
                        if(cleanerExecution.getState() != CleanerStateEnum.SUCCESS.getState()){
                            return new EmployeeExecution(EmployeeStateEnum.CLEANER_DELETE_ERROR);
                        }
                        employeeExecution = employeeService.deleteEmployee(employeeId);
                    } catch (Exception e){
                        employeeExecution = new EmployeeExecution(EmployeeStateEnum.INNER_ERROR);
                    }
                }
            } catch (Exception e){
                employeeExecution = new EmployeeExecution(EmployeeStateEnum.INNER_ERROR);
            }
        } else {
            employeeExecution = new EmployeeExecution(EmployeeStateEnum.EMPLOYEE_ID_ERROR);
        }
        return employeeExecution;
    }
}
