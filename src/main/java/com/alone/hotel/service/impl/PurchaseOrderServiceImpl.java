package com.alone.hotel.service.impl;

import com.alone.hotel.dao.InventoryDao;
import com.alone.hotel.dao.PurchaseOrderDao;
import com.alone.hotel.dto.PurchaseOrderExecution;
import com.alone.hotel.entity.Inventory;
import com.alone.hotel.entity.PurchaseOrder;
import com.alone.hotel.enums.OrderStateEnum;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.exceptions.ResultException;
import com.alone.hotel.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-04-26 22:02
 * @Description:
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    @Autowired
    private PurchaseOrderDao purchaseOrderDao;
    @Autowired
    private InventoryDao inventoryDao;

    @Override
    @Transactional
    public PurchaseOrderExecution addPurchaseOrder(PurchaseOrder purchaseOrder) {
        if(purchaseOrder != null && purchaseOrder.getRoom() != null && purchaseOrder.getEmployee() != null && purchaseOrder.getGoods() != null){
            try {
                String orderId = generateOrderId(purchaseOrder.getRoom().getRoomId(), purchaseOrder.getGoods().getGoodsId());
                purchaseOrder.setOrderId(orderId);
                //设置订单初始状态为未完成
                purchaseOrder.setStatus(OrderStateEnum.UNFINISHED.getState());
                purchaseOrder.setHandInTime(new Date());
                purchaseOrder.setLastEditTime(new Date());
                int effectedNum = purchaseOrderDao.addPurchaseOrder(purchaseOrder);
                if(effectedNum <= 0){
                    throw new ResultException(ResultEnum.INNER_ERROR);
                }
                return new PurchaseOrderExecution(ResultEnum.SUCCESS);
            } catch (Exception e){
                return new PurchaseOrderExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            return new PurchaseOrderExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    public List<PurchaseOrder> queryPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderDao.queryPurchaseOrder(purchaseOrder);
    }

    @Override
    @Transactional
    public PurchaseOrderExecution updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        if(purchaseOrder != null && purchaseOrder.getRoom() != null && purchaseOrder.getEmployee() != null && purchaseOrder.getGoods() != null){
            try {
                purchaseOrder.setLastEditTime(new Date());
                //订单状态为完成
                if(purchaseOrder.getStatus().equals(OrderStateEnum.FINISHED.getState())){
                    //查询原库存
                    Inventory inventory = inventoryDao.queryInventoryByGoodsId(purchaseOrder.getGoods().getGoodsId());
                    int amount = inventory.getGoodsAmount() + purchaseOrder.getGoodsAmount();
                    inventory.setGoodsAmount(amount);
                    //修改库存表
                    int effectedNum = inventoryDao.updateInventory(inventory);
                    if(effectedNum <= 0){
                        throw new ResultException(ResultEnum.INVENTORY_UPDATE_ERROR);
                    }
                }
                int effectedNum = purchaseOrderDao.updatePurchaseOrder(purchaseOrder);
                if(effectedNum <= 0){
                    throw new ResultException(ResultEnum.INNER_ERROR);
                }
                return new PurchaseOrderExecution(ResultEnum.SUCCESS, purchaseOrder);
            } catch (Exception e){
                System.out.println(e.getMessage());
                return new PurchaseOrderExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            return new PurchaseOrderExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public PurchaseOrderExecution deletePurchaseOrder(String orderId) {
        if(orderId != null){
            int effectedNum = purchaseOrderDao.deletePurchaseOrder(orderId);
            if(effectedNum <= 0){
                throw new ResultException(ResultEnum.INNER_ERROR);
            }
            return new PurchaseOrderExecution(ResultEnum.SUCCESS);
        } else {
            return new PurchaseOrderExecution(ResultEnum.EMPTY);
        }
    }

    /**
     * 生成订单号
     * 格式:日期+房间号+物品号
     * @param roomId
     * @param goodId
     * @return
     */
    private String generateOrderId(int roomId, int goodId){
        String orderId = "";
        String dateStr = simpleDateFormat.format(new Date());
        orderId = dateStr + roomId + goodId;
        return orderId;
    }
}
