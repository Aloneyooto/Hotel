package com.alone.hotel.dto;

import com.alone.hotel.entity.PurchaseOrder;
import com.alone.hotel.enums.ResultEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-04-26 21:53
 * @Description:
 */
@Data
public class PurchaseOrderExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //商品数量
    private int count;
    //操作的清洁订单
    private PurchaseOrder purchaseOrder;
    //操作的清洁订单列表
    private List<PurchaseOrder> purchaseOrderList;

    public PurchaseOrderExecution() {
    }

    public PurchaseOrderExecution(ResultEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public PurchaseOrderExecution(ResultEnum stateEnum, PurchaseOrder PurchaseOrder){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.purchaseOrder = PurchaseOrder;
    }

    public PurchaseOrderExecution(ResultEnum stateEnum, List<PurchaseOrder> PurchaseOrderList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.purchaseOrderList = PurchaseOrderList;
    }
}
