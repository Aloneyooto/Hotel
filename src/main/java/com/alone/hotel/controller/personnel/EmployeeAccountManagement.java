package com.alone.hotel.controller.personnel;

import com.alibaba.fastjson.JSONObject;
import com.alone.hotel.dto.EmployeeAccountExecution;
import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.EmployeeAccount;
import com.alone.hotel.enums.EmployeeAccountStateEnum;
import com.alone.hotel.service.EmployeeAccountService;
import com.alone.hotel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.personnel
 * @Author: Alone
 * @CreateTime: 2020-03-31 10:36
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/personnel")
public class EmployeeAccountManagement {
    @Autowired
    private EmployeeAccountService employeeAccountService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    private EmployeeAccountExecution login(@RequestBody JSONObject jsonObject){
        //String accountName, String password
        String accountName = jsonObject.getString("accountName");
        String password = jsonObject.getString("password");
        if(accountName != null && password != null){
            EmployeeAccount employeeAccount = employeeAccountService.queryEmployeeAccountByName(accountName, password);
            if(employeeAccount != null){
                //查询账号权限
                Employee employee = employeeService.queryEmployeeById(employeeAccount.getAccountName());
                employeeAccount.setAccountPower(employee.getPosition().getPositionId());
                return new EmployeeAccountExecution(EmployeeAccountStateEnum.SUCCESS, employeeAccount);
            } else {
                return new EmployeeAccountExecution(EmployeeAccountStateEnum.EMPLOYEE_ACCOUNT_ERROR);
            }
        } else {
            return new EmployeeAccountExecution(EmployeeAccountStateEnum.EMPLOYEE_ACCOUNT_EMPTY);
        }
    }

    @PostMapping("/changepwd")
    private EmployeeAccountExecution changePwd(@RequestBody JSONObject jsonObject){
        String accountName = jsonObject.get("accountName").toString();
        String oldPsw = jsonObject.get("oldPsw").toString();
        String newPsw = jsonObject.get("newPsw").toString();
        String newPsw2 = jsonObject.get("newPsw2").toString();
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
