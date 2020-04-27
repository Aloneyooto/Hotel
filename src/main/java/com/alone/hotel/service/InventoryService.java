package com.alone.hotel.service;

import com.alone.hotel.dto.InventoryExecution;
import com.alone.hotel.entity.Inventory;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-04-26 16:34
 * @Description:
 */
public interface InventoryService {
    /**
     * 添加库存信息
     * @param inventory
     * @return
     */
    InventoryExecution addInventory(Inventory inventory);

    /**
     * 根据id查询库存
     * @param goodsId
     * @return
     */
    Inventory queryInventoryByGoodsId(Integer goodsId);

    /**根据物品名字查询库存
     *
     * @return
     */
    List<Inventory> queryInventoryList(String goodsName);

    /**
     * 更新库存信息
     * @param inventory
     * @return
     */
    InventoryExecution updateInventory(Inventory inventory);

    /**
     * 删除库存信息
     * @param goodsId
     * @return
     */
    InventoryExecution deleteInventory(Integer goodsId);
}
