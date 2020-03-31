package com.alone.hotel.controller.personnel;

import com.alone.hotel.dto.EmployeeAccountExecution;
import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.EmployeeAccount;
import com.alone.hotel.enums.EmployeeAccountStateEnum;
import com.alone.hotel.service.EmployeeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.personnel
 * @Author: Alone
 * @CreateTime: 2020-03-31 10:36
 * @Description:
 */
@RestController
@RequestMapping("/personnel")
public class EmployeeAccountManagement {
    @Autowired
    private EmployeeAccountService employeeAccountService;

    @PostMapping("/login")
    private EmployeeAccountExecution login(String accountname, String password){
        if(accountname != null && password != null){
            EmployeeAccount employeeAccount = employeeAccountService.queryEmployeeAccountByName(accountname, password);
            if(employeeAccount != null){
                return new EmployeeAccountExecution(EmployeeAccountStateEnum.SUCCESS, employeeAccount);
            } else {
                return new EmployeeAccountExecution(EmployeeAccountStateEnum.EMPLOYEE_ACCOUNT_ERROR);
            }
        } else {
            return new EmployeeAccountExecution(EmployeeAccountStateEnum.EMPLOYEE_ACCOUNT_EMPTY);
        }
    }
}
