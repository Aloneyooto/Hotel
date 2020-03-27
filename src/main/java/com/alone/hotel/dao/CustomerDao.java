package com.alone.hotel.dao;

import com.alone.hotel.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-23 09:09
 * @Description:
 */
public interface CustomerDao {
    /**
     * 插入顾客
     * @param customer
     * @return
     */
    int addCustomer(Customer customer);

    /**
     * 查询对应工号的员工
     * @param customerId
     * @return
     */
    Customer queryCustomerById(String customerId);

    /**
     * 修改顾客信息
     * @param customer
     * @return
     */
    int updateCustomer(Customer customer);

    /**
     * 删除顾客信息
     * @param customerId
     * @return
     */
    int deleteCustomer(String customerId);
}
