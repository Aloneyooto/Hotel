package com.alone.hotel.service.impl;

import com.alone.hotel.dao.RecreateOrderDao;
import com.alone.hotel.dao.RecreationDao;
import com.alone.hotel.dto.OrderExecution;
import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.entity.RecreateOrder;
import com.alone.hotel.entity.Recreation;
import com.alone.hotel.enums.OrderStateEnum;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.exceptions.OrderException;
import com.alone.hotel.service.RecreateOrderService;
import com.alone.hotel.service.RecreationService;
import com.alone.hotel.utils.PageUtil;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
                recreateOrder.setOrderStatus(OrderStateEnum.UNFINISHED.getState());
                recreateOrder.setStartTime(new Date());
                //生成订单ID
                String orderId = generateRoomOrderId(recreateOrder.getHandInTime());
                recreateOrder.setOrderId(orderId);
                //设置订单类别
                Recreation recreation = recreationDao.queryRecreationById(recreateOrder.getRecreation().getRecreationId());
                recreateOrder.setRecreation(recreation);
                //添加订单
                int effectNum = recreateOrderDao.addRecreateOrder(recreateOrder);
                if(effectNum <= 0){
                    throw new OrderException(ResultEnum.INNER_ERROR.getStateInfo());
                }
                return new OrderExecution(ResultEnum.SUCCESS, recreateOrder);
            } catch (Exception e){
                throw new OrderException(ResultEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new OrderExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    public Customer queryRecreateOrderByCustomer(Integer recreationId, Integer orderStatus, String customerCardNumber) {
        return recreateOrderDao.queryRecreateOrderByCustomer(recreationId, orderStatus, customerCardNumber);
    }

    @Override
    public CustomerAccount queryRecreationListByAccount(String accountName) {
        return recreateOrderDao.queryRecreationListByAccount(accountName);
    }


    @Override
    public OrderExecution queryRecreateOrderList(RecreateOrder orderCondition, int pageIndex, int pageSize) {
        //页数转换成数据库的行数
        int rowIndex = PageUtil.calculateRowIndex(pageIndex, pageSize);
        //查询所需记录
        List<RecreateOrder> recreateOrderList = recreateOrderDao.queryRecreateOrderList(orderCondition, rowIndex, pageSize);
        //总记录数
        int count = recreateOrderDao.queryRecreateOrderCount(orderCondition);
        OrderExecution orderExecution = new OrderExecution();
        orderExecution.setRecreateOrderCount(count);
        orderExecution.setRecreateOrderList(recreateOrderList);
        return orderExecution;
    }


    @Override
    @Transactional
    public OrderExecution updateRecreateOrder(RecreateOrder recreateOrder) {
        if(recreateOrder != null && recreateOrder.getCustomer() != null && recreateOrder.getCustomer().getCustomerCardNumber() != null){
            try{
                Customer result = recreateOrderDao.queryRecreateOrderByCustomer(recreateOrder.getRecreation().getRecreationId(),
                        recreateOrder.getOrderStatus(), recreateOrder.getCustomer().getCustomerCardNumber());
                if(recreateOrder.getOrderStatus() == OrderStateEnum.UNFINISHED.getState()){
                    //对于某一消费类型没有支付的应当只有1个
                    RecreateOrder oldOrder = result.getRecreateOrderList().get(0);
                    //计算订单价格
                    oldOrder.setEndTime(new Date());
                    int hours = getDatePoor(oldOrder.getStartTime(), oldOrder.getEndTime());
                    double price = oldOrder.getRecreation().getRecreationPrice() * hours;
                    oldOrder.setOrderPrice(price);
                    oldOrder.setOrderStatus(OrderStateEnum.UNPAID.getState());
                    int effectNum = recreateOrderDao.updateRecreateOrder(oldOrder);
                    if(effectNum <= 0){
                        throw new OrderException(ResultEnum.INNER_ERROR.getStateInfo());
                    }
                } else {
                    int effectNum = recreateOrderDao.updateRecreateOrder(recreateOrder);
                    if(effectNum <= 0){
                        throw new OrderException(ResultEnum.INNER_ERROR.getStateInfo());
                    }
                }
                Customer updateResult = recreateOrderDao.queryRecreateOrderByCustomer(-1, -1, recreateOrder.getCustomer().getCustomerCardNumber());
                return new OrderExecution(ResultEnum.SUCCESS, null, updateResult.getRecreateOrderList());
            } catch (Exception e){
                throw new OrderException(ResultEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new OrderExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public OrderExecution deleteRecreateOrder(String recreateOrderId) {
        if(recreateOrderId != null){
            try {
                int effectNum = recreateOrderDao.deleteRecreateOrder(recreateOrderId);
                if(effectNum <= 0){
                    throw new OrderException(ResultEnum.INNER_ERROR.getStateInfo());
                }
                return new OrderExecution(ResultEnum.SUCCESS);
            } catch (Exception e){
                throw new OrderException(ResultEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new OrderExecution(ResultEnum.EMPTY);
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
        return (int)(min % 60 == 0 ? hour : hour + 1);
    }

//    public static void main(String[] args) {
//        Calendar startTime = Calendar.getInstance();
//        startTime.set(2020, 4, 29, 0, 0, 0);
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(2020, 4, 29, 1, 0, 0);
//        System.out.println(getDatePoor(startTime.getTime(), endTime.getTime()));
//    }
}
