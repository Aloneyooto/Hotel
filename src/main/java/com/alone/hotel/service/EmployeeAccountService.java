package com.alone.hotel.service;

import com.alone.hotel.dto.EmployeeAccountExecution;
import com.alone.hotel.entity.EmployeeAccount;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-18 15:01
 * @Description:
 */
public interface EmployeeAccountService {
    /**
     * 插入员工账号
     * @return
     */
    EmployeeAccountExecution addEmployeeAccount(EmployeeAccount employeeAccount);

    /**
     * 查找员工账号
     * @return
     */
    EmployeeAccount queryEmployeeAccountByName(String employeeAccountName);

    /**
     * 修改员工账号
     * @param employeeAccount
     * @return
     */
    EmployeeAccountExecution updateEmployeeAccount(EmployeeAccount employeeAccount);

    /**
     * 删除员工账号
     * @param employeeAccountName
     * @return
     */
    EmployeeAccountExecution deleteEmployeeAccount(String employeeAccountName);
}
