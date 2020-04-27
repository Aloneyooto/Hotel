package com.alone.hotel.dto;

import com.alone.hotel.entity.CleanOrder;
import com.alone.hotel.enums.ResultEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-04-25 09:45
 * @Description:
 */
@Data
public class CleanOrderExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //商品数量
    private int count;
    //操作的清洁订单
    private CleanOrder cleanOrder;
    //操作的清洁订单列表
    private List<CleanOrder> cleanOrderList;

    public CleanOrderExecution() {
    }

    public CleanOrderExecution(ResultEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public CleanOrderExecution(ResultEnum stateEnum, CleanOrder CleanOrder){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.cleanOrder = CleanOrder;
    }

    public CleanOrderExecution(ResultEnum stateEnum, List<CleanOrder> CleanOrderList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.cleanOrderList = CleanOrderList;
    }
}
