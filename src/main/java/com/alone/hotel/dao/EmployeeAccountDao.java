package com.alone.hotel.dao;

import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.EmployeeAccount;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-17 21:46
 * @Description:
 */
public interface EmployeeAccountDao {
    /**
     * 插入员工账号
     * @param employeeAccount
     * @return
     */
    int addEmployeeAccount(EmployeeAccount employeeAccount);

    /**
     * 通过用户名和密码查找账号
     * @param accountName
     * @return
     */
    EmployeeAccount queryEmployeeAccountByName(String accountName, String accountPassword);

    /**
     * 根据检索条件查询员工账号
     * @return
     */
    List<EmployeeAccount> queryEmployeeAccountList();

    /**
     * 修改账户信息
     * @param employeeAccount
     * @return
     */
    int updateEmployeeAccount(EmployeeAccount employeeAccount);

    /**
     * 删除账户信息
     * @param accountName
     * @return
     */
    int deleteEmployeeAccount(String accountName);
}
