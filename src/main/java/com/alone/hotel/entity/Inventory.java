package com.alone.hotel.entity;

import lombok.Data;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-04-26 13:19
 * @Description: 库存
 */
@Data
public class Inventory {
    //物品id
    private Integer goodsId;
    //物品名称
    private String goodsName;
    //物品数量
    private Integer goodsAmount;
    //物品单价
    private Double goodsPrice;
}
