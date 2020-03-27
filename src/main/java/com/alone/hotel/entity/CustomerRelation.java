package com.alone.hotel.entity;

import lombok.Data;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-09 09:03
 * @Description:
 */
@Data
public class CustomerRelation {
    private Customer customer;
    private CustomerAccount account;
}
