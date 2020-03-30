package com.alone.hotel.dto;

import com.alone.hotel.entity.RecreateOrder;
import com.alone.hotel.entity.Room;
import com.alone.hotel.entity.RoomOrder;
import com.alone.hotel.enums.OrderStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-29 14:36
 * @Description: 所有订单合在一起
 */
@Data
public class OrderExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //操作的房间订单
    private RoomOrder roomOrder;
    //操作的房间订单列表
    private List<RoomOrder> roomOrderList;
    //操作的其他订单
    private RecreateOrder recreateOrder;
    //操作的其他订单列表
    private List<RecreateOrder> recreateOrderList;
    //操作的房间列表
    private List<Room> roomList;

    public OrderExecution() {
    }

    public OrderExecution(OrderStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public OrderExecution(OrderStateEnum stateEnum, RoomOrder roomOrder){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.roomOrder = roomOrder;
    }

    public OrderExecution(OrderStateEnum stateEnum, RoomOrder roomOrder, List<Room> roomList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.roomOrder = roomOrder;
        this.roomList = roomList;
    }

    public OrderExecution(OrderStateEnum stateEnum, List<RoomOrder> roomOrderList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.roomOrderList = roomOrderList;
    }

    public OrderExecution(OrderStateEnum stateEnum, RecreateOrder recreateOrder){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.recreateOrder = recreateOrder;
    }

    public OrderExecution(OrderStateEnum stateEnum, RoomOrder roomOrder, RecreateOrder recreateOrder){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.roomOrder = roomOrder;
        this.recreateOrder = recreateOrder;
    }

    public OrderExecution(OrderStateEnum stateEnum, List<RoomOrder> roomOrderList, List<RecreateOrder> recreateOrderList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.roomOrderList = roomOrderList;
        this.recreateOrderList = recreateOrderList;
    }

    public OrderExecution(OrderStateEnum stateEnum, List<RoomOrder> roomOrderList, List<RecreateOrder> recreateOrderList, List<Room> roomList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.roomOrderList = roomOrderList;
        this.recreateOrderList = recreateOrderList;
        this.roomList = roomList;
    }
}
