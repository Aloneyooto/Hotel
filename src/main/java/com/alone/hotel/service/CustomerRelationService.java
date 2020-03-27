package com.alone.hotel.service;

import com.alone.hotel.dto.CustomerRelationExecution;
import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.entity.CustomerRelation;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-24 08:48
 * @Description:
 */
public interface CustomerRelationService {
    /**
     * 插入关联信息
     * @param customerRelation
     * @return
     */
    CustomerRelationExecution addCustomerRelation(CustomerRelation customerRelation);

    /**
     * 查询某个账号所添加的顾客信息
     * @param customerRelation
     * @return
     */
    CustomerAccount queryCustomerByAccount(CustomerRelation customerRelation);

    /**
     * 查询某个顾客被哪个账号添加了
     * @param customerCardNumber
     * @return
     */
    Customer queryAccountByCustomer(String customerCardNumber);

    /**
     * 删除关联信息
     * @param customerRelation
     * @return
     */
    CustomerRelationExecution deleteCustomerRelation(CustomerRelation customerRelation);
}
