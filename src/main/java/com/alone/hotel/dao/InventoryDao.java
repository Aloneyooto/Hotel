package com.alone.hotel.dao;

import com.alone.hotel.entity.Inventory;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-04-26 14:56
 * @Description:
 */
public interface InventoryDao {
    /**
     * 添加库存信息
     * @param inventory
     * @return
     */
    int addInventory(Inventory inventory);

    /**
     * 根据id查询库存
     * @param goodsId
     * @return
     */
    Inventory queryInventoryByGoodsId(Integer goodsId);

    /**
     * 根据物品名字查询库存
     * @return
     */
    List<Inventory> queryInventoryList(String goodsName);

    /**
     * 更新库存信息
     * @param inventory
     * @return
     */
    int updateInventory(Inventory inventory);

    /**
     * 删除库存信息
     * @param goodsId
     * @return
     */
    int deleteInventory(Integer goodsId);
}
