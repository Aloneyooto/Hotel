package com.alone.hotel.service;

import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.entity.CustomerAccount;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-23 21:48
 * @Description:
 */
public interface CustomerAccountService {
    /**
     * 添加账号
     * @param customerAccount
     * @return
     */
    CustomerAccountExecution addCustomerAccount(CustomerAccount customerAccount);

    /**
     * 查询账号信息
     * @param accountName
     * @return
     */
    CustomerAccount queryCustomerAccountByName(String accountName, String password);

    /**
     * 更新账号信息
     * @param customerAccount
     * @return
     */
    CustomerAccountExecution updateCustomerAccount(CustomerAccount customerAccount);

    /**
     * 删除账号
     * @param accountName
     * @return
     */
    CustomerAccountExecution deleteCustomerAccount(String accountName);
}
