package com.alone.hotel.controller.warehousemanager;

import com.alibaba.fastjson.JSONObject;
import com.alone.hotel.dto.PurchaseOrderExecution;
import com.alone.hotel.entity.PurchaseOrder;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.warehousemanager
 * @Author: Alone
 * @CreateTime: 2020-04-27 00:35
 * @Description:
 */
@RestController
@RequestMapping("/warehousemanager")
public class PurchaseOrderManagement {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    /**
     * 添加采购订单
     * @param purchaseOrder
     * @return
     */
    @PostMapping("/addpurchaseorder")
    private PurchaseOrderExecution addPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder){
        if(purchaseOrder != null){
            PurchaseOrderExecution purchaseOrderExecution = purchaseOrderService.addPurchaseOrder(purchaseOrder);
            return purchaseOrderExecution;
        } else {
            return new PurchaseOrderExecution(ResultEnum.EMPTY);
        }
    }


    /**
     * 根据查询条件查询库存
     * @param purchaseOrder
     * @return
     */
    @GetMapping("/querypurchaseorder")
    private PurchaseOrderExecution queryPurchaseOrder(PurchaseOrder purchaseOrder){
        try{
            List<PurchaseOrder> purchaseOrderList = purchaseOrderService.queryPurchaseOrder(purchaseOrder);
            return new PurchaseOrderExecution(ResultEnum.SUCCESS, purchaseOrderList);
        } catch (Exception e){
            return new PurchaseOrderExecution(ResultEnum.INNER_ERROR);
        }
    }

    /**
     * 修改订单
     * @param purchaseOrder
     * @return
     */
    @PostMapping("/updatepurchaseorder")
    private PurchaseOrderExecution updatePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder){
        if(purchaseOrder != null && purchaseOrder.getOrderId() != null){
            PurchaseOrderExecution purchaseOrderExecution = purchaseOrderService.updatePurchaseOrder(purchaseOrder);
            return purchaseOrderExecution;
        } else {
            return new PurchaseOrderExecution(ResultEnum.EMPTY);
        }
    }

    /**
     * 删除订单
     * @param jsonObject
     * @return
     */
    @PostMapping("/deletepurchaseorder")
    private PurchaseOrderExecution deletePurchaseOrder(@RequestBody JSONObject jsonObject){
        try{
            String orderId = jsonObject.getString("orderId");
            if(orderId != null){
                PurchaseOrderExecution purchaseOrderExecution = purchaseOrderService.deletePurchaseOrder(orderId);
                return purchaseOrderExecution;
            } else {
                return new PurchaseOrderExecution(ResultEnum.EMPTY);
            }
        } catch (Exception e){
            return new PurchaseOrderExecution(ResultEnum.EMPTY);
        }
    }
}
