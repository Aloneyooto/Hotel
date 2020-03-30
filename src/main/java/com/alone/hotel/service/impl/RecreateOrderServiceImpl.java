package com.alone.hotel.service.impl;

import com.alone.hotel.dao.RecreateOrderDao;
import com.alone.hotel.dto.OrderExecution;
import com.alone.hotel.entity.RecreateOrder;
import com.alone.hotel.enums.OrderStateEnum;
import com.alone.hotel.exceptions.OrderException;
import com.alone.hotel.service.RecreateOrderService;
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
 * @CreateTime: 2020-03-29 15:26
 * @Description:
 */
@Service
public class RecreateOrderServiceImpl implements RecreateOrderService {
    //订单号日期格式
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    //订单号编号格式
    private static final DecimalFormat orderNumberFormat = new DecimalFormat("000");
    @Autowired
    private RecreateOrderDao recreateOrderDao;

    @Override
    @Transactional
    public OrderExecution addRecreateOrder(RecreateOrder recreateOrder) {
        if(recreateOrder != null && recreateOrder.getCustomer() != null && recreateOrder.getCustomer().getCustomerCardNumber() != null){
            try{
                //设置初始值
                recreateOrder.setHandInTime(new Date());
                recreateOrder.setOrderStatus(0);
                //生成订单ID
                String orderId = generateRoomOrderId(recreateOrder.getHandInTime());
                recreateOrder.setOrderId(orderId);
                int effectNum = recreateOrderDao.addRecreateOrder(recreateOrder);
                if(effectNum <= 0){
                    throw new OrderException(OrderStateEnum.RECREATE_ORDER_INSERT_ERROR.getStateInfo());
                }
                return new OrderExecution(OrderStateEnum.SUCCESS);
            } catch (Exception e){
                throw new OrderException(OrderStateEnum.RECREATE_ORDER_INSERT_ERROR.getStateInfo());
            }
        } else {
            return new OrderExecution(OrderStateEnum.RECREATE_ORDER_EMPTY);
        }
    }

    @Override
    public List<RecreateOrder> queryRecreateOrderByCustomer(RecreateOrder recreateOrder) {
        return recreateOrderDao.queryRecreateOrderByCustomer(recreateOrder);
    }

    @Override
    @Transactional
    public OrderExecution updateRecreateOrder(RecreateOrder recreateOrder) {
        if(recreateOrder != null && recreateOrder.getCustomer() != null && recreateOrder.getCustomer().getCustomerCardNumber() != null){
            try{
                int effectNum = recreateOrderDao.updateRecreateOrder(recreateOrder);
                if(effectNum <= 0){
                    throw new OrderException(OrderStateEnum.RECREATE_ORDER_UPDATE_ERROR.getStateInfo());
                }
                return new OrderExecution(OrderStateEnum.SUCCESS);
            } catch (Exception e){
                throw new OrderException(OrderStateEnum.RECREATE_ORDER_UPDATE_ERROR.getStateInfo());
            }
        } else {
            return new OrderExecution(OrderStateEnum.RECREATE_ORDER_EMPTY);
        }
    }

    @Override
    @Transactional
    public OrderExecution deleteRecreateOrder(String recreateOrderId) {
        if(recreateOrderId != null){
            try {
                int effectNum = recreateOrderDao.deleteRecreateOrder(recreateOrderId);
                if(effectNum <= 0){
                    throw new OrderException(OrderStateEnum.RECREATE_ORDER_DELETE_ERROR.getStateInfo());
                }
                return new OrderExecution(OrderStateEnum.SUCCESS);
            } catch (Exception e){
                throw new OrderException(OrderStateEnum.RECREATE_ORDER_DELETE_ERROR.getStateInfo());
            }
        } else {
            return new OrderExecution(OrderStateEnum.RECREATE_ORDER_ID_EMPTY);
        }
    }

    /**
     * 生成订单ID
     * 格式:日期+订单号
     * @return
     */
    private String generateRoomOrderId(Date handInTime){
        //日期字符串
        String dateStr = dateFormat.format(new Date());
        int count = recreateOrderDao.queryOrderCount(handInTime);
        String numberStr = orderNumberFormat.format(count);
        //订单字符串
        String orderStr = dateStr + numberStr;
        return orderStr;
    }
}
