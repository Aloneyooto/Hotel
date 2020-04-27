package com.alone.hotel.dao;

import com.alone.hotel.entity.PurchaseOrder;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-04-26 19:17
 * @Description: 采购订单
 */
public interface PurchaseOrderDao {
    /**
     * 添加采购订单
     * @param purchaseOrder
     * @return
     */
    int addPurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 获取采购订单
     * @param purchaseOrder
     * @return
     */
    List<PurchaseOrder> queryPurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 更新采购订单
     * @param purchaseOrder
     * @return
     */
    int updatePurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 删除采购订单
     * @param orderId
     * @return
     */
    int deletePurchaseOrder(String orderId);
}
