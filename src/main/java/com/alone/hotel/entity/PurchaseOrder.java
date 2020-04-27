package com.alone.hotel.entity;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-04-26 14:51
 * @Description: 采购订单
 */
@Data
public class PurchaseOrder {
    //订单id
    private String orderId;
    //房间id
    private Room room;
    //物品id
    private Inventory goods;
    //经手人
    private Employee employee;
    //物品数量
    private Integer goodsAmount;
    //订单状态
    private Integer status;
    //提交时间
    private Date handInTime;
    //最后一次修改时间
    private Date lastEditTime;
}
