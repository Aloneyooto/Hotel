package com.alone.hotel.service.impl;

import com.alone.hotel.dao.RoomDao;
import com.alone.hotel.dao.RoomImgDao;
import com.alone.hotel.dto.ImageExecution;
import com.alone.hotel.dto.RoomExecution;
import com.alone.hotel.entity.Room;
import com.alone.hotel.entity.RoomImg;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.enums.RoomStateEnum;
import com.alone.hotel.exceptions.RoomException;
import com.alone.hotel.service.RoomService;
import com.alone.hotel.utils.ImageUtil;
import com.alone.hotel.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-09 19:31
 * @Description:
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private RoomImgDao roomImgDao;

    /**
     * 添加房间
     * @param room
     * @param files
     * @return
     */
    @Override
    @Transactional
    public RoomExecution addRoom(Room room, MultipartFile[] files) {
        //向tb_room写入房间信息,获取roomId
        //结合roomId批量处理房间详情图
        //将房间详情图列表批量插入tb_room_img中

        //空值判断
        if(room != null){
            //设置房间默认状态为空房间
            room.setRoomState(0);
            try{
                //插入房间信息
                int effectNum = roomDao.insertRoom(room);
                if(effectNum <= 0){
                    throw new RoomException(ResultEnum.ROOM_INSERT_ERROR);
                }
            } catch (Exception e){
                throw new RoomException(ResultEnum.ROOM_INSERT_ERROR);
            }
            //若房间详情图不为空则添加
            if(files != null && files.length > 0){
                addRoomImgList(room, files);
                return new RoomExecution(RoomStateEnum.SUCCESS, room);
            } else {
                return new RoomExecution(RoomStateEnum.EMPTY);
            }
        } else {
            return new RoomExecution(RoomStateEnum.EMPTY);
        }
    }

    private void addRoomImgList(Room room, MultipartFile[] files){
        List<RoomImg> roomImgList = new ArrayList<RoomImg>();
        //获取图片存储路径
        String dest = PathUtil.getRoomImagePath(room.getRoomId());
        //遍历图片
        for (MultipartFile file : files) {
            try {
                String imgAddr = ImageUtil.uploadImage(file, dest);
                RoomImg roomImg = new RoomImg();
                roomImg.setRoomImgAddr(imgAddr);
                roomImg.setRoomId(room.getRoomId());
                roomImgList.add(roomImg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(roomImgList.size() > 0){
            try {
                int effectedNum = roomImgDao.batchInsertRoomImg(roomImgList);
                room.setRoomImgList(roomImgList);
                if(effectedNum <= 0){
                    throw new RoomException(ResultEnum.ROOM_INSERT_ERROR);
                }
            } catch (Exception e){
                throw new RoomException(ResultEnum.ROOM_INSERT_ERROR);
            }
        }
    }
}
