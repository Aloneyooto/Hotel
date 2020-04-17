package com.alone.hotel.dao;

import com.alone.hotel.entity.CustomerAccount;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-23 08:37
 * @Description:
 */
public interface CustomerAccountDao {
    /**
     * 添加账户
     * @param customerAccount
     * @return
     */
    int addCustomerAccount(CustomerAccount customerAccount);

    /**
     * 通过用户名,密码查找账号
     * @param accountName
     * @return
     */
    CustomerAccount queryCustomerAccountByNameAndPsw(String accountName, String accountPassword);

    /**
     * 通过用户名查找账号
     * @param accountName
     * @return
     */
    CustomerAccount queryCustomerAccountByName(String accountName);

    /**
     * 查找全部账号
     * @return
     */
    List<CustomerAccount> queryCustomerAccountList();

    /**
     * 修改账户信息
     * @param customerAccount
     * @return
     */
    int updateCustomerAccount(CustomerAccount customerAccount);

    /**
     * 删除账户信息
     * @param accountName
     * @return
     */
    int deleteCustomerAccount(String accountName);
}
