package com.alone.hotel.controller.superadmin;

import com.alone.hotel.dto.ImageExecution;
import com.alone.hotel.dto.RoomExecution;
import com.alone.hotel.entity.Room;
import com.alone.hotel.enums.RoomStateEnum;
import com.alone.hotel.service.RoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
@RequestMapping("/superadmin")
public class RoomManagement {
    @Autowired
    private RoomService roomService;

    /**
     * 增加房间(formData提交)
     * @param roomStr json字符串
     * @param filelist 文件流
     * @return 成功或失败,失败返回失败信息
     */
    @PostMapping("/addroom")
    private String addRoom(@RequestParam("roomStr")String roomStr, @RequestParam("filelist")MultipartFile[] filelist){
        //TODO 验证码
        //List<ImageExecution> imageList = new ArrayList<ImageExecution>();
        Room room = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            room = mapper.readValue(roomStr, Room.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(room != null && filelist != null && filelist.length > 0){
            try{
                RoomExecution roomExecution = roomService.addRoom(room, filelist);
                if(roomExecution.getState() == RoomStateEnum.SUCCESS.getState()){
                    return "success";
                } else {
                    return "error" + ":插入失败";
                }
            } catch (Exception e){
                return "error" + e.getMessage();
            }
        } else {
            return "error" + "插入信息不能为空";
        }
    }
}
