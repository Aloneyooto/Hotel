package com.alone.hotel.service;

import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.CustomerAccount;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    CustomerAccount queryCustomerAccountByNameAndPsw(String accountName, String password);

    /**
     * 通过用户名查询账号
     * @param accountName
     * @return
     */
    CustomerAccount queryCustomerAccountByName(String accountName);

    /**
     * 查询账号列表
     * @return
     */
    List<CustomerAccount> queryCustomerAccountList();

    /**
     * 更新账号信息
     * @param customerAccount
     * @return
     */
    CustomerAccountExecution updateCustomerAccount(CustomerAccount customerAccount, MultipartFile headImg);

    /**
     * 删除账号
     * @param accountName
     * @return
     */
    CustomerAccountExecution deleteCustomerAccount(String accountName);
}
