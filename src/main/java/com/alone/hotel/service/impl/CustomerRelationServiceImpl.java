package com.alone.hotel.service.impl;

import com.alone.hotel.dao.CustomerRelationDao;
import com.alone.hotel.dto.CustomerRelationExecution;
import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.entity.CustomerRelation;
import com.alone.hotel.enums.CustomerRelationStateEnum;
import com.alone.hotel.exceptions.CustomerRelationException;
import com.alone.hotel.service.CustomerRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-24 08:53
 * @Description:
 */
@Service
public class CustomerRelationServiceImpl implements CustomerRelationService {
    @Autowired
    private CustomerRelationDao customerRelationDao;
    
    @Override
    @Transactional
    public CustomerRelationExecution addCustomerRelation(CustomerRelation customerRelation) {
        if(customerRelation != null && customerRelation.getAccount() != null && customerRelation.getCustomer() != null){
            try{
                int effectedNum = customerRelationDao.addCustomerRelation(customerRelation);
                if(effectedNum <= 0){
                    throw new CustomerRelationException(CustomerRelationStateEnum.INNER_ERROR.getStateInfo());
                }
                return new CustomerRelationExecution(CustomerRelationStateEnum.SUCCESS);
            } catch (Exception e){
                throw new CustomerRelationException(CustomerRelationStateEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new CustomerRelationExecution(CustomerRelationStateEnum.EMPTY);
        }
    }

    @Override
    public CustomerAccount queryCustomerByAccount(CustomerRelation customerRelation) {
        return customerRelationDao.queryCustomerByAccount(customerRelation);
    }

    @Override
    public Customer queryAccountByCustomer(String customerCardNumber) {
        return customerRelationDao.queryAccountByCustomer(customerCardNumber);
    }

    @Override
    @Transactional
    public CustomerRelationExecution deleteCustomerRelation(CustomerRelation customerRelation) {
        if(customerRelation != null){
            try {
                int effectedNum = customerRelationDao.deleteCustomerRelation(customerRelation);
                if(effectedNum <= 0){
                    throw new CustomerRelationException(CustomerRelationStateEnum.INNER_ERROR.getStateInfo());
                }
                return new CustomerRelationExecution(CustomerRelationStateEnum.SUCCESS);
            } catch (Exception e){
                throw new CustomerRelationException(CustomerRelationStateEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new CustomerRelationExecution(CustomerRelationStateEnum.EMPTY);
        }
    }
}
