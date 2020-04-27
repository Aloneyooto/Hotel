package com.alone.hotel.service;

import com.alone.hotel.dto.ImageExecution;
import com.alone.hotel.dto.RoomExecution;
import com.alone.hotel.entity.Room;
import com.alone.hotel.entity.RoomType;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.utils.ImageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-09 20:39
 * @Description:
 */
@SpringBootTest
class RoomServiceTest {
    @Autowired
    private RoomService roomService;

    @Test
    public void testInsertRoom() throws IOException {
        Room room = new Room();
        room.setRoomId(102);
        room.setRoomDesc("test");
        RoomType roomType = new RoomType();
        roomType.setTypeId(1);
        room.setRoomType(roomType);
        room.setRoomFloor(1);
        File file = new File("E:\\proresources\\images\\latestbg.jpg");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
        ImageExecution image = new ImageExecution(multipartFile, file.getName());
        List<ImageExecution> list = new ArrayList<ImageExecution>();
        list.add(image);
//        RoomExecution roomExecution = roomService.addRoom(room, list);
//        assertEquals(ResultEnum.SUCCESS.getState(), roomExecution.getState());
    }

    @Test
    public void testUpdateRoom() throws IOException {
        Room room = new Room();
        room.setRoomId(102);
        room.setRoomDesc("测试");
        File file = new File("E:\\proresources\\images\\jide.jpg");
        File file1 = new File("E:\\proresources\\images\\kidsama.jpg");
        InputStream inputStream = new FileInputStream(file);
        InputStream inputStream1 = new FileInputStream(file1);
        MultipartFile[] multipartFiles = new MultipartFile[2];
        multipartFiles[0] = new MockMultipartFile(file.getName(), inputStream);
        multipartFiles[1] = new MockMultipartFile(file1.getName(), inputStream1);
        RoomExecution roomExecution = roomService.updateRoom(room, multipartFiles);
        assertEquals(ResultEnum.SUCCESS.getState(), roomExecution.getState());
    }
}