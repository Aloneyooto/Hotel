package com.alone.hotel.entity;

import lombok.Data;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-09 08:59
 * @Description: 顾客
 */
@Data
public class Customer {
    private String customerName;
    private Integer customerAge;
    //1 男 0 女
    private Integer customerGender;
    private String customerCardId;
    private String customerCardImg;
    private String customerFaceImg;
    private String customerPhone;
}
