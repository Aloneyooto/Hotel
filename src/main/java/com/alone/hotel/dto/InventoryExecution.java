package com.alone.hotel.dto;

import com.alone.hotel.entity.Inventory;
import com.alone.hotel.enums.ResultEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-04-26 16:32
 * @Description:
 */
@Data
public class InventoryExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //商品数量
    private int count;
    //操作的清洁员
    private Inventory inventory;
    //操作的清洁员列表
    private List<Inventory> inventoryList;

    public InventoryExecution() {
    }

    public InventoryExecution(ResultEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public InventoryExecution(ResultEnum stateEnum, Inventory inventory){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.inventory = inventory;
    }

    public InventoryExecution(ResultEnum stateEnum, List<Inventory> inventoryList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.inventoryList = inventoryList;
    }
}
