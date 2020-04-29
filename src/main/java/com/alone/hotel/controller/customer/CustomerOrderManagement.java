package com.alone.hotel.controller.customer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alone.hotel.annotation.UserLoginToken;
import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.dto.OrderExecution;
import com.alone.hotel.dto.RoomExecution;
import com.alone.hotel.entity.*;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.enums.RoomStateEnum;
import com.alone.hotel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.customer
 * @Author: Alone
 * @CreateTime: 2020-03-29 15:37
 * @Description:
 */
@RestController
@RequestMapping("/customer")
public class CustomerOrderManagement {
    //订单号日期格式
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    //订单号编号格式
    private static final DecimalFormat orderNumberFormat = new DecimalFormat("000");

    @Autowired
    private RecreateOrderService recreateOrderService;
    @Autowired
    private RoomOrderService roomOrderService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomOrderRelationService roomOrderRelationService;
    @Autowired
    private CheckInService checkInService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 增加房间订单
     * @param request
     * @param roomOrder
     * @return
     */
    @UserLoginToken
    @PostMapping("/addroomorder")
    private OrderExecution addRoomOrder(HttpServletRequest request, @RequestBody RoomOrder roomOrder){
        //获取token
        String token = request.getHeader("token");
        //获取customerAccount对象
        String jsonStr = (String)redisTemplate.opsForValue().get(token);
        CustomerAccount customerAccount = JSONArray.parseObject(jsonStr, CustomerAccount.class);
        roomOrder.setAccount(customerAccount);
        //空值判断
        if(roomOrder != null && roomOrder.getAccount() != null && roomOrder.getRoomType() != null){
            //查询房间数量是否满足订单需求
            Room roomCondition = new Room();
            roomCondition.setRoomType(roomOrder.getRoomType());
            //0代表空房间
            roomCondition.setRoomState(RoomStateEnum.EMPTY.getState());
            RoomExecution roomExecution = roomService.getRoomList(roomCondition, 1, roomOrder.getRoomAmount());
            if(roomExecution.getCount() < roomOrder.getRoomAmount()){
                //剩余的房间数少于需要的房间数
                return new OrderExecution(ResultEnum.ROOM_LACKING);
            }
            //选出房间号
            List<Room> roomList = roomExecution.getRoomList();
            //获取入住人列表
            List<Customer> customerList = roomOrder.getCustomerList();
            //生成订单ID
            String roomOrderId = generateRoomOrderId(new Date());
            roomOrder.setOrderId(roomOrderId);
            //向订单-房间表写入信息
            List<RoomOrderRelation> roomOrderRelationList = new ArrayList<RoomOrderRelation>();
            for (Room room : roomList) {
                RoomOrderRelation roomOrderRelation = new RoomOrderRelation();
                roomOrderRelation.setRoomOrder(roomOrder);
                roomOrderRelation.setRoom(room);
                roomOrderRelationList.add(roomOrderRelation);
            }
            Boolean result = roomOrderRelationService.batchAddRoomOrderRelation(roomOrderRelationList);
            if(result == false){
                return new OrderExecution(ResultEnum.RELATION_INSERT_ERROR);
            }
            //向房间-入住人表写入信息
            int i = 0, j = 0;
            while (i < roomList.size()){
                while (j < customerList.size()){
                    CheckIn checkIn = new CheckIn();
                    checkIn.setRoom(roomList.get(i));
                    checkIn.setCustomer(customerList.get(j));
                    boolean effected = checkInService.addCheckInMessage(checkIn);
                    if(effected == false){
                        return new OrderExecution(ResultEnum.CHECK_IN_INSERT_ERROR);
                    }
                }
            }
            //改变房间状态
            for (Room room : roomList) {
                RoomExecution changeResult = roomService.updateRoom(room, null);
                if(changeResult.getState() != ResultEnum.SUCCESS.getState()){
                    return new OrderExecution(ResultEnum.ROOM_UPDATE_ERROR);
                }
            }
            //插入房间订单
            OrderExecution orderExecution = roomOrderService.addRoomOrder(roomOrder);
            if(orderExecution.getState() == ResultEnum.SUCCESS.getState()){
                return new OrderExecution(ResultEnum.SUCCESS, roomOrder, roomList);
            } else {
                return new OrderExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            return new OrderExecution(ResultEnum.EMPTY);
        }
    }

//    /**
//     * 获取账户所定的房间订单
//     * @param request
//     * @return
//     */
//    @UserLoginToken
//    @GetMapping("/queryroomorderbyaccountname")
//    private OrderExecution queryRoomOrderByAccountName(HttpServletRequest request){
//        //获取token
//        String token = request.getHeader("token");
//        //获取customerAccount对象
//        String jsonStr = (String)redisTemplate.opsForValue().get(token);
//        CustomerAccount customerAccount = JSONArray.parseObject(jsonStr, CustomerAccount.class);
//        if(customerAccount != null){
//            try {
//                List<RoomOrder> roomOrderList = roomOrderService.queryRoomOrderByAccountName(customerAccount.getAccountName());
//                return new OrderExecution(ResultEnum.SUCCESS, roomOrderList);
//            } catch (Exception e){
//                return new OrderExecution(ResultEnum.INNER_ERROR);
//            }
//        } else {
//            return new OrderExecution(ResultEnum.ACCOUNT_EMPTY);
//        }
//    }

    /**
     * 更新房间订单
     * @param roomOrder
     * @return
     */
    @UserLoginToken
    @PostMapping("/updateroomorder")
    private OrderExecution updateRoomOrder(@RequestBody RoomOrder roomOrder){
        if(roomOrder != null && roomOrder.getOrderId() != null){
            try {
                OrderExecution orderExecution = roomOrderService.updateRoomOrder(roomOrder);
                return orderExecution;
            } catch (Exception e){
                return new OrderExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            return new OrderExecution(ResultEnum.EMPTY);
        }
    }

    /**
     * 删除房间订单
     * @param roomOrder
     * @return
     */
    @UserLoginToken
    @PostMapping("/deleteroomorder")
    private OrderExecution deleteRoomOrder(@RequestBody RoomOrder roomOrder){
        if(roomOrder != null && roomOrder.getOrderId() != null && roomOrder.getCustomerList() != null){
            try{
                List<Customer> customerList = roomOrder.getCustomerList();
                //改变房间状态
                for (Customer customer : customerList) {
                    CheckIn checkIn = checkInService.queryCheckInByCustomer(customer.getCustomerCardNumber());
                    if(checkIn.getRoom().getRoomState() != RoomStateEnum.CHECK_OUT.getState()){
                        //不是已退房状态就改变房间状态
                        RoomExecution roomExecution = roomService.updateRoom(checkIn.getRoom(), null);
                        if(roomExecution.getState() != ResultEnum.SUCCESS.getState()){
                            return new OrderExecution(ResultEnum.ROOM_UPDATE_ERROR);
                        }
                    }
                    //删除入住关联表记录
                    boolean result = checkInService.deleteCheckInMessage(checkIn);
                    if(result != true){
                        return new OrderExecution(ResultEnum.RELATION_DELETE_ERROR);
                    }
                }
                //删除订单关联表记录
                boolean result = roomOrderRelationService.deleteRoomOrderRelation(roomOrder.getOrderId());
                if(result == false){
                    return new OrderExecution(ResultEnum.RELATION_DELETE_ERROR);
                }
                //删除订单记录
                OrderExecution orderExecution = roomOrderService.deleteRoomOrder(roomOrder.getOrderId());
                if(orderExecution.getState() == ResultEnum.SUCCESS.getState()){
                    return orderExecution;
                } else {
                    return new OrderExecution(ResultEnum.INNER_ERROR);
                }
            } catch (Exception e){
                return new OrderExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            return new OrderExecution(ResultEnum.EMPTY);
        }
    }

    @UserLoginToken
    @PostMapping("/addrecreateorder")
    private OrderExecution addRecreateOrder(@RequestBody RecreateOrder recreateOrder){
        if(recreateOrder != null && recreateOrder.getCustomer() != null && recreateOrder.getCustomer().getCustomerCardNumber() != null){
            return recreateOrderService.addRecreateOrder(recreateOrder);
        } else {
            return new OrderExecution(ResultEnum.EMPTY);
        }
    }

    @UserLoginToken
    @GetMapping("/queryrecreateorderbycustomer")
    private OrderExecution queryRecreateOrderByCustomer(int recreationId, int orderStatus, String customerCardNumber){
        if(customerCardNumber != null){
            Customer customer = recreateOrderService.queryRecreateOrderByCustomer(recreationId, orderStatus, customerCardNumber);
            return new OrderExecution(ResultEnum.SUCCESS, null, customer.getRecreateOrderList());
        } else {
            return new OrderExecution(ResultEnum.EMPTY);
        }
    }

    /**
     * 查询账户内所有人的娱乐订单
     * @return
     */
    @UserLoginToken
    @GetMapping("/queryrecreationlistbyaccount")
    private CustomerAccountExecution queryRecreationListByAccount(HttpServletRequest request){
        try{
            //获取token
            String token = request.getHeader("token");
            CustomerAccount customerAccount = null;
            if(token != null){
                //获取customerAccount对象
                String jsonStr = (String)redisTemplate.opsForValue().get(token);
                customerAccount = JSONArray.parseObject(jsonStr, CustomerAccount.class);
            } else {
                return new CustomerAccountExecution(ResultEnum.TOKEN_EMPTY);
            }
            CustomerAccount result = recreateOrderService.queryRecreationListByAccount(customerAccount.getAccountName());
            return new CustomerAccountExecution(ResultEnum.SUCCESS, result);
        } catch (Exception e){
            return new CustomerAccountExecution(ResultEnum.INNER_ERROR);
        }
    }

    @UserLoginToken
    @PostMapping("/updaterecreateorder")
    private OrderExecution updateRecreateOrder(@RequestBody RecreateOrder recreateOrder){
        if(recreateOrder != null && recreateOrder.getOrderStatus() > -1){
            return recreateOrderService.updateRecreateOrder(recreateOrder);
        } else {
            return new OrderExecution(ResultEnum.EMPTY);
        }
    }

    @UserLoginToken
    @PostMapping("/deleterecreateorder")
    private OrderExecution deleteRecreateOrder(@RequestBody String recreateOrderId){
        if(recreateOrderId != null){
            return recreateOrderService.deleteRecreateOrder(recreateOrderId);
        } else {
            return new OrderExecution(ResultEnum.EMPTY);
        }
    }


    /**
     * 生成房间订单ID
     * 格式:日期+订单号
     * @return
     */
    private String generateRoomOrderId(Date handInTime){
        //日期字符串
        String dateStr = dateFormat.format(new Date());
        int count = roomOrderService.queryOrderCount(handInTime);
        String numberStr = orderNumberFormat.format(count);
        //订单字符串
        String orderStr = dateStr + numberStr;
        return orderStr;
    }
}
