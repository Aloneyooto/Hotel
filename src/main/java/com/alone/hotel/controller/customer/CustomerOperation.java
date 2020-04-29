package com.alone.hotel.controller.customer;

import com.alibaba.fastjson.JSONObject;
import com.alone.hotel.annotation.UserLoginToken;
import com.alone.hotel.dto.OrderExecution;
import com.alone.hotel.dto.RoomExecution;
import com.alone.hotel.entity.CleanOrder;
import com.alone.hotel.entity.Room;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.enums.RoomStateEnum;
import com.alone.hotel.service.CheckInService;
import com.alone.hotel.service.EmployeeService;
import com.alone.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.customer
 * @Author: Alone
 * @CreateTime: 2020-04-28 13:41
 * @Description:
 */
@RequestMapping("/customer")
@RestController
public class CustomerOperation {
    @Autowired
    private CheckInService checkInService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private EmployeeService employeeService;

    /**
     * 退房
     * 返回值待定
     * @param jsonObject
     * @return
     */
//    @UserLoginToken
//    @PostMapping("/checkout")
//    private RoomExecution checkOut(@RequestBody JSONObject jsonObject){
//        try{
//            Integer roomId = jsonObject.getInteger("roomId");
//            if(roomId > 0){
//                //删除房间绑定信息
//                boolean result = checkInService.deleteCheckInMessageByRoom(roomId);
//                if(result == false){
//                    return new RoomExecution(ResultEnum.RELATION_DELETE_ERROR);
//                }
//                //增加房间打扫订单
//                CleanOrder cleanOrder = new CleanOrder();
//                //查询房间信息
//                Room room = roomService.getRoomById(roomId);
//                cleanOrder.setRoom(room);
//                //查询可分配清洁员列表
//
//                //改变房间状态
//                room.setRoomState(RoomStateEnum.CHECK_OUT.getState());
//                RoomExecution roomExecution = roomService.updateRoom(room, null);
//                return roomExecution;
//            } else {
//                return new RoomExecution(ResultEnum.ROOM_ID_ERROR);
//            }
//        } catch (Exception e){
//            return new RoomExecution(ResultEnum.INNER_ERROR);
//        }
//    }
}
