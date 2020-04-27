package com.alone.hotel.controller.warehousemanager;

import com.alibaba.fastjson.JSONObject;
import com.alone.hotel.dto.InventoryExecution;
import com.alone.hotel.entity.Inventory;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.exceptions.ResultException;
import com.alone.hotel.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.warehousemanager
 * @Author: Alone
 * @CreateTime: 2020-04-26 16:59
 * @Description: 库存管理
 */
@RestController
@RequestMapping("/warehousemanager")
public class InventoryManagement {
    @Autowired
    private InventoryService inventoryService;

    /**
     * 添加库存信息
     * @param inventory
     * @return
     */
    @PostMapping("/addinventory")
    private InventoryExecution addInventory(@RequestBody Inventory inventory){
        try{
            InventoryExecution inventoryExecution = inventoryService.addInventory(inventory);
            return inventoryExecution;
        } catch (Exception e){
            throw new ResultException(ResultEnum.INNER_ERROR);
        }
    }

    /**
     * 根据id查询库存信息
     * @param goodsId
     * @return
     */
    @GetMapping("/queryinventorybygoodsid")
    private InventoryExecution queryInventoryByGoodsId(@RequestParam("goodsId") Integer goodsId){
        if(goodsId > 0){
            Inventory inventory = inventoryService.queryInventoryByGoodsId(goodsId);
            return new InventoryExecution(ResultEnum.SUCCESS, inventory);
        } else {
            return new InventoryExecution(ResultEnum.ID_ERROR);
        }
    }

    /**
     * 根据物品名称查询库存信息
     * @param goodsName
     * @return
     */
    @GetMapping("/queryinventorylist")
    private InventoryExecution queryInventoryList(@RequestParam("goodsName") String goodsName){
        if(goodsName != null){
            List<Inventory> inventoryList = inventoryService.queryInventoryList(goodsName);
            return new InventoryExecution(ResultEnum.SUCCESS, inventoryList);
        } else {
            return new InventoryExecution(ResultEnum.EMPTY);
        }
    }

    /**
     * 更新库存信息
     * @param inventory
     * @return
     */
    @PostMapping("/updateinventory")
    private InventoryExecution updateInventory(@RequestBody Inventory inventory){
        if(inventory != null && inventory.getGoodsId() != null){
            InventoryExecution inventoryExecution = inventoryService.updateInventory(inventory);
            return inventoryExecution;
        } else {
            return new InventoryExecution(ResultEnum.EMPTY);
        }
    }

    /**
     * 删除库存信息
     * @param jsonObject
     * @return
     */
    @PostMapping("/deleteinventory")
    private InventoryExecution deleteInventory(@RequestBody JSONObject jsonObject){
        try {
            String id = jsonObject.get("goodsId").toString();
            Integer goodsId = Integer.parseInt(id);
            if(goodsId > 0){
                InventoryExecution inventoryExecution = inventoryService.deleteInventory(goodsId);
                return inventoryExecution;
            } else {
                return new InventoryExecution(ResultEnum.ID_ERROR);
            }
        } catch (Exception e){
            return new InventoryExecution(ResultEnum.INNER_ERROR);
        }
    }
}
