package com.alone.hotel.dao;

import com.alone.hotel.entity.RoomImg;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-09 14:59
 * @Description:
 */
@SpringBootTest
class RoomImgDaoTest {
    @Autowired
    private RoomImgDao roomImgDao;

    @Test
    public void testBatchInserRoomImg(){
        RoomImg roomImg1 = new RoomImg();
        roomImg1.setRoomImgAddr("test1");
        roomImg1.setPriority(100);
        roomImg1.setRoomId(101);
        RoomImg roomImg2 = new RoomImg();
        roomImg2.setRoomImgAddr("test2");
        roomImg2.setPriority(105);
        roomImg2.setRoomId(101);
        List<RoomImg> roomImgList = new ArrayList<RoomImg>();
        roomImgList.add(roomImg1);
        roomImgList.add(roomImg2);
        int effectNum = roomImgDao.batchInsertRoomImg(roomImgList);
        assertEquals(2, effectNum);
    }


}