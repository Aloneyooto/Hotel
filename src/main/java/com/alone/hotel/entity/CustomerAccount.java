package com.alone.hotel.entity;

import lombok.Data;

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
    private String password;
    private Integer flag;
}
