package com.alone.hotel.service.impl;

import com.alone.hotel.dao.CustomerAccountDao;
import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.enums.CustomerAccountStateEnum;
import com.alone.hotel.exceptions.CustomerAccountException;
import com.alone.hotel.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-24 08:34
 * @Description:
 */
@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {
    @Autowired
    private CustomerAccountDao customerAccountDao;
    
    @Override
    @Transactional
    public CustomerAccountExecution addCustomerAccount(CustomerAccount customerAccount) {
        if(customerAccount != null){
            try{
                int effectedNum = customerAccountDao.addCustomerAccount(customerAccount);
                if(effectedNum <= 0){
                    throw new CustomerAccountException(CustomerAccountStateEnum.INNER_ERROR.getStateInfo());
                }
                return new CustomerAccountExecution(CustomerAccountStateEnum.SUCCESS);
            } catch (Exception e){
                throw new CustomerAccountException(CustomerAccountStateEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
        }
    }

    @Override
    public CustomerAccount queryCustomerAccountByName(String accountName, String password) {
        return customerAccountDao.queryCustomerAccountByName(accountName, password);
    }

    @Override
    @Transactional
    public CustomerAccountExecution updateCustomerAccount(CustomerAccount customerAccount) {
        if(customerAccount != null){
            try{
                int effectNum = customerAccountDao.updateCustomerAccount(customerAccount);
                if(effectNum <= 0){
                    throw new CustomerAccountException(CustomerAccountStateEnum.INNER_ERROR.getStateInfo());
                }
                return new CustomerAccountExecution(CustomerAccountStateEnum.SUCCESS, customerAccount);
            } catch (Exception e){
                throw new CustomerAccountException(CustomerAccountStateEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public CustomerAccountExecution deleteCustomerAccount(String accountName) {
        if(accountName != null){
            try {
                int effectNum = customerAccountDao.deleteCustomerAccount(accountName);
                if(effectNum <= 0){
                    throw new CustomerAccountException(CustomerAccountStateEnum.INNER_ERROR.getStateInfo());
                }
                return new CustomerAccountExecution(CustomerAccountStateEnum.SUCCESS);
            } catch (Exception e){
                throw new CustomerAccountException(CustomerAccountStateEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.ACCOUNT_NAME_EMPTY);
        }
    }

}
