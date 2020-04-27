package com.alone.hotel.service.impl;

import com.alone.hotel.dao.InventoryDao;
import com.alone.hotel.dto.InventoryExecution;
import com.alone.hotel.entity.Inventory;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.exceptions.ResultException;
import com.alone.hotel.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-04-26 16:39
 * @Description:
 */
@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryDao inventoryDao;

    @Override
    @Transactional
    public InventoryExecution addInventory(Inventory inventory) {
        if(inventory != null){
            int effectNum = inventoryDao.addInventory(inventory);
            if(effectNum <= 0){
                throw new ResultException(ResultEnum.INNER_ERROR);
            }
            return new InventoryExecution(ResultEnum.SUCCESS);
        } else {
            return new InventoryExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    public Inventory queryInventoryByGoodsId(Integer goodsId) {
        return inventoryDao.queryInventoryByGoodsId(goodsId);
    }

    @Override
    public List<Inventory> queryInventoryList(String goodsName) {
        return inventoryDao.queryInventoryList(goodsName);
    }

    @Override
    @Transactional
    public InventoryExecution updateInventory(Inventory inventory) {
        if(inventory != null && inventory.getGoodsId() != null){
            int effectedNum = inventoryDao.updateInventory(inventory);
            if(effectedNum <= 0){
                throw new ResultException(ResultEnum.INNER_ERROR);
            }
            return new InventoryExecution(ResultEnum.SUCCESS, inventory);
        } else {
            return new InventoryExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public InventoryExecution deleteInventory(Integer goodsId) {
        if(goodsId > 0){
            int effectedNum = inventoryDao.deleteInventory(goodsId);
            if(effectedNum <= 0){
                throw new ResultException(ResultEnum.INNER_ERROR);
            }
            return new InventoryExecution(ResultEnum.SUCCESS);
        } else {
            return new InventoryExecution(ResultEnum.ID_ERROR);
        }
    }
}
