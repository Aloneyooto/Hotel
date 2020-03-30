package com.alone.hotel.service.impl;

import com.alone.hotel.dao.RecreateOrderDao;
import com.alone.hotel.dao.RecreationDao;
import com.alone.hotel.dto.OrderExecution;
import com.alone.hotel.entity.RecreateOrder;
import com.alone.hotel.entity.Recreation;
import com.alone.hotel.enums.OrderStateEnum;
import com.alone.hotel.exceptions.OrderException;
import com.alone.hotel.service.RecreateOrderService;
import com.alone.hotel.service.RecreationService;
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
    @Autowired
    private RecreationDao recreationDao;

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
                //计算订单价格
                Recreation recreation = recreationDao.queryRecreationById(recreateOrder.getRecreation().getRecreationId());
                double price = recreation.getRecreationPrice() * getDatePoor(recreateOrder.getStartTime(), recreateOrder.getEndTime());
                recreateOrder.setOrderPrice(price);
                recreateOrder.setRecreation(recreation);
                //添加订单
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

    /**
     * 计算两个时间之间相差多少小时
     * @param endDate
     * @param startDate
     * @return
     */
    public static int getDatePoor(Date startDate, Date endDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return (int)hour;
    }
}
