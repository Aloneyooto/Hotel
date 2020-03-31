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

    @PostMapping("/changepwd")
    private EmployeeAccountExecution changePwd(String accountName, String oldPsw, String newPsw, String newPsw2){
        if(accountName != null && oldPsw != null && newPsw != null && newPsw2 != null){
            if(newPsw.equals(newPsw2)){
                //查找账号的原来信息
                EmployeeAccount employeeAccount = employeeAccountService.queryEmployeeAccountByName(accountName, oldPsw);
                if(employeeAccount != null){
                    //将密码更换成新的
                    employeeAccount.setAccountPassword(newPsw);
                    EmployeeAccountExecution employeeAccountExecution = employeeAccountService.updateEmployeeAccount(employeeAccount);
                    return employeeAccountExecution;
                } else {
                    //旧密码错误
                    return new EmployeeAccountExecution(EmployeeAccountStateEnum.OLD_PASSWORD_ERROR);
                }
            } else {
                //两次输入的密码不一致
                return new EmployeeAccountExecution(EmployeeAccountStateEnum.NEW_PASSWORD_ERROR);
            }
        } else {
            return new EmployeeAccountExecution(EmployeeAccountStateEnum.EMPLOYEE_ACCOUNT_EMPTY);
        }
    }
}
