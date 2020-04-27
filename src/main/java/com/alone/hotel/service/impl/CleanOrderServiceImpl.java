package com.alone.hotel.service.impl;

import com.alone.hotel.dao.CleanOrderDao;
import com.alone.hotel.dto.CleanOrderExecution;
import com.alone.hotel.entity.CleanOrder;
import com.alone.hotel.enums.OrderStateEnum;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.service.CleanOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-04-25 09:48
 * @Description:
 */
@Service
public class CleanOrderServiceImpl implements CleanOrderService {
    //订单号中日期格式
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private CleanOrderDao cleanOrderDao;

    @Override
    @Transactional
    public CleanOrderExecution addCleanOrder(CleanOrder cleanOrder) {
        if(cleanOrder != null){
            //设置订单号
            String orderId = generateOrderId(cleanOrder.getRoom().getRoomId());
            cleanOrder.setOrderId(orderId);
            //设置订单状态
            cleanOrder.setStatus(OrderStateEnum.UNFINISHED.getState());
            cleanOrder.setHandInTime(new Date());
            int effectedNum = cleanOrderDao.addCleanOrder(cleanOrder);
            if(effectedNum <= 0){
                return new CleanOrderExecution(ResultEnum.INNER_ERROR);
            }
            return new CleanOrderExecution(ResultEnum.SUCCESS, cleanOrder);
        } else {
            return new CleanOrderExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    public List<CleanOrder> queryCleanOrder(String employeeId) {
        return cleanOrderDao.queryCleanOrder(employeeId);
    }

    @Override
    @Transactional
    public CleanOrderExecution updateCleanOrderStatus(CleanOrder cleanOrder) {
        if(cleanOrder != null && cleanOrder.getRoom() != null && cleanOrder.getRoom().getRoomId() != null && cleanOrder.getEmployee() != null && cleanOrder.getEmployee().getEmployeeId() != null){
            int effectedNum = cleanOrderDao.updateCleanOrderStatus(cleanOrder);
            if(effectedNum <= 0){
                return new CleanOrderExecution(ResultEnum.INNER_ERROR);
            }
            return new CleanOrderExecution(ResultEnum.SUCCESS, cleanOrder);
        } else {
            return new CleanOrderExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public CleanOrderExecution deleteCleanOrder(CleanOrder cleanOrder) {
        if(cleanOrder != null && cleanOrder.getRoom() != null && cleanOrder.getRoom().getRoomId() != null && cleanOrder.getEmployee() != null && cleanOrder.getEmployee().getEmployeeId() != null){
            int effectedNum = cleanOrderDao.deleteCleanOrder(cleanOrder);
            if(effectedNum <= 0){
                return new CleanOrderExecution(ResultEnum.INNER_ERROR);
            }
            return new CleanOrderExecution(ResultEnum.SUCCESS);
        } else {
            return new CleanOrderExecution(ResultEnum.EMPTY);
        }
    }

    /**
     * 生成订单号
     * 订单号格式:日期+房间号+序号
     * @return
     */
    private String generateOrderId(int roomId){
        String date = simpleDateFormat.format(new Date());
        int count = cleanOrderDao.queryOrderCount(date);
        String orderId = date + roomId + (count + 1);
        return orderId;
    }
}
