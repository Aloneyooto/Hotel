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
     * 根据身份证号查询顾客信息
     * @param customerCardNumber
     * @return
     */
    Customer queryCustomerById(String customerCardNumber);

    /**
     * 查询所有顾客的面部图片地址
     * @return
     */
    List<Customer> queryCustomerFaceImages();

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
