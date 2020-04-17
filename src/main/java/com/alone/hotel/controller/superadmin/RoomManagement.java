package com.alone.hotel.controller.superadmin;

import com.alibaba.fastjson.JSONObject;
import com.alone.hotel.dto.ImageExecution;
import com.alone.hotel.dto.RoomExecution;
import com.alone.hotel.entity.Room;
import com.alone.hotel.entity.RoomType;
import com.alone.hotel.enums.RoomStateEnum;
import com.alone.hotel.service.RoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.superadmin
 * @Author: Alone
 * @CreateTime: 2020-03-09 21:22
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/superadmin")
public class RoomManagement {
    @Autowired
    private RoomService roomService;

    /**
     * 增加房间(formData提交)
     * @param roomStr json字符串
     * @param fileList 文件流
     * @return 成功或失败,失败返回失败信息
     */
    @PostMapping("/addroom")
    private RoomExecution addRoom(@RequestParam("roomStr")String roomStr,
                                  @RequestParam("fileList")MultipartFile[] fileList){
        //TODO 验证码
        //List<ImageExecution> imageList = new ArrayList<ImageExecution>();
        Room room = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            room = mapper.readValue(roomStr, Room.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(room != null && fileList != null && fileList.length > 0){
            try{
                RoomExecution roomExecution = roomService.addRoom(room, fileList);
                if(roomExecution.getState() == RoomStateEnum.SUCCESS.getState()){
                    return new RoomExecution(RoomStateEnum.SUCCESS);
                } else {
                    return new RoomExecution(RoomStateEnum.INNER_ERROR);
                }
            } catch (Exception e){
                return new RoomExecution(RoomStateEnum.INNER_ERROR);
            }
        } else {
            return new RoomExecution(RoomStateEnum.EMPTY);
        }
    }

    /**
     * 根据房间号获取房间信息
     * @param roomId
     * @return
     */
    @GetMapping("/getroombyid")
    private RoomExecution getRoomById(@RequestParam("roomId")int roomId){
        RoomExecution roomExecution = null;
        if(roomId < -1){
            try{
                Room room = roomService.getRoomById(roomId);
                roomExecution = new RoomExecution(RoomStateEnum.SUCCESS, room);
            } catch (Exception e){
                roomExecution = new RoomExecution(RoomStateEnum.INNER_ERROR);
            }
        } else {
            //房间号错误
            roomExecution = new RoomExecution(RoomStateEnum.ROOM_ID_ERROR);
        }
        return roomExecution;
    }

    /**
     * 获取房间列表(json传参)
     * 这个方法可能会出问题
     * @param jsonParam
     * @return
     */
    @CrossOrigin
    @GetMapping("/getroomlist")
    private RoomExecution getRoomList(@RequestBody JSONObject jsonParam){
        int pageIndex = jsonParam.getInteger("pageIndex");
        int pageSize = jsonParam.getInteger("pageSize");
        int roomTypeId = jsonParam.getInteger("roomType");
        int roomState = jsonParam.getInteger("roomState");
        if(pageIndex > -1 && pageSize > 0){
            Room roomCondition = new Room();
            if(roomTypeId > -1){
                RoomType roomType = new RoomType();
                roomType.setTypeId(roomTypeId);
                roomCondition.setRoomType(roomType);
            }
            if(roomState > -1){
                roomCondition.setRoomState(roomState);
            }
            RoomExecution re = roomService.getRoomList(roomCondition, pageIndex, pageSize);
            return re;
        } else {
            return new RoomExecution(RoomStateEnum.PAGE_ERROR);
        }
    }

    /**
     * 修改房间
     * @param roomStr
     * @param fileList
     * @return
     */
    @PostMapping("/updateroom")
    private RoomExecution updateRoom(@RequestParam("roomStr")String roomStr, @RequestParam("fileList")MultipartFile[] fileList){
        Room room = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            room = mapper.readValue(roomStr, Room.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(room != null){
            try{
                RoomExecution roomExecution = roomService.updateRoom(room, fileList);
                //TODO 添加清扫订单
                if(roomExecution.getState() == RoomStateEnum.SUCCESS.getState()){
                    return new RoomExecution(RoomStateEnum.SUCCESS);
                } else {
                    return new RoomExecution(RoomStateEnum.INNER_ERROR);
                }
            } catch (Exception e){
                return new RoomExecution(RoomStateEnum.INNER_ERROR);
            }
        } else {
            return new RoomExecution(RoomStateEnum.EMPTY);
        }
    }

    @PostMapping("/deleteroom")
    private RoomExecution deleteRoom(@RequestParam("roomId")int roomId){
        if(roomId > 0){
            RoomExecution roomExecution = roomService.deleteRoom(roomId);
            return roomExecution;
        } else {
            return new RoomExecution(RoomStateEnum.ROOM_ID_ERROR);
        }
    }
}
