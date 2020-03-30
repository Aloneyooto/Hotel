package com.alone.hotel.service.impl;

import com.alone.hotel.dao.RoomOrderDao;
import com.alone.hotel.dto.OrderExecution;
import com.alone.hotel.entity.RoomOrder;
import com.alone.hotel.enums.OrderStateEnum;
import com.alone.hotel.exceptions.OrderException;
import com.alone.hotel.service.RoomOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-29 14:48
 * @Description:
 */
@Service
public class RoomOrderServiceImpl implements RoomOrderService {
    //订单号日期格式
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    //订单号编号格式
    private static final DecimalFormat orderNumberFormat = new DecimalFormat("000");
    @Autowired
    private RoomOrderDao roomOrderDao;

    @Override
    @Transactional
    public OrderExecution addRoomOrder(RoomOrder roomOrder) {
        //空值判断
        if(roomOrder != null && roomOrder.getAccount() != null && roomOrder.getAccount().getAccountName() != null){
            try{
                //设置初始值
                roomOrder.setHandInTime(new Date());
                roomOrder.setOrderStatus(0);
                //添加订单
                int effectNum = roomOrderDao.addRoomOrder(roomOrder);
                if(effectNum <= 0){
                    throw new OrderException(OrderStateEnum.ROOM_ORDER_INSERT_ERROR.getStateInfo());
                }
                return new OrderExecution(OrderStateEnum.SUCCESS);
            } catch (Exception e){
                throw new OrderException(OrderStateEnum.ROOM_ORDER_INSERT_ERROR.getStateInfo());
            }
        } else {
            return new OrderExecution(OrderStateEnum.ROOM_ORDER_EMPTY);
        }
    }

    @Override
    public List<RoomOrder> queryRoomOrderByAccountName(String accountName) {
        return roomOrderDao.queryRoomOrderByAccountName(accountName);
    }

    @Override
    public int queryOrderCount(Date handInTime) {
        return roomOrderDao.queryOrderCount(handInTime);
    }

    @Override
    @Transactional
    public OrderExecution updateRoomOrder(RoomOrder roomOrder) {
        //空值判断
        if(roomOrder != null && roomOrder.getOrderId() != null && roomOrder.getAccount() != null && roomOrder.getAccount().getAccountName() != null){
            try{
                int effectNum = roomOrderDao.updateRoomOrder(roomOrder);
                if(effectNum <= 0){
                    throw new OrderException(OrderStateEnum.ROOM_ORDER_UPDATE_ERROR.getStateInfo());
                }
                return new OrderExecution(OrderStateEnum.SUCCESS);
            } catch (Exception e){
                throw new OrderException(OrderStateEnum.ROOM_ORDER_UPDATE_ERROR.getStateInfo());
            }
        } else {
            return new OrderExecution(OrderStateEnum.ROOM_ORDER_EMPTY);
        }
    }

    @Override
    @Transactional
    public OrderExecution deleteRoomOrder(String orderId) {
        if(orderId != null){
            try {
                int effectNum = roomOrderDao.deleteRoomOrder(orderId);
                if(effectNum <= 0){
                    throw new OrderException(OrderStateEnum.ROOM_ORDER_DELETE_ERROR.getStateInfo());
                }
                return new OrderExecution(OrderStateEnum.SUCCESS);
            } catch (Exception e){
                throw new OrderException(OrderStateEnum.ROOM_ORDER_DELETE_ERROR.getStateInfo());
            }
        } else {
            return new OrderExecution(OrderStateEnum.ROOM_ORDER_ID_EMPTY);
        }
    }
}
