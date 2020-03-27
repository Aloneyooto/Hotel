package com.alone.hotel.dao;

import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.entity.CustomerRelation;
import org.apache.ibatis.annotations.Param;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-23 15:40
 * @Description:
 */
public interface CustomerRelationDao {
    /**
     * 插入关联信息
     * @param customerRelation
     * @return
     */
    int addCustomerRelation(CustomerRelation customerRelation);

    /**
     * 查询某个账号所添加的顾客信息
     * @param customerRelation
     * @return
     */
    CustomerAccount queryCustomerByAccount(@Param("customerRelation") CustomerRelation customerRelation);

    /**
     * 查询某个顾客被哪个账号添加了
     * @param customerCardNumber
     * @return
     */
    Customer queryAccountByCustomer(@Param("customerCardNumber") String customerCardNumber);

    /**
     * 删除关联信息
     * @param customerRelation
     * @return
     */
    int deleteCustomerRelation(CustomerRelation customerRelation);
}
