package com.alone.hotel.dao;

import com.alone.hotel.entity.Inventory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-04-26 15:14
 * @Description:
 */
@SpringBootTest
class InventoryDaoTest {
    @Autowired
    private InventoryDao inventoryDao;

    @Test
    public void testAddInventory(){
        Inventory inventory = new Inventory();
        inventory.setGoodsName("床");
        inventory.setGoodsAmount(100);
        inventory.setGoodsPrice(2000d);
        int effectedNum = inventoryDao.addInventory(inventory);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryInventoryByGoodsId(){
        Inventory inventory = inventoryDao.queryInventoryByGoodsId(1);
        assertEquals("床", inventory.getGoodsName());
    }

    @Test
    public void testQueryInventoryList(){
        List<Inventory> inventoryList = inventoryDao.queryInventoryList("床");
        assertEquals(1, inventoryList.size());
    }

    @Test
    public void testUpdateInventory(){
        Inventory inventory = new Inventory();
        inventory.setGoodsId(1);
        inventory.setGoodsName("棉被");
        int effectedNum = inventoryDao.updateInventory(inventory);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testDeleteInventory(){
        int effectedNum = inventoryDao.deleteInventory(1);
        assertEquals(1, effectedNum);
    }
}