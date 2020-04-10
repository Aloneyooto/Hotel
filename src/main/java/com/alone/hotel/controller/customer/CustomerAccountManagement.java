package com.alone.hotel.controller.customer;

import com.alibaba.fastjson.JSONObject;
import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.enums.CustomerAccountStateEnum;
import com.alone.hotel.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.customer
 * @Author: Alone
 * @CreateTime: 2020-03-24 20:26
 * @Description:
 */
@RestController
@RequestMapping("/customer")
public class CustomerAccountManagement {
    @Autowired
    private CustomerAccountService customerAccountService;

    @PostMapping(value = "/login")
    public CustomerAccountExecution login(@RequestBody CustomerAccount customerAccount){
        if(customerAccount.getAccountName() != null && customerAccount.getAccountPassword() != null){
            CustomerAccount account = customerAccountService.queryCustomerAccountByName(customerAccount.getAccountName(), customerAccount.getAccountPassword());
            if(account != null){
                return new CustomerAccountExecution(CustomerAccountStateEnum.SUCCESS);
            } else {
                return new CustomerAccountExecution(CustomerAccountStateEnum.NAME_OR_PASSWORD_ERROR);
            }
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
        }
    }

    @PostMapping(value = "/register")
    public CustomerAccountExecution register(@RequestBody CustomerAccount customerAccount){
        if(customerAccount.getAccountName() != null && customerAccount.getAccountPassword() != null){
            CustomerAccountExecution customerAccountExecution = customerAccountService.addCustomerAccount(customerAccount);
            return customerAccountExecution;
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
        }
    }

    @PostMapping(value = "/changepwd")
    public CustomerAccountExecution changePwd(@RequestBody JSONObject jsonObject){
        //String accountName, String oldPsw, String newPsw, String newPsw2
        String accountName = jsonObject.get("accountName").toString();
        String oldPsw = jsonObject.get("oldPsw").toString();
        String newPsw = jsonObject.get("newPsw").toString();
        String newPsw2 = jsonObject.get("newPsw2").toString();
        if(accountName != null && oldPsw != null && newPsw != null && newPsw2 != null){
            if(newPsw.equals(newPsw2)){
                //查找账号的原来信息
                CustomerAccount customerAccount = customerAccountService.queryCustomerAccountByName(accountName, oldPsw);
                if(customerAccount != null){
                    //将密码更换成新的
                    customerAccount.setAccountPassword(newPsw);
                    CustomerAccountExecution customerAccountExecution = customerAccountService.updateCustomerAccount(customerAccount, null);
                    return customerAccountExecution;
                } else {
                    //旧密码错误
                    return new CustomerAccountExecution(CustomerAccountStateEnum.NAME_OR_PASSWORD_ERROR);
                }
            } else {
                //两次输入的密码不一致
                return new CustomerAccountExecution(CustomerAccountStateEnum.NEW_PASSWORD_ERROR);
            }
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
        }
    }

}
