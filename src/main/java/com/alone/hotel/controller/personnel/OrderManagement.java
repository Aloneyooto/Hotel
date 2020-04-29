package com.alone.hotel.controller.personnel;

import com.alone.hotel.dto.OrderExecution;
import com.alone.hotel.entity.RecreateOrder;
import com.alone.hotel.entity.RoomOrder;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.service.RecreateOrderService;
import com.alone.hotel.service.RoomOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.personnel
 * @Author: Alone
 * @CreateTime: 2020-04-15 19:57
 * @Description:
 */

@RestController
@RequestMapping("/personnel")
public class OrderManagement {
    @Autowired
    private RoomOrderService roomOrderService;
    @Autowired
    private RecreateOrderService recreateOrderService;

    /**
     * 根据检索条件查询房间订单列表
     * 条件:用户名,房间类型,提交日期
     * @param orderCondition
     * @return
     */
    @PostMapping("/queryroomorderlist")
    private OrderExecution queryRoomOrderList(@RequestBody RoomOrder orderCondition){
        //accountName typeId handInTime
        try {
            List<RoomOrder> roomOrderList = roomOrderService.queryRoomOrderByCondition(orderCondition);
            return new OrderExecution(ResultEnum.SUCCESS, roomOrderList);
        } catch (Exception e){
            return new OrderExecution(ResultEnum.INNER_ERROR);
        }
    }


    /**
     * 根据筛选条件查询娱乐订单
     * @param request
     * @param recreateOrder
     * @return
     */
    @PostMapping("/queryrecreateorderlist")
    private OrderExecution queryRecreateOrderList(HttpServletRequest request, @RequestBody RecreateOrder recreateOrder){
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        if(pageIndex > 0 && pageSize > 0){
            try{
                OrderExecution orderExecution = recreateOrderService.queryRecreateOrderList(recreateOrder, pageIndex, pageSize);
                orderExecution.setState(ResultEnum.SUCCESS.getState());
                orderExecution.setStateInfo(ResultEnum.SUCCESS.getStateInfo());
                return orderExecution;
            } catch (Exception e){
                return new OrderExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            return new OrderExecution(ResultEnum.PAGE_ERROR);
        }
    }

}
