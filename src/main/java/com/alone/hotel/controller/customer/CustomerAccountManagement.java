package com.alone.hotel.controller.customer;

import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.enums.CustomerAccountStateEnum;
import com.alone.hotel.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/login")
    private CustomerAccountExecution login(String accountname, String password){
        if(accountname != null && password != null){
            CustomerAccount customerAccount = customerAccountService.queryCustomerAccountByName(accountname, password);
            if(customerAccount != null){
                return new CustomerAccountExecution(CustomerAccountStateEnum.SUCCESS, customerAccount);
            } else {
                return new CustomerAccountExecution(CustomerAccountStateEnum.NAME_OR_PASSWORD_ERROR);
            }
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
        }
    }

    @PostMapping("/register")
    private CustomerAccountExecution register(String accountName, String accountPassword){
        if(accountName != null && accountPassword != null){
            CustomerAccount customerAccount = new CustomerAccount();
            customerAccount.setAccountName(accountName);
            customerAccount.setAccountPassword(accountPassword);
            CustomerAccountExecution customerAccountExecution = customerAccountService.addCustomerAccount(customerAccount);
            return customerAccountExecution;
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
        }
    }

    @PostMapping("/changepwd")
    private CustomerAccountExecution changePwd(String accountName, String oldPsw, String newPsw, String newPsw2){
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

    //TODO logout
}
