package com.alone.hotel.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-09 09:01
 * @Description: 顾客账号
 */
@Data
public class CustomerAccount {
    private String accountName;

    private String accountPassword;

    private Integer flag;

    //押金
    private Double deposit;

    //头像
    private String headImg;

    //账号主人信息
    private Customer customer;

    private List<Customer> customerList;
    //private List<RoomOrder> roomOrderList;
}
