package com.alone.hotel.service;

import com.alone.hotel.dto.PurchaseOrderExecution;
import com.alone.hotel.entity.PurchaseOrder;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-04-26 21:59
 * @Description:
 */
public interface PurchaseOrderService {
    /**
     * 添加采购订单
     * @param purchaseOrder
     * @return
     */
    PurchaseOrderExecution addPurchaseOrder(PurchaseOrder purchaseOrder);

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
    PurchaseOrderExecution updatePurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 删除采购订单
     * @param orderId
     * @return
     */
    PurchaseOrderExecution deletePurchaseOrder(String orderId);
}
