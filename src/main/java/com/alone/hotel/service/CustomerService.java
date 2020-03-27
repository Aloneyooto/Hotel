package com.alone.hotel.service;

import com.alone.hotel.dto.CustomerExecution;
import com.alone.hotel.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-23 20:10
 * @Description:
 */
public interface CustomerService {
    /**
     * 添加顾客
     * @param customer
     * @return
     */
    CustomerExecution addCustomer(Customer customer, MultipartFile cardImg, MultipartFile faceImg);

    /**
     * 查询顾客个人信息
     * @param customerCardNumber
     * @return
     */
    Customer queryCustomerById(String customerCardNumber);

    /**
     * 更新顾客信息
     * @param customer
     * @param cardImg
     * @param faceImg
     * @return
     */
    CustomerExecution updateCustomer(Customer customer, MultipartFile cardImg, MultipartFile faceImg);

    /**
     * 删除顾客信息
     * @param customerCardNumber
     * @return
     */
    CustomerExecution deleteCustomer(String customerCardNumber);
}
