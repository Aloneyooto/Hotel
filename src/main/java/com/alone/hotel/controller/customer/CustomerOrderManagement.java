package com.alone.hotel.controller.customer;

import com.alone.hotel.dto.OrderExecution;
import com.alone.hotel.dto.RoomExecution;
import com.alone.hotel.entity.*;
import com.alone.hotel.enums.OrderStateEnum;
import com.alone.hotel.enums.RoomStateEnum;
import com.alone.hotel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

    /**
     * 增加房间订单
     * @param roomOrder
     * @return
     */
    @PostMapping("/addroomorder")
    private OrderExecution addRoomOrder(RoomOrder roomOrder){
        //空值判断
        if(roomOrder != null && roomOrder.getAccount() != null && roomOrder.getRoomType() != null){
            //查询房间数量是否满足订单需求
            Room roomCondition = new Room();
            roomCondition.setRoomType(roomOrder.getRoomType());
            //0代表空房间
            roomCondition.setRoomState(0);
            RoomExecution roomExecution = roomService.getRoomList(roomCondition, 1, roomOrder.getRoomAmount());
            if(roomExecution.getCount() < roomOrder.getRoomAmount()){
                //剩余的房间数少于需要的房间数
                return new OrderExecution(OrderStateEnum.ROOM_LACKING);
            }
            //选出房间号
            List<Room> roomList = roomExecution.getRoomList();
            List<Customer> customerList = roomOrder.getCustomerList();
            //生成订单ID
            String roomOrderId = generateRoomOrderId(new Date());
            roomOrder.setOrderId(roomOrderId);
            //向订单-入住人表写入信息
            if(roomOrder.getCustomerList() != null){
                for (Customer customer : customerList) {
                    RoomOrderRelation roomOrderRelation = new RoomOrderRelation();
                    roomOrderRelation.setRoomOrder(roomOrder);
                    roomOrderRelation.setCustomer(customer);
                    boolean effected = roomOrderRelationService.addRoomOrderRelation(roomOrderRelation);
                    if(effected == false){
                        return new OrderExecution(OrderStateEnum.RELATION_INSERT_ERROR);
                    }
                }
            } else {
                return new OrderExecution(OrderStateEnum.CUSTOMER_EMPTY);
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
                        return new OrderExecution(OrderStateEnum.CHECK_IN_INSERT_ERROR);
                    }
                }
            }
            //改变房间状态
            for (Room room : roomList) {
                RoomExecution changeResult = roomService.updateRoom(room, null);
                if(changeResult.getState() != RoomStateEnum.SUCCESS.getState()){
                    return new OrderExecution(OrderStateEnum.ROOM_UPDATE_ERROR);
                }
            }
            OrderExecution orderExecution = roomOrderService.addRoomOrder(roomOrder);
            if(orderExecution.getState() == OrderStateEnum.SUCCESS.getState()){
                return new OrderExecution(OrderStateEnum.SUCCESS, roomOrder, roomList);
            } else {
                return new OrderExecution(OrderStateEnum.ROOM_ORDER_INSERT_ERROR);
            }
        } else {
            return new OrderExecution(OrderStateEnum.ROOM_ORDER_EMPTY);
        }
    }

    @GetMapping("/queryroomorderbyaccountname")
    private OrderExecution queryRoomOrderByAccountName(CustomerAccount customerAccount){
        if(customerAccount != null){
            try {
                List<RoomOrder> roomOrderList = roomOrderService.queryRoomOrderByAccountName(customerAccount.getAccountName());
                return new OrderExecution(OrderStateEnum.SUCCESS, roomOrderList);
            } catch (Exception e){
                return new OrderExecution(OrderStateEnum.INNER_ERROR);
            }
        } else {
            return new OrderExecution(OrderStateEnum.ACCOUNT_EMPTY);
        }
    }

    @PostMapping("/updateroomorder")
    private OrderExecution updateRoomOrder(RoomOrder roomOrder){
        if(roomOrder != null && roomOrder.getOrderId() != null){
            try {
                OrderExecution orderExecution = roomOrderService.updateRoomOrder(roomOrder);
                return orderExecution;
            } catch (Exception e){
                return new OrderExecution(OrderStateEnum.INNER_ERROR);
            }
        } else {
            return new OrderExecution(OrderStateEnum.ROOM_ORDER_EMPTY);
        }
    }

    @PostMapping("/deleteroomorder")
    private OrderExecution deleteRoomOrder(RoomOrder roomOrder){
        if(roomOrder != null && roomOrder.getOrderId() != null && roomOrder.getCustomerList() != null){
            try{
                List<Customer> customerList = roomOrder.getCustomerList();
                //改变房间状态
                for (Customer customer : customerList) {
                    CheckIn checkIn = checkInService.queryCheckInByCustomer(customer.getCustomerCardNumber());
                    if(checkIn.getRoom().getRoomState() != 2){
                        //不是已退房状态就改变房间状态
                        RoomExecution roomExecution = roomService.updateRoom(checkIn.getRoom(), null);
                        if(roomExecution.getState() != RoomStateEnum.SUCCESS.getState()){
                            return new OrderExecution(OrderStateEnum.ROOM_UPDATE_ERROR);
                        }
                    }
                    //删除入住关联表记录
                    boolean result = checkInService.deleteCheckInMessage(checkIn);
                    if(result != true){
                        return new OrderExecution(OrderStateEnum.RELATION_DELETE_ERROR);
                    }
                }
                //删除订单关联表记录
                boolean result = roomOrderRelationService.deleteRoomOrderRelation(roomOrder.getOrderId());
                if(result == false){
                    return new OrderExecution(OrderStateEnum.RELATION_DELETE_ERROR);
                }
                //删除订单记录
                OrderExecution orderExecution = roomOrderService.deleteRoomOrder(roomOrder.getOrderId());
                if(orderExecution.getState() == OrderStateEnum.SUCCESS.getState()){
                    return orderExecution;
                } else {
                    return new OrderExecution(OrderStateEnum.ROOM_ORDER_DELETE_ERROR);
                }
            } catch (Exception e){
                return new OrderExecution(OrderStateEnum.ROOM_ORDER_DELETE_ERROR);
            }
        } else {
            return new OrderExecution(OrderStateEnum.ROOM_ORDER_EMPTY);
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
